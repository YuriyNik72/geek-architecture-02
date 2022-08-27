package facade;

public class Facade {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade(new Computer());
        computer.turnOn(); // Ouch! Beep beep! Loading.. Ready to be used!
        computer.turnOff(); // Bup bup buzzz! Haah! Zzzzz
    }
}
