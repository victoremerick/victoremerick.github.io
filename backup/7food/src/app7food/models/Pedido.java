/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app7food.models;

import app7food.json.JSONArray;
import app7food.json.JSONObject;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {

    private Date datapedido;
    private Date datadispatched;
    private String IDOrder;
    private String IDCustomer;
    private String IDCashier;
    private String IDCompany;
    private String IDCustomerAddress;
    private String AddressEntity;
    private CustomerAddress CustomerAddress;
    private String CustomerMessage;
    private String CanceledMessage;
    private String TipoDelivery;
    private String IDCourier;
    private String DeliveryTime;
    private String DateTime;
    private List<Produto> Products;
    private List<Integer> Quantities;
    private String Varieties;
    private String Sizes;
    private String size;
    private String Options;
    private String Payment;
    private String PaymentType;
    private String ValueDelivery;
    private String ValueFixPerTransaction;
    private String ValuePay;
    private String Total;
    private String ChangeFor;
    private String ChangeForMoney;
    private String Discount;
    private String ToPay;
    private String DataCardOrder;
    private String OrderReady;
    private String OrderPrint;
    private Color[] Status = new Color[2];
    private String Status1;
    private String TotalDesc;
    private String ItensDesc;
    private String ComboOptions;
    private String CancelRequest;
    private String CancelRequestJustify;
    private String CancelResponse;
    private String created_time;
    private String updated_time;
    private String Name;
    private String Surname;
    private String Telefone;

    public Pedido(JSONObject j, List<Produto> prods) {
        IDOrder = JSONManager.get(j, "IDOrder");
        IDCustomer = JSONManager.get(j, "IDCustomer");
        IDCashier = j.get("IDCashier") + "";
        IDCompany = JSONManager.get(j, "IDCompany");
        IDCustomerAddress = JSONManager.get(j, "IDCustomerAddress");
        AddressEntity = JSONManager.get(j, "AddressEntity");
        String CA = j.get("CustomerAddress").toString();
        if (!CA.equals("")) {
            if ((CA.charAt(0) + "").equals("{")) {
                CA = "[" + CA + "]";
            }
            JSONArray a = new JSONArray(CA);
            if (a.length() != 0) {
                CustomerAddress = new CustomerAddress(a.getJSONObject(0));
            } else {
                CustomerAddress = new CustomerAddress(new JSONObject("{}"));
            }
        } else {
            CustomerAddress = new CustomerAddress(new JSONObject("{}"));
        }
        CustomerMessage = JSONManager.get(j, "CustomerMessage");
        CanceledMessage = j.get("CanceledMessage") + "";
        TipoDelivery = JSONManager.get(j, "TipoDelivery");
        IDCourier = j.get("IDCourier") + "";
        DeliveryTime = JSONManager.get(j, "DeliveryTime");
        DateTime = JSONManager.get(j, "DateTime");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = sdf.parse(DateTime);
            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
            Date newD = new Date(d.getTime() + (Long.parseLong(Index.getDeliveryTime()) * 60000));
            datapedido = newD;
            DateTime = sdf1.format(newD);
        } catch (ParseException ex) {
            System.err.println("Deu Ruim");
        }
        String prds = j.get("Products").toString();
        JSONArray produtos = new JSONArray(prds);
        Products = new ArrayList();
        for (int i = 0; i < produtos.length(); i++) {
            for (int k = 0; k < prods.size(); k++) {
                if (prods.get(k).getIDProduct().equals(produtos.get(i).toString())) {
                    Products.add(prods.get(k));
                    break;
                }
            }
        }
        Varieties = JSONManager.get(j, "Varieties");
        Sizes = JSONManager.get(j, "Sizes");
        size = JSONManager.get(j, "size");
        String Quantity = JSONManager.get(j, "Quantity");
        JSONArray qnts = new JSONArray(Quantity);
        Quantities = new ArrayList();
        for (int i = 0; i < qnts.length(); i++) {
            Quantities.add(qnts.getInt(i));
        }
        Options = JSONManager.get(j, "Options");
        Payment = j.get("Payment") + "";
        PaymentType = JSONManager.get(j, "PaymentType");
        ValueDelivery = JSONManager.get(j, "ValueDelivery");
        ValueFixPerTransaction = JSONManager.get(j, "ValueFixPerTransaction");
        ValuePay = JSONManager.get(j, "ValuePay");
        Total = JSONManager.get(j, "Total");
        ChangeFor = JSONManager.get(j, "ChangeFor");
        ChangeForMoney = JSONManager.get(j, "ChangeForMoney");
        Discount = j.get("Discount") + "";
        ToPay = JSONManager.get(j, "ToPay");
        DataCardOrder = j.get("DataCardOrder") + "";
        OrderReady = JSONManager.get(j, "OrderReady");
        OrderPrint = JSONManager.get(j, "OrderPrint");
        Status1 = JSONManager.get(j, "Status");
        if (Status1.equals("Accepted")) {
            if (OrderReady.equals("1")) {
                this.Status[0] = new Color(250, 242, 204);
                this.Status[1] = new Color(230, 126, 34);
            } else {
                this.Status[0] = new Color(217, 237, 247);
                this.Status[1] = new Color(52, 152, 219);
            }
        } else if (Status1.equals("Waiting")) {
            this.Status[0] = new Color(242, 222, 222);
            this.Status[1] = new Color(231, 76, 60);
        } else if (Status1.equals("Dispatched")) {
            this.Status[0] = new Color(208, 233, 198);
            this.Status[1] = new Color(39, 174, 96);
        }
        TotalDesc = JSONManager.get(j, "TotalDesc");
        ItensDesc = JSONManager.get(j, "ItensDesc");
        ComboOptions = JSONManager.get(j, "ComboOptions");
        CancelRequest = j.get("CancelRequest") + "";
        CancelRequestJustify = j.get("CancelRequestJustify") + "";
        CancelResponse = j.get("CancelResponse") + "";
        created_time = JSONManager.get(j, "created_time");
        updated_time = JSONManager.get(j, "updated_time");
        Name = JSONManager.get(j, "Name");
        Surname = JSONManager.get(j, "Surname");
        Telefone = JSONManager.get(j, "Telefone");
    }

    public String getIDOrder() {
        return IDOrder;
    }

    public String getIDCustomer() {
        return IDCustomer;
    }

    public String getIDCashier() {
        return IDCashier;
    }

    public String getIDCompany() {
        return IDCompany;
    }

    public String getIDCustomerAddress() {
        return IDCustomerAddress;
    }

    public String getAddressEntity() {
        return AddressEntity;
    }

    public CustomerAddress getCustomerAddress() {
        return CustomerAddress;
    }

    public String getCustomerMessage() {
        return CustomerMessage;
    }

    public String getCanceledMessage() {
        return CanceledMessage;
    }

    public String getTipoDelivery() {
        return TipoDelivery;
    }

    public String getIDCourier() {
        return IDCourier;
    }

    public String getDeliveryTime() {
        return DeliveryTime;
    }

    public String getDateTime() {
        return DateTime;
    }

    public List<Produto> getProducts() {
        return Products;
    }

    public String getVarieties() {
        return Varieties;
    }

    public String getSizes() {
        return Sizes;
    }

    public String getSize() {
        return size;
    }

    public List<Integer> getQuantity() {
        return Quantities;
    }

    public String getOptions() {
        return Options;
    }

    public String getPayment() {
        return Payment;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public String getValueDelivery() {
        return ValueDelivery;
    }

    public String getValueFixPerTransaction() {
        return ValueFixPerTransaction;
    }

    public String getValuePay() {
        return ValuePay;
    }

    public String getTotal() {
        return Total;
    }

    public String getChangeFor() {
        return ChangeFor;
    }

    public String getChangeForMoney() {
        return ChangeForMoney;
    }

    public String getDiscount() {
        return Discount;
    }

    public String getToPay() {
        return ToPay;
    }

    public String getDataCardOrder() {
        return DataCardOrder;
    }

    public String getOrderReady() {
        return OrderReady;
    }

    public String getOrderPrint() {
        return OrderPrint;
    }

    public Color[] getStatus() {
        return Status;
    }

    public String getTotalDesc() {
        return TotalDesc;
    }

    public String getItensDesc() {
        return ItensDesc;
    }

    public String getComboOptions() {
        return ComboOptions;
    }

    public String getCancelRequest() {
        return CancelRequest;
    }

    public String getCancelRequestJustify() {
        return CancelRequestJustify;
    }

    public String getCancelResponse() {
        return CancelResponse;
    }

    public String getCreated_time() {
        return created_time;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getTelefone() {
        return Telefone;
    }

    public List<Integer> getQuantities() {
        return Quantities;
    }

    public String getStatus1() {
        return Status1;
    }

    public void setStatus(String Status) {
        Status1 = Status;
        if (Status1.equals("Accepted")) {
            if (OrderReady.equals("1")) {
                this.Status[0] = new Color(250, 242, 204);
                this.Status[1] = new Color(230, 126, 34);
            } else {
                this.Status[0] = new Color(217, 237, 247);
                this.Status[1] = new Color(52, 152, 219);
            }
        } else if (Status1.equals("Waiting")) {
            this.Status[0] = new Color(242, 222, 222);
            this.Status[1] = new Color(231, 76, 60);
        } else if (Status1.equals("Dispatched")) {
            this.Status[0] = new Color(208, 233, 198);
            this.Status[1] = new Color(39, 174, 96);
            datadispatched = new Date();
        }
    }

    public void setOrderReady(String OrderReady) {
        this.OrderReady = OrderReady;
    }

    public int verificarAtraso() {
        if (!Status1.equals("Delivered")) {
            long hora = (new Date()).getTime() - datapedido.getTime();
            if (hora > 0) {
                Date d = new Date(hora);
                if (d.getMinutes() <= 5) {
                    return 1;
                } else {
                    return 2;
                }
            } else if (Status1.equals("Waiting")) {
                return 3;
            }
        }else if(Status1.equals("Dispatched")){
            long hora = (new Date()).getTime() - datadispatched.getTime();
            if (hora > 0) {
                Date d = new Date(hora);
                if (d.getMinutes() >= 30) {
                    return 2;
                }else if(d.getMinutes() >= 15){
                    return 1;
                }
            }
        }
        return 0;
    }

}
