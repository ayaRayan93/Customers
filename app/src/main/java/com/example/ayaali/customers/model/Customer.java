package com.example.ayaali.customers.model;

/**
 * Created by AyaAli on 05/03/2018.
 */

public class Customer {
    String CustomerCode,CustName,CustomerNameLat,StreetAra,Classification,PersonToConnect,Tel,TAXID,SaleAreaCode,Notes,SalesRepCode,CodeList,NotActive;

    public String getCustomerCode() {
        return CustomerCode;
    }

    public Customer() {
    }

    public Customer(String customerCode, String custName, String streetAra, String classification, String personToConnect, String tel, String TAXID, String saleAreaCode) {
        CustomerCode = customerCode;
        CustName = custName;
        StreetAra = streetAra;
        Classification = classification;
        PersonToConnect = personToConnect;
        Tel = tel;
        this.TAXID = TAXID;
        SaleAreaCode = saleAreaCode;
    }

    public void setCustomerCode(String customerCode) {
        CustomerCode = customerCode;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    public String getCustomerNameLat() {
        return CustomerNameLat;
    }

    public void setCustomerNameLat(String customerNameLat) {
        CustomerNameLat = customerNameLat;
    }

    public String getStreetAra() {
        return StreetAra;
    }

    public void setStreetAra(String streetAra) {
        StreetAra = streetAra;
    }

    public String getClassification() {
        return Classification;
    }

    public void setClassification(String classification) {
        Classification = classification;
    }

    public String getPersonToConnect() {
        return PersonToConnect;
    }

    public void setPersonToConnect(String personToConnect) {
        PersonToConnect = personToConnect;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getTAXID() {
        return TAXID;
    }

    public void setTAXID(String TAXID) {
        this.TAXID = TAXID;
    }

    public String getSaleAreaCode() {
        return SaleAreaCode;
    }

    public void setSaleAreaCode(String saleAreaCode) {
        SaleAreaCode = saleAreaCode;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getSalesRepCode() {
        return SalesRepCode;
    }

    public void setSalesRepCode(String salesRepCode) {
        SalesRepCode = salesRepCode;
    }

    public String getCodeList() {
        return CodeList;
    }

    public void setCodeList(String codeList) {
        CodeList = codeList;
    }

    public String getNotActive() {
        return NotActive;
    }

    public void setNotActive(String notActive) {
        NotActive = notActive;
    }
}
