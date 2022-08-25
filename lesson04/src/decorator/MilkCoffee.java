package decorator;

public class MilkCoffee implements Coffee {

    private Coffee coffee;

    public MilkCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public int getCost() {
        return coffee.getCost() + 2;
    }

    public String getDescription() {
        return coffee.getDescription() + ", milk";
    }
}
