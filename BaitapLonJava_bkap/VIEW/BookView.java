package BaitapLonJava_bkap.VIEW;
import BaitapLonJava_bkap.DAO.Book_Impl;
import BaitapLonJava_bkap.DAO.Book_Impl;
import BaitapLonJava_bkap.entities.Book;

import java.util.*;
import java.io.*;
public class BookView {
    private static final Scanner sc = new Scanner(System.in);
    private static final Book_Impl bookDAO = new Book_Impl();

    public void menu() {
        int choice = 0;
        do {
            try {
                System.out.println("\n===== QUẢN LÝ SÁCH =====");
                System.out.println("1. Thêm mới sách");
                System.out.println("2. Hiển thị thông tin");
                System.out.println("3. Sắp xếp");
                System.out.println("4. Cập nhật thông tin");
                System.out.println("5. Quay lại");
                System.out.print("Chọn: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> infoMenu();
                    case 3 -> sortMenu();
                    case 4 -> updateBook();
                    case 5 -> System.out.println("Quay lại menu chính...");
                    default -> System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Vui lòng nhập số nguyên hợp lệ!");
            }
        } while (choice != 5);
    }



    private double safeDoubleInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println(" Không được để trống. Vui lòng nhập lại!");
                    continue;
                }
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }
    }

    // Nhập số int an toàn
    private int safeIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = sc.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println(" Không được để trống. Vui lòng nhập lại!");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
            }
        }
    }

    private void addBook() {

        System.out.println("== Thêm mới sách ==");

        String more = "y";
        while (more.equalsIgnoreCase("y")) {
            try {
                System.out.print("Nhập ID: ");
                String id = sc.nextLine().trim();
                if (id.isEmpty()) {
                    System.out.println(" ID không được để trống!");
                    continue; // quay lại nhập lại
                }

                System.out.print("Nhập tên sách: ");
                String title = sc.nextLine().trim();
                if (title.isEmpty()) {
                    System.out.println(" Tên sách không được để trống!");
                    continue;
                }

                System.out.print("Nhập trạng thái (1 = hoạt động, 0 = ngừng): ");
                int st;
                try {
                    st = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(" Trạng thái phải là số 0 hoặc 1");
                    continue;
                }
                boolean status = (st == 1);

                System.out.print("Nhập giá: ");
                double price;
                try {
                    price = Double.parseDouble(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(" Giá phải là số!");
                    continue;
                }

                System.out.print("Nhập mô tả: ");
                String description = sc.nextLine();

                System.out.print("Nhập tác giả: ");
                String author = sc.nextLine();

                System.out.print("Nhập ID danh mục: ");
                int category_Id;
                try {
                    category_Id = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println(" ID danh mục phải là số!");
                    continue;
                }

                Book b = new Book(id, title, status, price, description, author, category_Id);
                if (bookDAO.InsertBook(b)) {
                    System.out.println(" Thêm sách thành công!");
                } else {
                    System.out.println(" Thêm thất bại!");
                }

            } catch (Exception e) {
                System.out.println("️ Lỗi nhập dữ liệu: " + e.getMessage());
            }

            // Luôn an toàn khi hỏi người dùng có nhập tiếp không
            System.out.print("Bạn có muốn thêm tiếp? (y/n): ");
            more = sc.nextLine();
            if (more == null || more.trim().isEmpty()) more = "n"; // tránh NullPointer
        }
    }


    // ================== 2. Hiển thị thông tin ==================
    private void infoMenu() {
        int choice;
        do {
            System.out.println("= MENU HIỂN THỊ THÔNG TIN ==");
            System.out.println("1. Hiển thị sách theo danh mục");
            System.out.println("2. Hiển thị sách theo ID");
            System.out.println("3. Hiển thị sách theo giá min và tác giả");
            System.out.println("4. Quay lại");
            System.out.print("Chọn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> getShow();
                case 2 -> showByCategory();
                case 3 -> showByMinPrice();
                case 4 -> System.out.println("Quay lại menu chính...");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 4);
    }

    private void showByCategory() {
        sc.nextLine();
        System.out.print("Nhập ID danh mục: ");
        int catId = sc.nextInt();
        List<Book> list = bookDAO.getBooksByCategoryId(catId);
        if (list.isEmpty()) {
            System.out.println("Không có sách nào trong danh mục này.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private void showByMinPrice() {
        sc.nextLine();
        System.out.print("Nhập giá tối thiểu: ");
        double minPrice = sc.nextDouble();
        List<Book> list = bookDAO.getBooksByMinPrice(minPrice);

        if (list.isEmpty()) {
            System.out.println("Không có sách nào trên mức giá này.");
        } else {
            list.forEach(System.out::println);

            System.out.print("Bạn có muốn xuất ra file txt không? (y/n): ");
            sc.nextLine();
            String ans = sc.nextLine();
            if (ans.equalsIgnoreCase("y")) exportToTxt(list);
        }
    }

    private void exportToTxt(List<Book> list) {
        try (PrintWriter writer = new PrintWriter("books_output.txt")) {
            for (Book b : list) {
                writer.println(b);
            }
            System.out.println("Xuất file thành công: books_output.txt");
        } catch (IOException e) {
            System.out.println(" Lỗi ghi file: " + e.getMessage());
        }
    }

    // ================== 3. Sắp xếp ==================
    private void sortMenu() {
        int choice;
        do {
            System.out.println("== MENU SẮP XẾP ==");
            System.out.println("1. Sắp xếp theo khoảng giá");
            System.out.println("2. Sắp xếp theo tên tăng dần");
            System.out.println("3. Quay lại");
            System.out.print("Chọn: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> getBooksByPriceRange();
                case 2 -> getBooksSortedByTitle();
                case 3 -> System.out.println("Quay lại menu chính...");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 3);
    }

    private void getBooksByPriceRange() {
        sc.nextLine();
        System.out.print("Nhập giá thấp nhất: ");
        double min = sc.nextDouble();
        System.out.print("Nhập giá cao nhất: ");
        double max = sc.nextDouble();
        List<Book> list = bookDAO.getBooksByPriceRange(min, max);
        list.forEach(System.out::println);
    }

    private void getBooksSortedByTitle() {
        List<Book> list = bookDAO.getBooksSortedByTitle();
        list.forEach(System.out::println);
    }
private void getShow(){
        List<Book> list = bookDAO.getShow();
    list.forEach(System.out::println);
}
    // ================== 4. Cập nhật ==================
    private void updateBook() {

        System.out.print("Nhập ID sách cần cập nhật: ");
        String id = sc.nextLine().trim();
        Book b = bookDAO.findById(id);
        if (b == null) {
            System.out.println(" Không tìm thấy sách!");
            return;
        }

        try {
            System.out.println("Nhập thông tin mới ");
            System.out.print("Tên mới: ");
            String newName = sc.nextLine();
            if (!newName.isEmpty()) b.setTitle(newName);

            System.out.print("Giá mới: ");
            String newPrice = sc.nextLine();
            if (!newPrice.isEmpty()) b.setPrice(Double.parseDouble(newPrice));

            if (bookDAO.UpdateBook(b)) {
                System.out.println(" Cập nhật thành công!");
            } else {
                System.out.println("Cập nhật thất bại!");
            }
        } catch (NumberFormatException e) {
            System.out.println(" Giá phải là số hợp lệ!");
        } catch (Exception e) {
            System.out.println("Lỗi cập nhật: " + e.getMessage());
        }
    }

}
