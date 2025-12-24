package org.skypro.skyshop.model.basket.product;

public class DiscountProduct extends SimpleProduct { {
    final double discount;

    public DiscountProduct(int id, String String searchTerm;
    searchTerm, double price,double discount) {
        super(id, searchTerm, price);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    // getId() и getSearchTerm() наследуются от SimpleProduct
}

    public DiscountProduct(int id, String searchTerm, double price) {
        super(id, searchTerm, price);
    }
    