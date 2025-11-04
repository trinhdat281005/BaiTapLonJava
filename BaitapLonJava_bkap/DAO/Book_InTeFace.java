package BaitapLonJava_bkap.DAO;

import BaitapLonJava_bkap.entities.Book;

import java.util.List;

public interface Book_InTeFace {
    public Boolean InsertBook(Book bk );
    public List<Book> getShow();
    List<Book>  getBooksByCategoryId(int Caterory_id);
    List<Book> getBooksByMinPrice(double minPrice);
    List<Book> getBooksByPriceRange(double min, double max);


    List<Book> getBooksSortedByTitle();

    public Boolean UpdateBook(Book bk);

    public Book findById(String id);

}
