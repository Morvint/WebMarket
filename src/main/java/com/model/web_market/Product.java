package com.model.web_market;

import java.util.ArrayList;


public class Product {
    private ArrayList<Ad> base = new ArrayList<>();
    public Product(){}
    public synchronized void add(String h, String d, String n) {
        base.add(new Ad(h,d,n));
    }
    public synchronized Ad getAd(int index) {
        return base.get(index);
    }
    public int size(){
        return base.size();
    }
    public class Ad {
        public String name;
        public String quantity;
        public String description;

        public Ad(String n, String q, String d) {
            name = n;
            quantity = q;
            description = d;

        }


        public String getName() {
            return name;
        }

        public String getQuantity() {
            return quantity;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String text) {
            description = text;
        }
        public void setQuantity(int value) {
            quantity = Integer.toString(value);
        }
    }
}