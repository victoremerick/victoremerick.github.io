/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app7food.models;

import app7food.json.JSONArray;
import app7food.json.JSONException;
import app7food.json.JSONObject;
import app7food.models.Index;
import app7food.threads.Atualizar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class JSONManager {

    private static String readUrl(String urlString) throws MalformedURLException, IOException{
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

    public static void main(String[] args){
        
    }
    
    public static void init() {
        try {
            String json = readUrl("http://www.prod.7foods.com.br/shopkeeper/dashboard/index?ws=true&partial=pedidos&idCompany=10");
            json = json.replace("<pre>", "");
            json = json.replace("</pre>", "");
            
            JSONObject json1 = new JSONObject(json);
            JSONArray entregadores = json1.getJSONArray("entregadores");
            for (int i = 0; i < entregadores.length(); i++) {
                Index.addEntregador(entregadores.getJSONObject(i));
            }
            JSONObject produtos = json1.getJSONObject("produtos");
            Iterator<String> listprod = produtos.keys();
            while (listprod.hasNext()) {
                String l = listprod.next();
                Index.addProduto(l, produtos.getJSONObject(l));
            }
            JSONArray pedidos = json1.getJSONArray("pedidos");
            for (int i = 0; i < pedidos.length(); i++) {
                Index.addPedidos(pedidos.getJSONObject(i));
            }
            Index.setDeliveryTime(json1.getString("DeliveryTime"));
        } catch (IOException ex) {
            System.err.println("Houve um erro ao coletar os dados.");
        }
    }

    public static String get(JSONObject s, String r) {
        try {
            String t = s.get(r) + "";
            return t;
        } catch (JSONException e) {
            return "";
        }
    }

}
