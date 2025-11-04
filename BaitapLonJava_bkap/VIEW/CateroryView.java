package BaitapLonJava_bkap.VIEW;

import BaitapLonJava_bkap.DAO.Caterory_impl;
import BaitapLonJava_bkap.entities.Caterory;

import java.util.List;
import java.util.Scanner;

public class CateroryView {
    private final Scanner sc = new Scanner(System.in);
    private final Caterory_impl dao = new Caterory_impl();

    public void menu() {
        while (true) {
            try {
                System.out.println("===== QUẢN LÝ DANH MỤC =====");
                System.out.println("1. Hiển thị danh sách danh mục");
                System.out.println("2. Thêm danh mục");
                System.out.println("3. Cập nhật danh mục");
                System.out.println("4. Xóa danh mục");
                System.out.println("5. Tìm kiếm danh mục");
                System.out.println("0. Quay lại / Thoát");
                System.out.print("Chọn chức năng: ");

                String choice = sc.nextLine().trim();

                switch (choice) {
                    case "1" -> showAll();
                    case "2" -> insert();
                    case "3" -> update();
                    case "4" -> delete();
                    case "5" -> search();
                    case "0" -> {
                        System.out.println("Quay lại menu chính!");
                        return;
                    }
                    default -> System.out.println(" Lựa chọn không hợp lệ!");
                }

            } catch (Exception e) {
                System.out.println("Lỗi không xác định: " + e.getMessage());
            }
        }
    }


    private void showAll() {
        List<Caterory> list = dao.getall();
        System.out.println(" DANH SÁCH DANH MỤC ==");
        System.out.printf("%-10s %-20s %-10s %-10s%n", "ID", "Tên danh mục", "Trạng thái", "ParentId");
        for (Caterory c : list) {
            System.out.printf("%-10s %-20s %-10s %-10s%n",
                    c.getId(),
                    c.getName(),
                    c.getStatus(),
                    c.getParentId());
        }
    }


    private void insert() {
        try {
            System.out.println("= THÊM DANH MỤC ==");
            System.out.print("Nhập ID: ");
            String id = sc.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println(" ID không được để trống!");
                return;
            }

            System.out.print("Nhập tên danh mục: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Tên danh mục không được để trống!");
                return;
            }

            System.out.print("Trạng thái (true/false): ");
            String statusInput = sc.nextLine().trim();
            boolean status;
            if (statusInput.equalsIgnoreCase("true") || statusInput.equalsIgnoreCase("false")) {
                status = Boolean.parseBoolean(statusInput);
            } else {
                System.out.println("Vui lòng nhập true hoặc false!");
                return;
            }

            System.out.print("Nhập mã cha (parentId) hoặc để trống: ");
            String parentId = sc.nextLine().trim();
            if (parentId.isEmpty()) parentId = null;

            Caterory c = new Caterory(id, name, status, parentId);
            if (dao.InSertCaterory(c)) {
                System.out.println(" Thêm thành công!");
            } else {
                System.out.println(" Thêm thất bại!");
            }
        } catch (Exception e) {
            System.out.println(" Lỗi khi thêm danh mục: " + e.getMessage());
        }
    }

    // 3. Cập nhật danh mục
    private void update() {
        try {
            System.out.println("== CẬP NHẬT DANH MỤC ==");
            System.out.print("Nhập ID cần cập nhật: ");
            String id = sc.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println(" ID không được để trống!");
                return;
            }

            System.out.print("Nhập tên mới: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println(" Tên không được để trống!");
                return;
            }

            System.out.print("Trạng thái mới (true/false): ");
            String statusInput = sc.nextLine().trim();
            boolean status;
            if (statusInput.equalsIgnoreCase("true") || statusInput.equalsIgnoreCase("false")) {
                status = Boolean.parseBoolean(statusInput);
            } else {
                System.out.println(" Trạng thái phải là true hoặc false!");
                return;
            }

            System.out.print("Nhập parentId mới (hoặc bỏ trống): ");
            String parentId = sc.nextLine().trim();
            if (parentId.isEmpty()) parentId = null;

            Caterory c = new Caterory(id, name, status, parentId);
            if (dao.UpDateCaterory(c)) {
                System.out.println(" Cập nhật thành công!");
            } else {
                System.out.println(" Không tìm thấy danh mục cần sửa!");
            }
        } catch (Exception e) {
            System.out.println(" Lỗi khi cập nhật: " + e.getMessage());
        }
    }

    // 4. Xóa danh mục
    private void delete() {
        try {
            System.out.println("\n== XÓA DANH MỤC ==");
            System.out.print("Nhập ID cần xóa: ");
            String input = sc.nextLine().trim(); // đọc chuỗi từ người dùng

            if (input.isEmpty()) { // kiểm tra rỗng
                System.out.println(" ID không được để trống!");
                return;
            }

            int idInput;
            try {
                idInput = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(" ID phải là số nguyên hợp lệ!");
                return;
            }


            // Gọi DAO xóa
            if (dao.DeleteCaterory(idInput)) {
                System.out.println("Xóa thành công!");
            } else {
                System.out.println(" Không tìm thấy danh mục có ID = " + idInput);
            }

        } catch (Exception e) {
            System.out.println(" Lỗi không mong muốn: " + e.getMessage());
        }
    }


    // 5. Tìm kiếm danh mục theo tên
    private void search() {
        try {
            System.out.println("== TÌM KIẾM DANH MỤC ==");
            System.out.print("Nhập tên danh mục cần tìm: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Tên không được để trống!");
                return;
            }

            List<Caterory> list = dao.searchCaterory(name);
            if (list.isEmpty()) {
                System.out.println(" Không tìm thấy danh mục nào!");
            } else {
                System.out.printf("%-10s %-20s %-10s %-10s%n", "ID", "Tên danh mục", "Trạng thái", "ParentID");
                for (Caterory c : list) {
                    System.out.printf("%-10s %-20s %-10s %-10s%n",
                            c.getId(),
                            c.getName(),
                            c.getStatus(),
                            c.getParentId());
                }
            }
        } catch (Exception e) {
            System.out.println(" Lỗi khi tìm kiếm: " + e.getMessage());
        }
    }
}
