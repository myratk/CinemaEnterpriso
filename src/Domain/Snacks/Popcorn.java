package Domain.Snacks;

public class Popcorn extends Snack {
    private Size size;

    public Popcorn(String name, double priceS, double priceM, double priceL) {
        super(name, priceS, priceM, priceL);
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
