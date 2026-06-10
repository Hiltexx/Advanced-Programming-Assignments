import java.util.*;

//  Abstract Base Class
abstract class LibraryItem {
    protected String title;
    protected int year;

    private static int count = 0;

    public LibraryItem(String title, int year) {
        this.title = title;
        this.year = year;
        count++;
    }

    public static int getCount() {
        return count;
    }

    abstract void displayInfo();
}


//  Book Class
class Book extends LibraryItem {
    private String author;

    public Book(String title, int year, String author) {
        super(title, year);
        this.author = author;
    }

    @Override
    void displayInfo() {
        System.out.println("Book: " + title + ", Year: " + year + ", Author: " + author);
    }
}


//  DVD Class
class DVD extends LibraryItem {
    private int duration;
    private String genre;

    public DVD(String title, int year, int duration, String genre) {
        super(title, year);
        this.duration = duration;
        this.genre = genre;
    }

    @Override
    void displayInfo() {
        System.out.println("DVD: " + title + ", Year: " + year +
                ", Duration: " + duration + " mins, Genre: " + genre);
    }
}


//  Main System
public class LibrarySystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<LibraryItem> items = new ArrayList<>();


    //  Preload items
    static void preloadItems() {

    //  Books
    items.add(new Book("Java Basics", 2020, "James Gosling"));
    items.add(new Book("Operating Systems", 2019, "Galvin"));
    items.add(new Book("Data Structures", 2018, "Mark Allen"));
    items.add(new Book("Computer Networks", 2021, "Andrew Tanenbaum"));
    items.add(new Book("Database Systems", 2020, "Elmasri & Navathe"));
    items.add(new Book("Artificial Intelligence", 2022, "Stuart Russell"));
    items.add(new Book("Machine Learning", 2021, "Tom Mitchell"));
    items.add(new Book("Software Engineering", 2019, "Ian Sommerville"));
    items.add(new Book("Discrete Mathematics", 2017, "Kenneth Rosen"));
    items.add(new Book("Compiler Design", 2018, "Aho & Ullman"));

    //  Educational DVDs (no movies)
    items.add(new DVD("Java Programming Tutorial", 2020, 120, "Education"));
    items.add(new DVD("Data Structures Course", 2019, 150, "Education"));
    items.add(new DVD("Operating Systems Concepts Lecture", 2018, 180, "Education"));
    items.add(new DVD("Computer Networks Workshop", 2021, 140, "Education"));
    items.add(new DVD("AI Fundamentals Series", 2022, 160, "Education"));
    items.add(new DVD("Database Management Systems Training", 2020, 130, "Education"));
}


    //  View all items (Polymorphism)
    static void viewItems() {
        if (items.isEmpty()) {
            System.out.println("No items available.");
            return;
        }

        int i = 1;
        for (LibraryItem item : items) {
            System.out.print(i++ + ". ");
            item.displayInfo();
        }
    }


    //  Add new item
    static void addItem() {
        try {
            System.out.println("\n1. Add Book");
            System.out.println("2. Add DVD");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            System.out.print("Enter title: ");
            String title = sc.nextLine();

            System.out.print("Enter year: ");
            int year = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter author: ");
                String author = sc.nextLine();
                items.add(new Book(title, year, author));
                System.out.println("Book added successfully!");

            } else if (choice == 2) {
                System.out.print("Enter duration (mins): ");
                int duration = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter genre: ");
                String genre = sc.nextLine();

                items.add(new DVD(title, year, duration, genre));
                System.out.println("DVD added successfully!");

            } else {
                System.out.println("Invalid choice.");
            }

        } catch (Exception e) {
            System.out.println("Invalid input.");
            sc.nextLine(); // reset scanner
        }
    }


    //  Main Menu
    public static void main(String[] args) {

        preloadItems();

        while (true) {
            System.out.println("\n===== LIBRARY SYSTEM =====");
            System.out.println("1. View Items");
            System.out.println("2. Add Item");
            System.out.println("3. Total Items");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewItems();
                    break;

                case 2:
                    addItem();
                    break;

                case 3:
                    System.out.println("Total Items: " + LibraryItem.getCount());
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
