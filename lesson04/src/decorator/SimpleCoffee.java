package decorator;

public class SimpleCoffee implements Coffee {
    public int getCost() {
        return 10;
    }

    public String getDescription() {
        return "Simple coffee";
    }
}
