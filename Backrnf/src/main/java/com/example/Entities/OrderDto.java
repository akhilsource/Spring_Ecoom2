package com.example.Entities;

public class OrderDto {

    private String order_item_ids;    // Matches "order_item_ids" column
    private long product_id;          // Matches "product_id" column
    private String product_name;      // Matches "product_name" column
    private int order_item_quantity;  // Matches "order_item_quantity" column

    // Constructor matching the fields in the query
    public OrderDto(String order_item_ids, String product_name, int order_item_quantity) {
        this.order_item_ids = order_item_ids;
        this.product_id = product_id;
        this.product_name = product_name;
        this.order_item_quantity = order_item_quantity;
    }


    // Getters and setters
    public String getOrder_item_ids() {
        return order_item_ids;
    }

    public void setOrder_item_ids(String order_item_ids) {
        this.order_item_ids = order_item_ids;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getOrder_item_quantity() {
        return order_item_quantity;
    }

    public void setOrder_item_quantity(int order_item_quantity) {
        this.order_item_quantity = order_item_quantity;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "order_item_ids='" + order_item_ids + '\'' +
                ", product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", order_item_quantity=" + order_item_quantity +
                '}';
    }
}
