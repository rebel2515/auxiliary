/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.payload;

import java.util.List;

/**
 *
 * @author eisrael
 */
public class ProductFields {

    private String FieldName;
    private String ControlType;
    private List<ListItems> ListItems;

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String FieldName) {
        this.FieldName = FieldName;
    }

    public String getControlType() {
        return ControlType;
    }

    public void setControlType(String ControlType) {
        this.ControlType = ControlType;
    }

    public List<ListItems> getListItems() {
        return ListItems;
    }

    public void setListItems(List<ListItems> ListItems) {
        this.ListItems = ListItems;
    }
}
