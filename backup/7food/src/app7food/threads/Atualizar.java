package app7food.threads;

import app7food.Configs;
import app7food.Usuario;
import app7food.json.JSONArray;
import app7food.json.JSONObject;
import app7food.models.Entregador;
import app7food.models.Index;
import app7food.models.Pedido;
import app7food.models.Produto;
import app7food.view.AlertAtraso;
import app7food.view.CellAcao;
import app7food.view.CellCliente;
import app7food.view.CellGeral;
import app7food.view.CellPedido;
import app7food.view.LinePedido;
import app7food.view.table.Table;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class Atualizar extends Thread {

    Table table;
    JLabel wait;
    JLabel late;
    List<LinePedido> linhas = new ArrayList();
    List<Entregador> entregadores = Index.getEntregadores();
    List<Produto> produtos = Index.getProdutos();
    List<Pedido> pedidos = Index.getPedidos();
    AlertAtraso aa = new AlertAtraso(null, false);
    JFrame paineldecontrole;
    Date ultimahora;

    public Atualizar(JFrame j, Table t, JLabel wait, JLabel late) {
        table = t;
        this.wait = wait;
        this.late = late;
        paineldecontrole = j;
    }

    @Override
    public synchronized void run() {
        execute();
        while (true) {
            try {
                sleep(Configs.INTERVALOTABELA);
            } catch (InterruptedException ex) {
                System.out.println("Thread Interrompida");
            }
            execute();

        }
    }

    public void execute() {
        try {
            JSONObject json = getJSONTable();
            if (!Index.getDeliveryTime().equals(json.getString("DeliveryTime"))) {
                Index.setDeliveryTime(json.getString("DeliveryTime"));
            }
            JSONArray es = json.getJSONArray("entregadores");
            boolean tem;
            for (int i = 0; i < es.length(); i++) {
                tem = false;
                JSONObject entregador = es.getJSONObject(i);
                for (int j = 0; j < entregadores.size(); j++) {
                    if (entregador.getString("Email").equals(entregadores.get(j).getEmail())) {
                        tem = true;
                    }
                }
                if (!tem) {
                    Index.addEntregador(es.getJSONObject(i));
                }
            }
            JSONObject ps = json.getJSONObject("produtos");
            Iterator<String> listprod = ps.keys();
            while (listprod.hasNext()) {
                tem = false;
                String l = listprod.next();
                for (int i = 0; i < produtos.size(); i++) {
                    if (produtos.get(i).getIDProduct().equals(l)) {
                        tem = true;
                    }
                }
                if (!tem) {
                    Index.addProduto(l, ps.getJSONObject(l));
                }
            }
            JSONArray pds = json.getJSONArray("pedidos");
            boolean temAlteracao = false;
            boolean temAlteracaoStatus = false;
            for (int i = 0; i < pds.length(); i++) {
                tem = false;
                JSONObject peds = pds.getJSONObject(i);
                for (int j = 0; j < pedidos.size(); j++) {
                    if (peds.getString("IDOrder").equals(pedidos.get(j).getIDOrder())) {
                        if (!peds.getString("Status").equals(pedidos.get(j).getStatus1())) {
                            if (peds.getString("Status").equals("Delivered")) {
                                Index.getPedidos().remove(j);
                            } else {
                                pedidos.get(j).setStatus(peds.getString("Status"));
                                temAlteracaoStatus = true;
                            }
                        }
                        tem = true;
                    }
                }
                if (!tem) {
                    temAlteracao = true;
                    if (!pds.getJSONObject(i).getString("Status").equals("Delivered")) {
                        Index.addPedidos(pds.getJSONObject(i));
                    }
                }
            }
            if (temAlteracao) {
                AtualizarTable();
                table.repaint();
                table.setPreferredSize(table.getSize());
                int pend = 0;
                for (Pedido pedido : Index.getPedidos()) {
                    if (pedido.getStatus1().equals("Waiting")) {
                        pend++;
                    }
                }
                wait.setText("<html><strong>" + pend + "</strong></html>");
            } else if (temAlteracaoStatus) {
                AtualizarTable();
                table.repaint();
                table.setPreferredSize(table.getSize());
            }
            verificarAtrasos();
        } catch (IOException ex) {
            System.out.println("URL Ruinho");
        }
    }

    private void AtualizarTable() {
        table.removeAll();
        for (Pedido p : Index.getPedidos()) {
            CellGeral cg = new CellGeral(p);
            CellPedido cp = new CellPedido(p);
            CellCliente cc = new CellCliente(p);
            CellAcao ca = new CellAcao(p);
            LinePedido lp = new LinePedido(cg, cp, cc, ca, p.getStatus()[0], p);
            table.addTableLine(lp);
        }
    }

    private void verificarAtrasos() {
        int atrasado = 0;
        int muitoAtrasado = 0;
        int pendentes = 0;
        boolean temAtraso = false;
        for (int i = 0; i < Index.getPedidos().size(); i++) {
            int t = Index.getPedidos().get(i).verificarAtraso();
            if (t == 1) {
                atrasado++;
                temAtraso = true;
            } else if (t == 2) {
                muitoAtrasado++;
                temAtraso = true;
            } else if (t == 3) {
                temAtraso = true;
                pendentes++;
            }
        }
        if (temAtraso) {
            if (ultimahora == null) {
                ultimahora = new Date();
            } else {
                long hora = (new Date()).getTime() - ultimahora.getTime();
                if (hora >= Configs.INTERVALOALERTA*1000*60) {
                    aa.setVisible(atrasado + "", muitoAtrasado + "", pendentes + "");
                }
            }
        }
    }

    private JSONObject getJSONTable() throws IOException {
        System.out.println("http://www.prod.7foods.com.br/shopkeeper/dashboard/index?username="+Usuario.email+"&md5pass="+Usuario.encrypt()+"&ws=true&partial=pedidos");
        String json = readUrl("http://www.prod.7foods.com.br/shopkeeper/dashboard/index?username="+Usuario.email+"&md5pass="+Usuario.encrypt()+"&ws=true&partial=pedidos");
        return new JSONObject(json);
    }

    private static String readUrl(String urlString) throws MalformedURLException, IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
