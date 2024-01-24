package com.control.web_market;

import java.util.ArrayList;


public class Product {
    private ArrayList<Ad> base = new ArrayList<>();
    public Product(){}
    public synchronized void add(String h, String d, String n, String c) {
        base.add(new Ad(h,d,n,c));
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
        public String cost;

        public Ad(String n, String q, String d, String c) {
            name = n;
            quantity = q;
            description = d;
            cost = c;
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
        public String getCost() {
            return cost;
        }

        public void setDescription(String text) {
            description = text;
        }
        public void setQuantity(int value) {
            quantity = Integer.toString(value);
        }
    }
}