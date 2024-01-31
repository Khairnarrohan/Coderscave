import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class User {
    private String name;
    private int userId;

    public User(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }
}

class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(String title, String author) {
        Book book = new Book(title, author);
        books.add(book);
    }

    public void addUser(String name, int userId) {
        User user = new User(name, userId);
        users.add(user);
    }

    public void displayBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    public void displayUsers() {
        System.out.println("Registered Users:");
        for (User user : users) {
            System.out.println(user.getUserId() + ": " + user.getName());
        }
    }

    public void borrowBook(int userId, String bookTitle) {
        User user = findUserById(userId);
        Book bookToBorrow = findBookByTitle(bookTitle);

        if (user != null && bookToBorrow != null) {
            if (bookToBorrow.isAvailable()) {
                bookToBorrow.setAvailable(false);
                System.out.println(user.getName() + " has borrowed " + bookToBorrow.getTitle());
            } else {
                System.out.println("Book is not available for borrowing.");
            }
        } else {
            System.out.println("Invalid User ID or Book Title.");
        }
    }

    public void returnBook(int userId, String bookTitle) {
        User user = findUserById(userId);
        Book bookToReturn = findBookByTitle(bookTitle);

        if (user != null && bookToReturn != null) {
            if (!bookToReturn.isAvailable()) {
                bookToReturn.setAvailable(true);
                System.out.println(user.getName() + " has returned " + bookToReturn.getTitle());
            } else {
                System.out.println("Book is already available. No need to return.");
            }
        } else {
            System.out.println("Invalid User ID or Book Title.");
        }
    }

    private User findUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    private Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Display Available Books");
            System.out.println("4. Display Registered Users");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String authorName = scanner.nextLine();
                    library.addBook(bookTitle, authorName);
                    break;

                case 2:
                    System.out.print("Enter user name: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    library.addUser(userName, userId);
                    break;

                case 3:
                    library.displayBooks();
                    break;

                case 4:
                    library.displayUsers();
                    break;

                case 5:
                    System.out.print("Enter user ID: ");
                    int borrowUserId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter the title of the book to borrow: ");
                    String borrowBookTitle = scanner.nextLine();
                    library.borrowBook(borrowUserId, borrowBookTitle);
                    break;

                case 6:
                    System.out.print("Enter user ID: ");
                    int returnUserId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter the title of the book to return: ");
                    String returnBookTitle = scanner.nextLine();
                    library.returnBook(returnUserId, returnBookTitle);
                    break;

                case 7:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}