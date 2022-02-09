package app7food.models;

import app7food.json.JSONObject;

public class Produto {

    private String ID;
    private String IDVariety;
    private String IDProduct;
    private String IDSize;
    private String Name;
    private String Description;
    private String Value;
    private String Image;
    private String Sales;
    private String Status;
    private String ISOnline;
    private String ISDelivery;
    private String DescTypeCombo;
    private String order_id;
    private String created_time;
    private String updated_time;
    private String Points;
    private String IDBase;
    private String tamanho;
    private String produto;
    private String ISCombo;

    public Produto() {
    }

    public Produto(String ID, JSONObject j) {
        this.ID = ID;
        IDVariety = JSONManager.get(j, "IDVariety");
        IDProduct = JSONManager.get(j, "IDProduct");
        IDSize = JSONManager.get(j, "IDSize");
        Name = JSONManager.get(j, "Name");
        Description = JSONManager.get(j, "Description");
        Value = JSONManager.get(j, "Value");
        Image = JSONManager.get(j, "Image");
        Sales = JSONManager.get(j, "Sales");
        Status = JSONManager.get(j, "Status");
        ISOnline = JSONManager.get(j, "ISOnline");
        ISDelivery = JSONManager.get(j, "ISDelivery");
        DescTypeCombo = JSONManager.get(j, "DescTypeCombo");
        order_id = JSONManager.get(j, "order_id");
        created_time = JSONManager.get(j, "created_time");
        updated_time = JSONManager.get(j, "updated_time");
        Points = JSONManager.get(j, "Points");
        IDBase = j.get("IDBase") + "";
        tamanho = JSONManager.get(j, "tamanho");
        produto = JSONManager.get(j, "produto");
        ISCombo = JSONManager.get(j, "ISCombo");
    }

    public String getID() {
        return ID;
    }

    public String getIDVariety() {
        return IDVariety;
    }

    public String getIDProduct() {
        return IDProduct;
    }

    public String getIDSize() {
        return IDSize;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getValue() {
        return Value;
    }

    public String getImage() {
        return Image;
    }

    public String getSales() {
        return Sales;
    }

    public String getStatus() {
        return Status;
    }

    public String getISOnline() {
        return ISOnline;
    }

    public String getISDelivery() {
        return ISDelivery;
    }

    public String getDescTypeCombo() {
        return DescTypeCombo;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getCreated_time() {
        return created_time;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public String getPoints() {
        return Points;
    }

    public String getIDBase() {
        return IDBase;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getProduto() {
        return produto;
    }

    public String getISCombo() {
        return ISCombo;
    }

}
