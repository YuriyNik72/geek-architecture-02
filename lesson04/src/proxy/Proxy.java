package proxy;

public class Proxy {
    public static void main(String[] args) {
        SecuredDoor door = new SecuredDoor(new LabDoor());
        door.open("invalid"); // Big no! It ain't possible.

        door.open("$ecr@t"); // Opening lab door
        door.close(); // Closing lab door
    }
}
