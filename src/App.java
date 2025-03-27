import service.AuthService;
import userinterface.Auth;
import userinterface.CashierMenu;
import java.util.Scanner;
import model.User;

public class App {
    public static void main(String[] args) {
        AuthService userService = new AuthService();
        Auth auth = new Auth(userService);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearScreen();
            
            System.out.println("\n==== User System ====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            if (choice == 1) {
                auth.register(scanner);
            } 
            else if (choice == 2) {
                User user = auth.login(scanner);
                if (user != null) { 
                    if (user.getRole().equalsIgnoreCase("cashier")) {
                        CashierMenu cashierMenu = new CashierMenu(scanner);
                        cashierMenu.show(); 
                    } else if (user.getRole().equalsIgnoreCase("owner")) {
                        System.out.println("🏢 Welcome to the Owner Menu!");
                    } else {
                        System.out.println("🛍️ Welcome to the Customer Menu!");
                    }
                } 
            } 
            else if (choice == 0) {
                System.out.println("Exiting...");
                break;
            } 
            else {
                System.out.println("❌ Invalid choice.");
            }
        }

        scanner.close();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}
