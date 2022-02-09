/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app7food.models;

import app7food.json.JSONObject;

public class Entregador {

    private String IDCourier;
    private String Name;
    private String Telephone;
    private String Email;
    private String Status;
    private String IDCompany;
    private String created_time;
    private String updated_time;

    public Entregador(JSONObject j) {
        IDCourier = JSONManager.get(j, "IDCourier");
        Name = JSONManager.get(j, "Name");
        Telephone = JSONManager.get(j, "Telephone");
        Email = JSONManager.get(j, "Email");
        Status = JSONManager.get(j, "Status");
        IDCompany = JSONManager.get(j, "IDCompany");
        created_time = JSONManager.get(j, "created_time");
        updated_time = JSONManager.get(j, "updated_time");
    }

    public String getIDCourier() {
        return IDCourier;
    }

    public String getName() {
        return Name;
    }

    public String getTelephone() {
        return Telephone;
    }

    public String getEmail() {
        return Email;
    }

    public String getStatus() {
        return Status;
    }

    public String getIDCompany() {
        return IDCompany;
    }

    public String getCreated_time() {
        return created_time;
    }

    public String getUpdated_time() {
        return updated_time;
    }
    
}
