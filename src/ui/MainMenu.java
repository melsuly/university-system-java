package ui;

import java.io.*;

import authentication.Session;
import core.Main;

public abstract class MainMenu {
    protected static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    protected void showCommonOptions() throws IOException {
        System.out.println();
        System.out.println("---");
        System.out.println("exit - " + LanguageManager.getInstance().translate("logout"));
        System.out.println();
    }

    public boolean handleCommonChoice(String choice) throws IOException {
        switch (choice) {
            case "exit":
                logout();
                return true;
            default:
                return false;
        }
    }

    public abstract void showSpecificMenu() throws IOException;

    protected void logout() throws IOException {
        System.out.println("Logged out successfully.");
        Session.removeCurrentUser();
        Main.main(new String[] {});
    }
}
