package Domain.Snacks;


public class HotBeverage extends Snack {
    private Size size;
    
    public HotBeverage(String name, double priceS, double priceM, double priceL) {
        super(name, priceS, priceM, priceL);
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
