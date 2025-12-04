import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable, Comparable<Book> {
    private static final long serialVersionUID = 1L;
    
    private String isbn;        // ISBN编号
    private String title;       // 书名
    private String author;     // 作者
    private int quantity;      // 库存数量

    public Book(String isbn, String title, String author, int quantity) {
        setIsbn(isbn);
        setTitle(title);
        setAuthor(author);
        setQuantity(quantity);
    }

    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN不能为空");
        }
        this.isbn = isbn.trim();
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("书名不能为空");
        }
        this.title = title.trim();
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("作者不能为空");
        }
        this.author = author.trim();
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("库存数量不能为负数");
        }
        this.quantity = quantity;
    }
    
    //借书操作
    public boolean borrowBook() {
        if (quantity > 0) {
            quantity--;
            return true;
        }
        return false;
    }
    
    //还书操作
    public void returnBook() {
        quantity++;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return Objects.equals(isbn, book.isbn);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
    
    @Override
    public int compareTo(Book other) {
        return this.isbn.compareTo(other.isbn);
    }
    
    @Override
    public String toString() {
        return String.format("ISBN: %-15s 书名: %-20s 作者: %-15s 库存: %d", 
                           isbn, title, author, quantity);
    }
    public String toCSV() {
        return String.format("%s,%s,%s,%d", isbn, title, author, quantity);
    }

    public static Book fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("CSV格式错误: " + csvLine);
        }
        try {
            String isbn = parts[0];
            String title = parts[1];
            String author = parts[2];
            int quantity = Integer.parseInt(parts[3]);
            return new Book(isbn, title, author, quantity);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("库存数量格式错误: " + csvLine);
        }
    }
}