package com.example.project;
import java.util.Scanner;
public class NYFL {

    BookStore nyfl = new BookStore();
    Scanner scan = new Scanner(System.in);
    int selection = -1;
    int prevISBN = 0;

    NYFL() {}

    public void run() {
        IdGenerate.reset();
;        while(selection != 0) {
            for(int i = 0; i < 18; i ++) {
                System.out.print("*");
    
            }
            System.out.print("Welcome to NYPL Library!");
            for(int i = 0; i < 18; i ++) {
                System.out.print("*");
    
            }
            System.out.println();
            for(int i = 0; i < 13; i ++) {
                System.out.print(" ");
    
            }
            System.out.print("Select from the following Options:");
            System.out.println();
    
            for(int i = 0; i < 60; i ++) {
                System.out.print("*");
    
            }
            System.out.println();
            for(int i = 0; i < 60; i ++) {
                System.out.print("-");
    
            }
            System.out.println();
            System.out.println("Press 1 to Add a new Book");
            System.out.println("Press 2 to Update the Quantity of a Book.");
            System.out.println("Press 3 to Search for a Book");
            System.out.println("Press 4 to Display All Books");
            System.out.println("Press 5 to Register a Student");
            System.out.println("Press 6 to Display All Registed Students");
            System.out.println("Press 7 to Remove a Registered Student");
            System.out.println("Press 8 to Check Out a Book");
            System.out.println("Press 9 to Check In a Book");
            System.out.println("Press 0 to Exit Application");
            selection = scan.nextInt();
            scan.nextLine();
            if(selection == 1) {
                addBook();
            }
            else if (selection == 2) {
                updateQuantity();
            }
            else if(selection == 3) {
                searchBook();
            }
            else if(selection == 4) {
                displayAll();
            }
            else if(selection == 5) {
                registerStudent();
            }
        }
    }

    private void addBook() {
        System.out.println("You are currently adding a Book");
        System.out.print("Please enter the title of the Book: ");
        String title = scan.nextLine();
        System.out.print("Please enter the author of the Book: ");
        String author = scan.nextLine();
        System.out.print("Please enter the Year Published of the Book: ");
        int yearPublished = scan.nextInt();
        scan.nextLine();
        String isbn = "" + prevISBN + 1;
        prevISBN ++;
        System.out.print("Please enter the Quantity of the Book: ");
        int quantity = scan.nextInt();
        scan.nextLine();
        Book newBook = new Book(title, author, yearPublished, isbn, quantity);
        nyfl.addBook(newBook);
        System.out.println("Successfully added a Book named \"" + title + "\"");
        System.out.println("Redirecting back to Main Menu...");
        System.out.println();
    }

    public void updateQuantity() {
        selection = -1;
        Book toUpdate = null;
        while(selection != 0 && nyfl.getBooks().length > 0) {
            System.out.print("Please enter the ISBN of the book: ");
            String enteredISBN = scan.nextLine();
            for(int i = 0; i < nyfl.getBooks().length; i ++) {
                Book[] current = nyfl.getBooks();
                if(current[i].getIsbn().equals(enteredISBN)) {
                    toUpdate = current[i];
                    selection = 0;
                    System.out.println();
                    break;
                }
            }
            if(selection != 0) {
                System.out.println("Book not found, please try again.");
            }
        }
        selection = -1;
            int updateQuantity = 0;
            while(updateQuantity == 0 && nyfl.getBooks().length > 0) {
                System.out.println("1. Add Quantity");
                System.out.println("2. Remove Quantity");
                System.out.print("Book Found! Choose your action:");
                selection = scan.nextInt();
                scan.nextLine();
                if(selection == 1) {
                    toUpdate.setQuantity(toUpdate.getQuantity() + 1); 
                    System.out.println("Successfully updated the Book Quantity of \"" + toUpdate.getTitle() + "\"");
                    System.out.println("Returning to Main Menu...");
                    System.out.println();

                }
                else if(selection == 2) {
                    System.out.println("Successfully updated the Book Quantity of \"" + toUpdate.getTitle() + "\"");
                    nyfl.removeBook(toUpdate);
                    updateQuantity = -1;
                    System.out.println("Returning to Main Menu...");
                    System.out.println();
                }
            }
    }

    public void searchBook() {
        while(nyfl.getBooks().length > 0 && selection != 0) {
            System.out.println("Please enter the full title or ISBN to Search for a book");
            String search  = scan.nextLine();
            Book[] library = nyfl.getBooks();
            for(int i = 0; i < library.length; i ++) {
                if(library[i].getTitle().equalsIgnoreCase(search) || library[i].getIsbn().equalsIgnoreCase(search)) {
                    System.out.println("Book found!");
                    bookInfo(library[i]);
                    System.out.print("Press enter to continue");
                    String wait = scan.nextLine();
                    System.out.println();
                    selection = 0;
                    break;

                }
            }
            if(selection != 0) {
                System.out.println("Book not found, please try again");
            }
        
        }
    selection = -1;
    }

    public void displayAll() {
        System.out.println("Below are all Books currently in the library: ");
        if(nyfl.getBooks().length > 0) {
            for(Book book : nyfl.getBooks()) {
                bookInfo(book);
                System.out.println();

            }
            System.out.print("Press enter to continue");
            String wait = scan.nextLine();
            System.out.println();
        }
        else {
            System.out.println("There is no Book here so far, try adding some");
            String wait = scan.nextLine();
            System.out.println();
        }

    }

    public void registerStudent() {
        selection = -1;
        System.out.println("You are currently adding a Student");
        User[] users = nyfl.getUsers();
        for(int i = 0; i < nyfl.getUsers().length; i ++) {
            if(users[i] == null) {
                System.out.print("Please enter the Student Name: ");
                String name = scan.nextLine();
                IdGenerate.generateID();
                String id = IdGenerate.getCurrentId();
                users[i] = new User(name, id);
                selection = 0;
                System.out.println("Successfully added a Student named " + name);
                System.out.println("Returning to Main Menu...");
                System.out.println();
                break;
            }

        }
        if(selection != 0) {
            System.out.println("Unable to add a new Student because nos pace left");
            System.out.println("Returning to Main Menu...");
            System.out.println();
        }
        selection = -1;
    }

    public void bookInfo(Book book) {
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Year Published: " + book.getYearPublished());
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Quantity: " + book.getQuantity());
    }
}
