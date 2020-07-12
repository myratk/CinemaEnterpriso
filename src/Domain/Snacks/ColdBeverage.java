package Domain.Snacks;

public class ColdBeverage extends Snack {
    private boolean containsIce;
    private Size size;

    public ColdBeverage(String name, double priceS, double priceM, double priceL) {
        super(name, priceS, priceM, priceL);
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setContainsIceIce(boolean ice) {
        containsIce = ice;
    }
}
