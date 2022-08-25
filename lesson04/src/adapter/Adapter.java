package adapter;

public class Adapter {
    public static void main(String[] args) {
        Lion asianLion = new AsianLion();
        Lion africanLion = new AfricanLion();

        WildDog wildDog = new WildDog();
        Lion wildDogAdapter = new WildDogAdapter(wildDog);

        Hunter hunter = new Hunter();
        hunter.hunt(asianLion);
        hunter.hunt(africanLion);
        hunter.hunt(wildDogAdapter);
    }

}
