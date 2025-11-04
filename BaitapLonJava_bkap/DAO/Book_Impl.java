package BaitapLonJava_bkap.DAO;

import BaitapLonJava_bkap.Util.DBConnection;
import BaitapLonJava_bkap.entities.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Book_Impl implements Book_InTeFace {


    public boolean existsById(String id) {
        String sql = "SELECT COUNT(*) FROM Book WHERE id = ?";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(" Lỗi kiểm tra ID sách: " + e.getMessage());
        }
        return false;
    }


    public boolean existsByTitle(String title) {
        String sql = "SELECT COUNT(*) FROM Book WHERE title = ?";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(" Lỗi kiểm tra tiêu đề sách: " + e.getMessage());
        }
        return false;
    }


    @Override
    public Boolean InsertBook(Book bk) {
        if (existsById(bk.getId())) {
            System.out.println("ID " + bk.getId() + " đã tồn tại trong hệ thống!");
            return false;
        }
        if (existsByTitle(bk.getTitle())) {
            System.out.println("Tên sách " + bk.getTitle() + "đã tồn tại!");
            return false;
        }

        String sql = "INSERT INTO Book (id, title, status, price, description, author, category_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, bk.getId());
            stm.setString(2, bk.getTitle());
            stm.setBoolean(3, bk.getStatus());
            stm.setDouble(4, bk.getPrice());
            stm.setString(5, bk.getDescription());
            stm.setString(6, bk.getAuthor());
            stm.setInt(7, bk.getCategory_id());

            return stm.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(" Lỗi khi thêm sách: " + e.getMessage());
            return false;
        }
    }


    @Override
    public List<Book> getShow() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM Book";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement stm = conn.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                Book bk = new Book(
                        rs.getString("id"),
                        rs.getBoolean("status"),
                        rs.getString("title"),
                        rs.getDouble("price"),
                        rs.getString("author"),
                        rs.getInt("category_id"),
                        rs.getString("description")
                );
                list.add(bk);
            }

        } catch (SQLException e) {
            System.out.println(" Lỗi khi lấy danh sách sách: " + e.getMessage());
        }
        return list;
    }


    @Override
    public List<Book> getBooksByCategoryId(int category_id) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT b.id,b.status,b.title,b.price,b.author,b.category_id,b.description " +
                "FROM Book b JOIN Category c ON b.category_id = c.id WHERE b.category_id = ?";

        try (Connection conn = DBConnection.getConnect();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1, category_id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Book book = new Book(
                        rs.getString("id"),
                        rs.getBoolean("status"),
                        rs.getString("title"),
                        rs.getDouble("price"),
                        rs.getString("author"),
                        rs.getInt("category_id"),
                        rs.getString("description")
                );
                list.add(book);
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy sách theo danh mục: " + e.getMessage());
        }
        return list;
    }


    @Override
    public List<Book> getBooksByMinPrice(double minPrice) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT b.id, b.title, b.author, b.price, b.category_id " +
                "FROM Book b JOIN Category c ON b.category_id = c.id WHERE b.price >= ?";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setDouble(1, minPrice);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("category_id")
                );
                list.add(book);
            }
        } catch (SQLException e) {
            System.out.println(" Lỗi khi lọc sách theo giá: " + e.getMessage());
        }
        return list;
    }


    @Override
    public List<Book> getBooksByPriceRange(double min, double max) {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT b.id, b.title, b.author, b.price, b.category_id " +
                "FROM Book b JOIN Category c ON b.category_id = c.id " +
                "WHERE b.price BETWEEN ? AND ? ORDER BY b.price ASC";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setDouble(1, min);
            stm.setDouble(2, max);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Book b = new Book(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("category_id")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println(" Lỗi khi lọc theo khoảng giá: " + e.getMessage());
        }
        return list;
    }


    @Override
    public List<Book> getBooksSortedByTitle() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT b.id, b.title, b.author, b.price, b.category_id " +
                "FROM Book b JOIN Category c ON b.category_id = c.id ORDER BY b.title ASC";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Book b = new Book(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getInt("category_id")
                );
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println(" Lỗi khi sắp xếp sách: " + e.getMessage());
        }
        return list;
    }


    @Override
    public Boolean UpdateBook(Book bk) {
        String sql = "UPDATE Book SET title=?, author=?, price=?, description=?, category_id=?, status=? WHERE id=?";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bk.getTitle());
            ps.setString(2, bk.getAuthor());
            ps.setDouble(3, bk.getPrice());
            ps.setString(4, bk.getDescription());
            ps.setInt(5, bk.getCategory_id());
            ps.setBoolean(6, bk.getStatus());
            ps.setString(7, bk.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(" Lỗi khi cập nhật sách: " + e.getMessage());
            return false;
        }
    }


    @Override
    public Book findById(String id) {
        Book b = null;
        String sql = "SELECT * FROM Book WHERE id = ?";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                b = new Book(
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getBoolean("status"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getString("author"),
                        rs.getInt("category_id")
                );
            }
        } catch (SQLException e) {
            System.out.println(" Lỗi khi tìm sách theo ID: " + e.getMessage());
        }
        return b;
    }
}
