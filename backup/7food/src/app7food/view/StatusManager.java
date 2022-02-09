package app7food.view;

import app7food.Usuario;
import app7food.json.JSONObject;
import app7food.models.Pedido;
import app7food.view.icones.GetIcon;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;

public class StatusManager {

    public static boolean avancaPedido(Pedido p) {
        try {
            String url = "http://www.prod.7foods.com.br/shopkeeper/dashboard/alteraStatus?username="+Usuario.email+"&md5pass="+Usuario.encrypt()+"&ws=true&pretty=true&idCompany=10&id=" + p.getIDOrder() + "&sentido=>";
            System.out.println(url);
            JSONObject o = getJSONTable(url);
            p.setOrderReady(o.getString("orderReady"));
            p.setStatus(o.getString("status"));
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static boolean retornaPedido(Pedido p, Component c) {
        try {
            if (p.getStatus1().equals("Waiting")) {
                JOptionPane.showInputDialog(c, "Dê motivos para o pedido ser cancelado (Opcional)", "Cancelar Pedido", JOptionPane.WARNING_MESSAGE, GetIcon.create("programa"), null, null);
            }
            String url = "http://www.prod.7foods.com.br/shopkeeper/dashboard/alteraStatus?username="+Usuario.email+"&md5pass="+Usuario.encrypt()+"&ws=true&pretty=true&idCompany=10&id=" + p.getIDOrder() + "&sentido=<";
            System.out.println(url);
            JSONObject o = getJSONTable(url);
            p.setOrderReady(o.getString("orderReady"));
            p.setStatus(o.getString("status"));
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static boolean viewPedido(Pedido pedido) {
        return true;
    }

    private static JSONObject getJSONTable(String url) throws IOException {
        String json = readUrl(url);
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
