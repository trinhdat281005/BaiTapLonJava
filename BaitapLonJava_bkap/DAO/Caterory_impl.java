package BaitapLonJava_bkap.DAO;

import BaitapLonJava_bkap.Util.DBConnection;
import BaitapLonJava_bkap.entities.Caterory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Caterory_impl implements Caterory_InteFace {


    @Override
    public List<Caterory> getall() {
        List<Caterory> list = new ArrayList<>();
        String sql = "SELECT * FROM Category";

        try (Connection conn = DBConnection.getConnect();
             PreparedStatement stm = conn.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Caterory c = new Caterory(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getBoolean("status"),
                        rs.getString("parentId")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh mục: " + e.getMessage());
        }
        return list;
    }
    public boolean existsById(String id) {
        String sql = "SELECT COUNT(*) FROM Category WHERE id = ?";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(" Lỗi kiểm tra ID: " + e.getMessage());
        }
        return false;
    }


    @Override
    public Boolean InSertCaterory(Caterory ca) {

        if (existsById(ca.getId())) {
            System.out.println("ID " + ca.getId() + " đã tồn tại!");
            return false;
        }

        String sql = "INSERT INTO Category (id, name, status, parentId) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, ca.getId());
            stm.setString(2, ca.getName());
            stm.setBoolean(3, ca.getStatus()); // Dùng boolean đúng kiểu
            stm.setString(4, ca.getParentId());

            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi thêm danh mục: " + e.getMessage());
            return false;
        }
    }

    //  4. Cập nhật danh mục
    @Override
    public Boolean UpDateCaterory(Caterory ca) {
        String sql = "UPDATE Category SET name=?, status=?, parentId=? WHERE id=?";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, ca.getName());
            stm.setBoolean(2, ca.getStatus());
            stm.setString(3, ca.getParentId());
            stm.setString(4, ca.getId());

            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật danh mục: " + e.getMessage());
        }
        return false;
    }


    @Override
    public Boolean DeleteCaterory(int id) {
        String sql = "DELETE FROM Category WHERE id=?";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement stm = conn.prepareStatement(sql)) {


            stm.setString(1, String.valueOf(id));

            int affected = stm.executeUpdate();
            return affected > 0;

        } catch (SQLException e) {
            System.out.println(" Lỗi xóa danh mục: " + e.getMessage());
        }
        return false;
    }

    //  6. Tìm kiếm danh mục theo tên
    @Override
    public List<Caterory> searchCaterory(String name) {
        List<Caterory> data = new ArrayList<>();
        String sql = "SELECT * FROM Category WHERE name LIKE ?";

        try (Connection conn = DBConnection.getConnect();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, "%" + name + "%");
            ResultSet re = stm.executeQuery();

            while (re.next()) {
                Caterory ca = new Caterory(
                        re.getString("id"),
                        re.getString("name"),
                        re.getBoolean("status"),
                        re.getString("parentId")
                );
                data.add(ca);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi tìm kiếm danh mục: " + e.getMessage());
        }

        return data;
    }
}
