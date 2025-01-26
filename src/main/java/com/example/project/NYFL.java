package com.example.project;
import java.util.Scanner;
public class NYFL {

    BookStore nyfl = new BookStore();
    Scanner scan = new Scanner(System.in);
    int selection = -1;
    int prevISBN = 0;
    BookStore tempStorage = new BookStore();
    User[] students = nyfl.getUsers();

    NYFL() {}

    public void run() {
        IdGenerate.reset();
        while(selection != 0) {
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
                displayAllBooks();
            }
            else if(selection == 5) {
                registerStudent();
            }
            else if(selection == 6) {
                displayAllStudents();
            }
            else if(selection == 7) {
                removeStudent();
            }
            else if(selection == 8) {
                checkOut();
            }
            else if(selection == 9) {
                checkIn();
            }        }
        
    }

    private void addBook() {
        System.out.println("You are currently adding a Book (Enter 0 to exit)");
        System.out.print("Please enter the title of the Book: ");
        String title = scan.nextLine();
        if(title.equals("0")) {
            selection = 0;
        }
        if(selection != 0) {
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
        }
        selection = -1;
        System.out.println("Redirecting back to Main Menu...");
        System.out.println();
    }

    public void updateQuantity() {
        selection = -1;
        Book toUpdate = null;
        while(selection != 0 && nyfl.getBooks().length > 0) {
            System.out.print("Please enter the ISBN of the book (0 to Exit): ");
            String enteredISBN = scan.nextLine();
            if(enteredISBN.equals("0")) {
                selection = -2;
                break;
            }
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
        if(selection != -2) {
            selection = -1;
        }
            int updateQuantity = 0;
            while(updateQuantity == 0 && nyfl.getBooks().length > 0 && selection == -1) {
                System.out.println("1. Add Quantity");
                System.out.println("2. Remove Quantity");
                System.out.print("Book Found! Choose your action:");
                selection = scan.nextInt();
                scan.nextLine();
                if(selection == 1) {
                    toUpdate.setQuantity(toUpdate.getQuantity() + 1); 
                    System.out.println("Successfully updated the Book Quantity of \"" + toUpdate.getTitle() + "\"");

                }
                else if(selection == 2) {
                    System.out.println("Successfully updated the Book Quantity of \"" + toUpdate.getTitle() + "\"");
                    nyfl.removeBook(toUpdate);
                    updateQuantity = -1;
                }

            }
            selection = -1;
            System.out.println("Returning to Main Menu...");
            System.out.println();
    }

    public Book searchBook() {
        Book book = null;
        while(nyfl.getBooks().length > 0 && selection != 0) {
            System.out.println("Please enter the full title or ISBN to Search for a book (0 to Exit): ");
            String search  = scan.nextLine();
            if(search.equals("0")) {
                break;
            }
            Book[] library = nyfl.getBooks();
            for(int i = 0; i < library.length; i ++) {
                if(library[i].getTitle().equalsIgnoreCase(search) || library[i].getIsbn().equalsIgnoreCase(search)) {
                    System.out.println("Book found!");
                    bookInfo(library[i]);
                    System.out.print("Press enter to continue");
                    String wait = scan.nextLine();
                    System.out.println();
                    selection = 0;
                    book = library[i];
                    break;

                }
            }
            if(selection != 0) {
                System.out.println("Book not found, please try again");
                
            }
        
        }
    selection = -1;
    return book;
    }

    public void displayAllBooks() {
        System.out.println("Below are all Books currently in the library: ");
        System.out.println();
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
        System.out.println("You are currently adding a Student (Enter 0 to quit)");
        User[] users = nyfl.getUsers();
        for(int i = 0; i < nyfl.getUsers().length; i ++) {
            if(users[i] == null) {
                System.out.print("Please enter the Student Name: ");
                String name = scan.nextLine();
                if(name.equals("0")) {
                    selection = 0;
                    break;
                }
                IdGenerate.generateID();
                String id = IdGenerate.getCurrentId();
                users[i] = new User(name, id);
                selection = 0;
                System.out.println("Successfully added a Student named " + name);
                break;
            }

        }
        if(selection != 0) {
            System.out.println("Unable to add a new Student because nos pace left");
        }
        selection = -1;
        System.out.println("Returning to Main Menu...");
        System.out.println();
    }

    public void displayAllStudents() {
        System.out.println("Below are all currently registered Students: ");
        System.out.println();
        for(User student : nyfl.getUsers()) {
            if(student != null) {
                studentInfo(student);
                selection = 0;
                System.out.println();
            }
        }
        if(selection == 0) {
            System.out.println("Press Enter to continue");
            String wait = scan.nextLine();

        }
        else {
            System.out.println("There is no Registered Student so far, please add a student");

        }
        selection = -1;
        System.out.println("Returning to Main Menu...");
        System.out.println();


    }

    public void removeStudent() {
        System.out.println("You are currently removing a student (Enter 0 to quit)");
        System.out.print("Please enter the name or ID of the student: ");
        String remove = scan.nextLine();
        if(remove.equals("0")) {
            selection = 0;
        }
        User[] students = nyfl.getUsers();
        for(int i = 0; i < students.length; i ++) {
            if(students[i].getName().equalsIgnoreCase(remove) || students[i].getId().equalsIgnoreCase(remove)) {
                selection = 0;
                System.out.println("Successfully removed student with name: " + students[i].getName());
                students[i] = null;
                break;
            }
        }
        if(selection != 0) {
            System.out.println("A Student with this Name or ID cannot be found");
        }
        selection = -1;
        System.out.println("Returning to Main Menu...");
        System.out.println();
    }

    public void checkOut() {
        Book borrow = null;
        System.out.println("You are currently checking out a book");
        if(nyfl.getBooks().length > 0 && isThereStudent()) {
            System.out.print("Please enter your Name or ID (0 to quit):");
            String idName = scan.nextLine();
            for(int i = 0; i < students.length; i ++) {
                if(students[i].getName().equalsIgnoreCase(idName) || students[i].getId().equalsIgnoreCase(idName)) {
                    borrow = searchBook();
                    Book[] studentBooks = students[i].getBook();
                    if(borrow != null) {
                        for(int j = 0; j < studentBooks.length; j ++) {
                            if(studentBooks.length > 0) {
                                if(studentBooks[i].getIsbn().equals(borrow.getIsbn())) {
                                    selection = 0;
                                }
                            }
                        }
                        if(selection != 0) {
                            tempStorage.setBooks(students[i].getBook());
                            tempStorage.addBook(borrow);
                            students[i].setBooks(tempStorage.getBooks());
                            System.out.println("Successfully borrowed a book with title: " + borrow.getTitle());
                            nyfl.removeBook(borrow);  
                            break;
                        }   
                        else {
                            System.out.println("You have already borrowed this book! Borrow another one");
                            break;

                        }                                           
                    }
                    else {
                        break;
                    }
                }
                else {
                    System.out.println("Student not found, please try again");
                    break;

                }
            }
        }
        else if(nyfl.getBooks().length > 0 && !isThereStudent()){
            System.out.println("There is no Registered Student yet" );
            System.out.println("Please Register first before borrowing.");
            System.out.println();

        }
        else if(nyfl.getBooks().length <= 0 && isThereStudent()) {
            System.out.println("There is no book in the library" );
            System.out.println("Please Add a book first before borrowing.");
            System.out.println();

        }
        else {
            System.out.println("There is no Registered Student and no book in library yet" );
            System.out.println("Please Add a Book and Register first before borrowing.");
            System.out.println();

        }
    }

    public void checkIn() {
        if(isThereStudent()) {
            System.out.println("You are currently checking in a book");
            while(selection != 0) {
                System.out.print("Please enter your name or ID to continue (0 to quit:) ");
                String idName = scan.nextLine();
                for(int i = 0; i < students.length; i ++) {
                    if(idName.equals(students[i].getId()) || idName.equals(students[i].getName())) {
                        if(students[i].getBook().length > 0) {
                            System.out.println("Below is the list of Books you've Borrowed:");
                            int index = 0;
                            for(Book book : students[i].getBook()) {
                                index ++;
                                System.out.println(index + ". ");
                                System.out.println(book.bookInfo());
                                System.out.println();
                            }
                            while (selection != 1) {
                                System.out.print("Please enter the name or ISBN of the book to return");
                                String bookIdentify = scan.nextLine();
                                Book[] studentBooks = students[i].getBook();
                                for(int j = 0; j < studentBooks.length; j ++) {
                                    if(bookIdentify.equals(studentBooks[j].getTitle()) || bookIdentify.equals(studentBooks[j].getIsbn())) {
                                        System.out.println("Book with name \"" + studentBooks[j].getTitle() + "\" was returned");
                                        boolean bookExist = false;
                                        for(int k = 0; k < nyfl.getBooks().length; k ++) {
                                            Book[] libraryBooks = nyfl.getBooks();
                                            if(studentBooks[j].getIsbn().equals(libraryBooks[k].getIsbn())) {
                                                libraryBooks[k].setQuantity(libraryBooks[k].getQuantity() + 1);
                                                bookExist = true;
                                                break;
                                            }
                                        }
                                        if(bookExist == false) {
                                            nyfl.addBook(studentBooks[j]);
                                        }
                                        studentBooks[j] = null;
                                        selection = 1;
                                        break;
                                    }
                                }
                                if(selection != 1) {
                                    System.out.println("Book not found! Please try again");
                                }
                            }
                            selection = 0;
    
                        } 
                    }
                    else if(idName.equals("0")) {
                        selection = 0;
                        break;
                    }
                }
                if(selection != 0) {
                    System.out.println("Student not found! Please try again");
                }

            }
            selection = -1;
        }
        else {
            System.out.println("There is no Registered Student and no book in library yet" );
            System.out.println("egister first before returning a book.");
        }
        
    }

    public void bookInfo(Book book) {
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Year Published: " + book.getYearPublished());
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Quantity: " + book.getQuantity());
    }
    
    public void studentInfo(User student) {
        System.out.println("Name" + student.getName());
        System.out.println("ID: " + student.getId());
    }

    public boolean isThereStudent() {
        for(int i = 0; i < students.length; i++) {
            if(students[i] != null) {
                return true;
            }
        }
        return false;
    }
 
}
