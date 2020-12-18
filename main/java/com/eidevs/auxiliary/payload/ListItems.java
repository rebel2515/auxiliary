/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eidevs.auxiliary.payload;

/**
 *
 * @author eisrael
 */
public class ListItems {
    private String ItemType;
    private String ItemName;
    private String Amount;
    private String ItemDesc;
    private String ItemOthers;

    public String getItemType() {
        return ItemType;
    }

    public void setItemType(String ItemType) {
        this.ItemType = ItemType;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getItemDesc() {
        return ItemDesc;
    }

    public void setItemDesc(String ItemDesc) {
        this.ItemDesc = ItemDesc;
    }

    public String getItemOthers() {
        return ItemOthers;
    }

    public void setItemOthers(String ItemOthers) {
        this.ItemOthers = ItemOthers;
    }
}
