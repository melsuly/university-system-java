package core;

import java.io.*;
import model.*;
import ui.*;
import authentication.AuthenticationService;

public final class Main {
	public static void main(String[] args) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			AuthenticationService authService = new AuthenticationService();

			while (true) {
				System.out.println("Тіл / Язык / Language (kz, ru, en): ");
				String language = reader.readLine();

				switch (language) {
					case "kz":
						AppState.getInstance().setCurrentLanguage(Language.KAZAKH);
						break;

					case "ru":
						AppState.getInstance().setCurrentLanguage(Language.RUSSIAN);
						break;

					case "en":
						AppState.getInstance().setCurrentLanguage(Language.ENGLISH);
						break;

					default:
						System.out.println("Тіл дұрыс емес, қайтадан көріңіз");
						System.out.println("Неправильный язык, пожалуйста, попробуйте еще раз");
						System.out.println("Invalid language, please try again.");
						break;
				}

				if (AppState.getInstance().getCurrentLanguage() != null) {
					break;
				}
			}

			System.out.println(LanguageManager.getInstance().translate("welcome_message"));

			while (true) {
				System.out.print(LanguageManager.getInstance().translate("email") + ": ");
				String email = reader.readLine();
				System.out.print(LanguageManager.getInstance().translate("password") + ": ");
				String password = reader.readLine();

				User user = authService.authenticate(email, password);

				if (user != null) {
					MainMenu menu = getMenuForUser(user);

					if (menu != null) {
						menu.showSpecificMenu();
					}

					break;
				} else {
					System.out.println(LanguageManager.getInstance().translate("invalid_credentials"));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static MainMenu getMenuForUser(User user) {
		if (user instanceof Student) {
			return new StudentMenu();
		}
		if (user instanceof Manager) {
			return new ManagerMenu();
		}
		if (user instanceof Teacher) {
			return new TeacherMenu();
		}
		return null;
	}
}
