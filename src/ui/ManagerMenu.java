package ui;

import java.io.IOException;
import java.util.List;

import authentication.Session;
import core.AppState;
import model.*;

public final class ManagerMenu extends MainMenu {
	@Override
	public void showSpecificMenu() throws IOException {
		while (true) {

			System.out.println("Manager Menu:");
			System.out.println("");
			System.out.println("1. View course applications");
			System.out.println("2.  Students in the system");
			System.out.println("3.  Teachers in the system");
			System.out.println("4.  All courses in the system");

			showCommonOptions();

			String choice = reader.readLine();

			if (handleCommonChoice(choice)) {
				break;
			}

			switch (choice.trim()) {
				case "1":
					showCourseApplications();
					break;
				case "2":
					System.out.println("");
					System.out.println("Press Enter to go back");
					System.out.println("");

					reader.readLine();
					break;
				case "3":
					System.out.println("");
					System.out.println("Press Enter to go back");
					System.out.println("");

					reader.readLine();
					break;
				case "4":
					System.out.println("");
					System.out.println("Press Enter to go back");
					System.out.println("");

					reader.readLine();
					break;
			}
		}
	}

	private void showCourseApplications() throws IOException {
		Manager manager = (Manager) Session.getCurrentUser();

		List<CourseApplication> uncheckedApplications = AppState.getInstance().courseRegistrationSystem
				.getUncheckedApplications();

		if (uncheckedApplications.isEmpty()) {
			System.out.print("No applications");

			System.out.println("");
			System.out.println("Press Enter to go back");
			System.out.println("");

			reader.readLine();
		} else {
			for (int i = 0; i < uncheckedApplications.size(); i++) {
				CourseApplication application = uncheckedApplications.get(i);

				System.out.println(i + 1 + ". " + application.toString());
			}

			System.out.println("");
			System.out.print("Type number of application: ");

			while (true) {
				int index = Integer.parseInt(reader.readLine());

				if (index > uncheckedApplications.size() || index < 1) {
					System.out.println("Invalid number.");
				} else {
					CourseApplication selectedApplication = uncheckedApplications.get(index - 1);

					System.out.println(selectedApplication.toString());
					System.out.println("1 - approve");
					System.out.println("2 - reject");

					while (true) {
						System.out.print("enter decision (1/2): ");

						int decision = Integer.parseInt(reader.readLine());

						if (decision == 1) {
							selectedApplication.makeApproved();
							break;
						} else if (decision == 2) {
							selectedApplication.makeRejected();
							break;
						} else {
							System.out.println("Invalid option.");
						}
					}

					break;
				}
			}
		}
	}
}
