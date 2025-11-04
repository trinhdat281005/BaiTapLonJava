package BaitapLonJava_bkap.entities;

public class Book {
        private  String id;
        private Boolean status;
        private String title;
        private  double price;
    private String Author;
    private  int category_id;
    private String description;


    public Book(String id) {
        this.id = id;
    }

    public Book(String id, String title,String author ,double price, int category_id) {
        this.id = id;
        this.title = title;
        Author = author;
        this.price = price;

        this.category_id = category_id;
    }

    public Book(String id, Boolean status, String title, double price, String Author, int category_id,String description) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.price = price;
        this.Author = Author;
        this.category_id = category_id;
        this.description = description;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Book(String id , String title, Boolean status, double price, String description, String Author, int category_id) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.price = price;
        this.description = description;
        this.Author = Author;
        this.category_id = category_id;


    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", Author='" + Author + '\'' +
                ", Caterory_id='" + category_id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
