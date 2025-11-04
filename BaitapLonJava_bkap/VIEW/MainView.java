package BaitapLonJava_bkap.VIEW;
import java.util.Scanner;
public class MainView {

    private final Scanner sc = new Scanner(System.in);
    private final CateroryView categoryView = new CateroryView();
    private final BookView bookView = new BookView();

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n===== MENU CHÍNH =====");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sách");
            System.out.println("3. Thoát chương trình");
            System.out.print("Chọn: ");

            while (!sc.hasNextInt()) {
                System.out.println("Vui lòng nhập số!");
                sc.next(); // clear input
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> categoryView.menu();
                case 2 -> bookView.menu();
                case 3 -> System.out.println("Đã thoát chương trình!");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 3);
    }

    public static void main(String[] args) {
        MainView app = new MainView();
        app.showMenu();
    }
}
