package com.example.justjava;

public class Order {

    private String name;
    private int quantity;
    private boolean whippedCreme;
    private boolean chocolate;
    private int total;

    public Order(int quantity, String name, boolean whippedCreme, boolean chocolate, int total){
        this.chocolate = chocolate;
        this.total = total;
        this.quantity = quantity;
        this.name = name;
        this.whippedCreme = whippedCreme;
    }

    @Override
    public String toString(){
        return   "Name : "+ name +"\n" +
                "Quantity "+ quantity+"\n" +
                "Add Whipped creme: "+whippedCreme+"\n" +
                "Add Chocolate Topping: "+chocolate+"\n" +
                "Total : "+total+"\n" +
                "Thank You!!";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isWhippedCreme() {
        return whippedCreme;
    }

    public void setWhippedCreme(boolean whippedCreme) {
        this.whippedCreme = whippedCreme;
    }

    public boolean isChocolate() {
        return chocolate;
    }

    public void setChocolate(boolean chocolate) {
        this.chocolate = chocolate;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
