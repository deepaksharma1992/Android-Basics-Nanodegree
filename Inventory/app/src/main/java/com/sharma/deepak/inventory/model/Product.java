package com.sharma.deepak.inventory.model;

import java.io.Serializable;

/**
 * Created by deepak on 16/7/2017.
 */

public class Product implements Serializable {
    private String productName, purchaseDate, phone, imageAddress;
    private int productPrice, productQuantity, productId;


    public Product(int id, String productName, String purchaseDate, int productPrice, int productQuantity,String phone,String imageAddress) {
        this.productId = id;
        this.productName = productName;
        this.purchaseDate = purchaseDate;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.phone=phone;
        this.imageAddress=imageAddress;
    }

    public Product(String productName, String purchaseDate, int productPrice, int productQuantity,String phone,String imageAddress ) {
        this.productName = productName;
        this.purchaseDate = purchaseDate;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.phone=phone;
        this.imageAddress=imageAddress;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }
}
