package bridge;

import bridge.themes.AquaTheme;
import bridge.themes.DarkTheme;
import bridge.themes.Theme;

public class Bridge {
    public static void main(String[] args) {
        Theme darkTheme = new DarkTheme();
        Theme aquaTheme = new AquaTheme();

        WebPage about = new About(darkTheme);
        WebPage careers = new Careers(aquaTheme);

        System.out.println(about.getContent()); // "About page in Dark Black";
        System.out.println(careers.getContent()); // "Careers page in Dark Black";
    }
}
