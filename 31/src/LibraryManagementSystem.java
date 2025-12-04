import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class LibraryManagementSystem {
    private List<Book> books;
    private static final String DATA_FILE = "library_data.txt";
    private Scanner scanner;
    
    public LibraryManagementSystem() {
        this.books = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        loadFromFile(); // 启动时自动加载数据
    }
    
    //添加图书
    public void addBook() {
        System.out.println("\n添加图书：");
        
        try {
            System.out.print("请输入ISBN编号: ");
            String isbn = scanner.nextLine();
            
            // 检查ISBN是否已存在
            if (findBookByIsbn(isbn) != null) {
                System.out.println("错误：该ISBN编号已存在！");
                return;
            }
            
            System.out.print("请输入书名: ");
            String title = scanner.nextLine();
            
            System.out.print("请输入作者: ");
            String author = scanner.nextLine();
            
            System.out.print("请输入库存数量: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            
            Book newBook = new Book(isbn, title, author, quantity);
            books.add(newBook);
            saveToFile(); // 保存到文件
            
            System.out.println("图书添加成功！");
            
        } catch (NumberFormatException e) {
            System.out.println("错误：库存数量必须是整数！");
        } catch (IllegalArgumentException e) {
            System.out.println("错误：" + e.getMessage());
        }
    }
    
    //查看所有图书
    public void displayAllBooks() {
        System.out.println("\n所有图书信息（按ISBN排序）：");
        
        if (books.isEmpty()) {
            System.out.println("当前没有图书信息。");
            return;
        }
        
        // 使用Comparator对图书按ISBN进行排序
        List<Book> sortedBooks = new ArrayList<>(books);
        sortedBooks.sort(Comparator.comparing(Book::getIsbn));
        
        // 使用Stream API格式化输出
        String bookList = sortedBooks.stream()
            .map(book -> String.format("  %s", book.toString()))
            .collect(Collectors.joining("\n"));
        
        System.out.println(bookList);
        System.out.printf("总计: %d 本图书\n", sortedBooks.size());
    }
    
    //借书操作
    public void borrowBook() {
        System.out.println("\n借书操作");
        
        if (books.isEmpty()) {
            System.out.println("当前没有可借阅的图书。");
            return;
        }
        
        System.out.print("请输入要借阅的图书ISBN编号: ");
        String isbn = scanner.nextLine();
        
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("错误：未找到该ISBN编号的图书！");
            return;
        }
        
        if (book.borrowBook()) {
            saveToFile(); // 保存变更到文件
            System.out.println("借书成功！当前库存: " + book.getQuantity());
        } else {
            System.out.println("借书失败：库存不足！");
        }
    }
    
    //还书操作
    public void returnBook() {
        System.out.println("\n还书操作");
        
        if (books.isEmpty()) {
            System.out.println("当前没有图书记录。");
            return;
        }
        
        System.out.print("请输入要归还的图书ISBN编号: ");
        String isbn = scanner.nextLine();
        
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            System.out.println("错误：未找到该ISBN编号的图书！");
            return;
        }
        
        book.returnBook();
        saveToFile(); // 保存变更到文件
        System.out.println("还书成功！当前库存: " + book.getQuantity());
    }
    
    //根据ISBN查找图书
    private Book findBookByIsbn(String isbn) {
        return books.stream()
                   .filter(book -> book.getIsbn().equals(isbn))
                   .findFirst()
                   .orElse(null);
    }
    
    //将图书信息保存到文件
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Book book : books) {
                writer.println(book.toCSV());
            }
            System.out.println("数据已保存到文件: " + DATA_FILE);
        } catch (IOException e) {
            System.out.println("错误：保存数据到文件失败 - " + e.getMessage());
        }
    }
    
    //从文件加载图书信息
    public void loadFromFile() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("数据文件不存在，将创建新文件。");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            books.clear();
            String line;
            int successCount = 0;
            int errorCount = 0;
            
            while ((line = reader.readLine()) != null) {
                try {
                    if (!line.trim().isEmpty()) {
                        Book book = Book.fromCSV(line);
                        books.add(book);
                        successCount++;
                    }
                } catch (Exception e) {
                    System.out.println("警告：跳过无效数据行 - " + line);
                    errorCount++;
                }
            }
            
            System.out.printf("数据加载完成：成功加载%d条记录，跳过%d条无效记录。\n", 
                            successCount, errorCount);
            
        } catch (IOException e) {
            System.out.println("错误：从文件加载数据失败 - " + e.getMessage());
        }
    }
    
    //显示系统菜单
    public void displayMenu() {
        System.out.println("\n图书管理系统：");
        System.out.println("1. 添加图书");
        System.out.println("2. 查看所有图书");
        System.out.println("3. 借书");
        System.out.println("4. 还书");
        System.out.println("5. 保存数据到文件");
        System.out.println("6. 从文件重新加载数据");
        System.out.println("0. 退出系统");
        System.out.println("");
        System.out.print("请选择操作（0-6）: ");
    }
    
    //运行系统主循环
    public void run() {
        System.out.println("欢迎使用图书管理系统！");
        
        while (true) {
            try {
                displayMenu();
                String input = scanner.nextLine();
                
                switch (input) {
                    case "1":
                        addBook();
                        break;
                    case "2":
                        displayAllBooks();
                        break;
                    case "3":
                        borrowBook();
                        break;
                    case "4":
                        returnBook();
                        break;
                    case "5":
                        saveToFile();
                        break;
                    case "6":
                        loadFromFile();
                        break;
                    case "0":
                        System.out.println("感谢使用图书管理系统，再见！");
                        return;
                    default:
                        System.out.println("无效选择，请重新输入！");
                }
                
                // 每次操作后暂停一下
                System.out.print("\n按回车键继续...");
                scanner.nextLine();
                
            } catch (Exception e) {
                System.out.println("发生错误: " + e.getMessage());
                System.out.print("按回车键继续...");
                scanner.nextLine();
            }
        }
    }
}