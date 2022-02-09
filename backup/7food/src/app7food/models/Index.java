/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app7food.models;

import app7food.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Index {

    private static List<Entregador> entregadores = new ArrayList();
    private static List<Produto> produtos = new ArrayList();
    private static List<Pedido> pedidos = new ArrayList();
    private static String DeliveryTime = "";

    public static void addEntregador(JSONObject o) {
        entregadores.add(new Entregador(o));
    }

    public static void addProduto(String ID, JSONObject o) {
        produtos.add(new Produto(ID, o));
    }

    public static void addPedidos(JSONObject o) {
        pedidos.add(new Pedido(o, produtos));
    }
    public static void addPedidos(Pedido p) {
        pedidos.add(p);
    }
    
    public static void setDeliveryTime(String s){
        DeliveryTime = s;
    }

    public static List<Entregador> getEntregadores() {
        return entregadores;
    }

    public static List<Produto> getProdutos() {
        return produtos;
    }

    public static List<Pedido> getPedidos() {
        return pedidos;
    }

    public static String getDeliveryTime() {
        return DeliveryTime;
    }
    
}
