package com.example.minimoneybox.Model;

public class ProductResponses {
    private Product Product;
    private float PlanValue;
    private float Moneybox;
    private int Id;

    public ProductResponses(com.example.minimoneybox.Model.Product product, float planValue, float moneybox, int id) {
        Product = product;
        PlanValue = planValue;
        Moneybox = moneybox;
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public com.example.minimoneybox.Model.Product getProduct() {
        return Product;
    }

    public void setProduct(com.example.minimoneybox.Model.Product product) {
        Product = product;
    }

    public float getPlanValue() {
        return PlanValue;
    }

    public void setPlanValue(float planValue) {
        PlanValue = planValue;
    }

    public float getMoneybox() {
        return Moneybox;
    }

    public void setMoneybox(float moneybox) {
        Moneybox = moneybox;
    }
}
