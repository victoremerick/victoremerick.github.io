/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app7food.models;

import app7food.json.JSONObject;

public class CustomerAddress {

    private String Table;
    private String TableName;
    private String TablePhone;
    private String Status;
    private String Entity;
    private String created_time;
    private String updated_time;
    private String Address;
    private String Telephone;
    private String Reference;
    private String Address2;
    private String Address3;
    private String City;
    private String MapLong;
    private String MapLat;
    private String Number;
    private String SEOCity;
    private String State;
    private String Country;
    private String IDAddress;
    private String PostCode;
    private String Main;
    private String IDEntity;
    private String Nickname;

    public CustomerAddress(JSONObject j) {
        Status = JSONManager.get(j, "Status");
        Entity = JSONManager.get(j, "Entity");
        created_time = JSONManager.get(j, "created_time");
        updated_time = JSONManager.get(j, "updated_time");
        Address = JSONManager.get(j, "Address");
        Telephone = JSONManager.get(j, "Telephone");
        Reference = JSONManager.get(j, "Reference");
        Address2 = JSONManager.get(j, "Address2");
        Address3 = JSONManager.get(j, "Address3");
        City = JSONManager.get(j, "City");
        MapLong = JSONManager.get(j, "MapLong");
        MapLat = JSONManager.get(j, "MapLat");
        Number = JSONManager.get(j, "Number");
        SEOCity = JSONManager.get(j, "SEOCity");
        State = JSONManager.get(j, "State");
        Country = JSONManager.get(j, "Country");
        IDAddress = JSONManager.get(j, "IDAddress");
        PostCode = JSONManager.get(j, "PostCode");
        Main = JSONManager.get(j, "Main");
        IDEntity = JSONManager.get(j, "IDEntity");
        Nickname = JSONManager.get(j, "Nickname");
        Table = JSONManager.get(j, "Table");
        TableName = JSONManager.get(j, "TableName");
        TablePhone = JSONManager.get(j, "TablePhone");
    }

    public String getTable() {
        return Table;
    }

    public String getTableName() {
        return TableName;
    }

    public String getTablePhone() {
        return TablePhone;
    }

    public String getStatus() {
        return Status;
    }

    public String getEntity() {
        return Entity;
    }

    public String getCreated_time() {
        return created_time;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public String getAddress() {
        return Address;
    }

    public String getTelephone() {
        return Telephone;
    }

    public String getReference() {
        return Reference;
    }

    public String getAddress2() {
        return Address2;
    }

    public String getAddress3() {
        return Address3;
    }

    public String getCity() {
        return City;
    }

    public String getMapLong() {
        return MapLong;
    }

    public String getMapLat() {
        return MapLat;
    }

    public String getNumber() {
        return Number;
    }

    public String getSEOCity() {
        return SEOCity;
    }

    public String getState() {
        return State;
    }

    public String getCountry() {
        return Country;
    }

    public String getIDAddress() {
        return IDAddress;
    }

    public String getPostCode() {
        return PostCode;
    }

    public String getMain() {
        return Main;
    }

    public String getIDEntity() {
        return IDEntity;
    }

    public String getNickname() {
        return Nickname;
    }
}
