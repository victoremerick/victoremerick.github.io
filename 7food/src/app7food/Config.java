package app7food;

import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.swing.JOptionPane;

public class Config {

    public static void load() {
        File f = new File("config.txt");
        if (!f.exists()) {
            save();
            return;
        }
        FileReader fr = null;
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException ex) {
        }
        BufferedReader br = new BufferedReader(fr);
        try {
            Configs.IMPRESSORA = br.readLine();
            Configs.INTERVALOALERTA = Integer.parseInt(br.readLine());
            Configs.INTERVALOTABELA = Integer.parseInt(br.readLine());
            Usuario.email = br.readLine();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível carregar as configurações.");
        }
    }

    public static void save() {
        File f = new File("config.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível salvar as configurações básicas.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        try {
            if (Configs.IMPRESSORA.equals("")) {
                PrintService[] pservices = PrinterJob.lookupPrintServices();
                if (pservices.length > 0) {
                    JOptionPane.showMessageDialog(null, "Não foi possível encontrar uma impressora para configuração inicial.", "Erro", JOptionPane.ERROR_MESSAGE);
                }else{
                    Configs.IMPRESSORA = pservices[0].getName();
                }
            }
            FileWriter fw = new FileWriter(f, false);
            fw.write(Configs.IMPRESSORA + "\n" + Configs.INTERVALOALERTA + "\n" + Configs.INTERVALOTABELA+ "\n" + Usuario.email);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
