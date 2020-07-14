package Domain.Customer;

import Domain.Snacks.Snack;

public class SnackPurchased {
    private String name;
    private double price;
    private int quantity;
    private Snack.Size size;

    public SnackPurchased(String name, double price, int quantity, Snack.Size size) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Snack.Size getSize() { return size; }

    public double getTotal() {
        return price*quantity;
    }

    public boolean equals(SnackPurchased snackPurchased) {
        if (this.name.equals(snackPurchased.name))
            return true;
        return false;
    }
}
