package com.example.minimoneybox.Model;

import java.util.ArrayList;
import java.util.List;

public class Accounts {
    private ArrayList<ProductResponses> ProductResponses;

    public Accounts(ArrayList<com.example.minimoneybox.Model.ProductResponses> productResponses) {
        ProductResponses = productResponses;
    }

    public ArrayList<com.example.minimoneybox.Model.ProductResponses> getProductResponses() {
        return ProductResponses;
    }

    public void setProductResponses(ArrayList<com.example.minimoneybox.Model.ProductResponses> productResponses) {
        ProductResponses = productResponses;
    }
}
