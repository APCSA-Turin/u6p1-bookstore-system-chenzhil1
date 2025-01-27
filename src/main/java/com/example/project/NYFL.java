package com.example.project;
import java.util.Scanner;
public class NYFL {

    //CHEN ZHI LIN
    //Instance variables
    BookStore nyfl = new BookStore();
    Scanner scan = new Scanner(System.in);
    int selection = -1; //Initialize selection to -1 indicating nothing was executed
    int prevISBN = 0; //ISBN was initialized as 0, every book will have an ISBN 1 greater than previous by order created
    BookStore tempStorage = new BookStore(); 
    User[] students = nyfl.getUsers(); //Used to reduce redundancy

    //Empty instructor because no outside parameter input needed
    NYFL() {}

    //Starts the program
    public void run() {
        IdGenerate.reset(); //Reset User ID
        while(selection != 0) {  //When selection eg user input is zero quit the program
            for(int i = 0; i < 18; i ++) { //Format the interface
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
            //Main menu selection
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
            selection = scan.nextInt(); //PRECONDITION: Only accept numbers or throw error
            scan.nextLine();
            if(selection == 1) { //Go to selection when check input equals
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
            }  
          }

        
    }

    private void addBook() { //Selection 1
        System.out.println("You are currently adding a Book (Enter 0 to exit)");
        System.out.print("Please enter the title of the Book: ");
        String title = scan.nextLine();
        if(title.equals("0")) { //Exit module when user enters 0
            selection = 0;
        }
        if(selection != 0) {
            System.out.print("Please enter the author of the Book: "); //Obtain Book info from user input
            String author = scan.nextLine();
            System.out.print("Please enter the Year Published of the Book: ");
            int yearPublished = scan.nextInt();  //PRECONDITION: Only accept numbers or throw error
            scan.nextLine();
            String isbn = "" + (prevISBN + 1);
            prevISBN ++;
            System.out.print("Please enter the Quantity of the Book: ");
            int quantity = scan.nextInt(); //PRECONDITION: Only accept numbers or throw error
            scan.nextLine();
            Book newBook = new Book(title, author, yearPublished, isbn, quantity); //Add Book to the List
            nyfl.addBook(newBook); 
            System.out.println("Successfully added a Book named \"" + title + "\""); 
        }
        selection = -1; //Reset selection to -1 as nothing is executed afterward
        System.out.println("Redirecting back to Main Menu...");
        System.out.println();
    }

    public void updateQuantity() {
        selection = -1; //Reset selection before begin
        Book toUpdate = null; //Book to update is not clear
        while(selection != 0 && nyfl.getBooks().length > 0) { //Check if user didn't enter 0 and thhere is books in library
            System.out.print("Please enter the ISBN of the book (0 to Exit): ");
            String enteredISBN = scan.nextLine();
            if(enteredISBN.equals("0")) { 
                selection = -2; //Indicate user quits the module
                break;
            }
            for(int i = 0; i < nyfl.getBooks().length; i ++) {
                Book[] current = nyfl.getBooks(); //To allow easy access to indexes
                if(current[i].getIsbn().equals(enteredISBN)) { //Find target Book with same ISBN
                    toUpdate = current[i];
                    selection = 0; //Indicate module executed
                    System.out.println();
                    break;
                }
            }
            if(selection != 0) { //Check if module executed
                System.out.println("Book not found, please try again.");
            }
        }
        if(selection != -2) { 
            selection = -1; //Reset to not executed if the user didn't quit and above is exeucted successfully
        }
            int updateQuantity = 0; 
            while(updateQuantity == 0 && nyfl.getBooks().length > 0 && selection == -1) {
                System.out.println("1. Add Quantity");
                System.out.println("2. Remove Quantity");
                System.out.print("Book Found! Choose your action:");
                selection = scan.nextInt(); //PRECONDITION: Only accept numbers or throw error
                scan.nextLine();
                if(selection == 1) {
                    toUpdate.setQuantity(toUpdate.getQuantity() + 1); //Add quantity bt 1
                    System.out.println("Successfully updated the Book Quantity of \"" + toUpdate.getTitle() + "\"");
                    updateQuantity = 1; //Indicate successfully executed and book +1 quantity

                }
                else if(selection == 2) { 
                    System.out.println("Successfully updated the Book Quantity of \"" + toUpdate.getTitle() + "\"");
                    nyfl.removeBook(toUpdate); //Remove book based on rule described in BookStore
                    updateQuantity = -1; //Indicate successfully executed and book -1 quantity
                }

            }
            selection = -1; //Reset selection to -1 as nothing is executed afterward
            System.out.println("Returning to Main Menu...");
            System.out.println();
    }

    public Book searchBook() {
        selection = -1; //Reset selection before begin
        Book book = null; //Book is currently unclear
        while(nyfl.getBooks().length > 0 && selection != 0) {
            System.out.println("Please enter the full title or ISBN to Search for a book (0 to Exit): ");
            String search  = scan.nextLine();
            if(search.equals("0")) { //Quits the module if user entered 0 
                break;
            }
            Book[] library = nyfl.getBooks(); //To allow easy access to indexes
            for(int i = 0; i < library.length; i ++) { //Loop over and check if Book with same name or ISBN exist
                if(library[i].getTitle().equalsIgnoreCase(search) || library[i].getIsbn().equalsIgnoreCase(search)) {
                    System.out.println("Book found!");
                    bookInfo(library[i]); //Print book info if found
                    System.out.print("Press enter to continue");
                    String wait = scan.nextLine(); //Allow user reading, enter to continue
                    System.out.println();
                    selection = 0; //Indicate module executed
                    book = library[i]; //Allow the found Book to be returned for other functionality below
                    break;

                }
            }
            if(selection != 0) { //IF module not executed loop and reattempt
                System.out.println("Book not found, please try again");
                
            }
        
        }
    selection = -1; //Reset selection to -1 as nothing is executed afterward
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
        selection = -1; //Rrset selection to -1 as nothing is executed afterward
        System.out.println("Returning to Main Menu...");
        System.out.println();
    }

    public void displayAllStudents() {
        System.out.println("Below are all currently registered Students: ");
        System.out.println();
        for(User student : nyfl.getUsers()) { //Prints info of all existing students
            if(student != null) {
                studentInfo(student);
                selection = 0; //Indicate module executed
                System.out.println();
            }
        }
        if(selection == 0) { 
            System.out.println("Press Enter to continue"); 
            String wait = scan.nextLine(); //Pause to allow the user read when list of students was printed

        }
        else {
            System.out.println("There is no Registered Student so far, please add a student");

        }
        selection = -1; //Reset selection to -1 as nothing is executed afterward
        System.out.println("Returning to Main Menu...");
        System.out.println();


    }

    public void removeStudent() {
        System.out.println("You are currently removing a student (Enter 0 to quit)");
        System.out.print("Please enter the name or ID of the student: ");
        String remove = scan.nextLine();
        if(remove.equals("0")) { //Exits the module if user entered 0
            selection = 0;
        }
        User[] students = nyfl.getUsers(); //To allow easy access to indexes
        if(selection != 0) {
            for(int i = 0; i < students.length; i ++) {
                if(students[i].getName().equalsIgnoreCase(remove) || students[i].getId().equalsIgnoreCase(remove)) {
                    selection = 0; //Indicate module executed when student was found with name or ID
                    System.out.println("Successfully removed student with name: " + students[i].getName());
                    students[i] = null; //removes the user and empties this slot
                    break;
                }
        }
        }
        if(selection != 0) {
            System.out.println("A Student with this Name or ID cannot be found");
        }
        selection = -1; //Reset selection to -1 as nothing is executed afterward
        System.out.println("Returning to Main Menu...");
        System.out.println();
    }

    public void checkOut() {
        Book borrow = null; // The borrow book is currently unclear
        System.out.println("You are currently checking out a book");
        if (nyfl.getBooks().length > 0 && isThereStudent()) { // Check if there are students and books in the library
            System.out.print("Please enter your Name or ID (0 to quit): ");
            String idName = scan.nextLine();
            if (!idName.equals("0")) {
                Book[] studentBooks = null;
                for (int i = 0; i < students.length; i++) { // Loop to find target student with the same name or ID
                    if (students[i] != null) {
                        if (students[i].getName().equalsIgnoreCase(idName) || students[i].getId().equalsIgnoreCase(idName)) {
                            borrow = searchBook(); // Use previous module to aid searching Book and borrow that Book
                            studentBooks = students[i].getBook();
                            break;
                        }
                        if (borrow != null && studentBooks != null) { // Check if borrow was successful, e.g., Book found
                            for (int j = 0; j < studentBooks.length; j++) {
                                if (studentBooks[j] != null) { // Corrected from studentBooks[i]
                                    if (studentBooks[j].getIsbn().equals(borrow.getIsbn())) { // Check if student has the Book
                                        selection = 0; // Indicates that the student already has the Book
                                        break; // Exit loop when found
                                    }
                                }
                            }
                        }
                        if (selection != 0) { // If student doesn't have the Book, then allow borrow
                            tempStorage.setBooks(students[i].getBook()); // Set a virtual library and Book list to store student Books
                            tempStorage.addBook(borrow); // Add the Book to virtual library
                            students[i].setBooks(tempStorage.getBooks()); // Set student Books to virtual library to update the borrowed Book
                            System.out.println("Successfully borrowed a book with title: " + borrow.getTitle());
                            nyfl.removeBook(borrow); // Remove a copy of Book from the library
                            break; // Exit loop after successful borrow
                        } else {
                            System.out.println("You have already borrowed this book! Borrow another one");
                            break; // Exit loop as book already borrowed
                        }
                    } else {
                        continue; // Continue to the next student if current one is null
                    }
                }
            }
        } else if (nyfl.getBooks().length > 0 && !isThereStudent()) { // No Student
            System.out.println("There is no Registered Student yet");
            System.out.println("Please Register first before borrowing.");
            System.out.println();
        } else if (nyfl.getBooks().length <= 0 && isThereStudent()) { // No Book
            System.out.println("There is no book in the library");
            System.out.println("Please Add a book first before borrowing.");
            System.out.println();
        } else { // Neither
            System.out.println("There is no Registered Student and no book in library yet");
            System.out.println("Please Add a Book and Register first before borrowing.");
            System.out.println();
        }
        selection = -1; // Reset selection to -1 as nothing is executed afterward
    }
    
    public void checkIn() {
        if(isThereStudent()) { //Check if there is student registered
            System.out.println("You are currently checking in a book");
            while(selection != 0) { 
                System.out.print("Please enter your name or ID to continue (0 to quit:) ");
                String idName = scan.nextLine();
                for(int i = 0; i < students.length; i ++) { //Loop over all student to find target student with same ID or name
                    if(students[i] != null) {
                        if(idName.equals(students[i].getId()) || idName.equals(students[i].getName())) {
                            boolean isThereBook = false;
                            for(Book book : students[i].getBook()) {
                                if(book != null) {
                                    isThereBook = true;
                                }
                            }
                        
                            if(students[i].getBook().length > 0 && isThereBook) { //Check if student have borrowed Book
                                System.out.println("Below is the list of Books you've Borrowed:");
                                int index = 0; //Display book Index
                                for(Book book : students[i].getBook()) { //Loop over all Books and print detail 
                                    if(book != null) {
                                        index ++; 
                                        System.out.println(index + ". ");
                                        System.out.println(book.bookInfo());
                                        System.out.println();
                                    }
                                }
                                while (selection != 1) { 
                                    System.out.print("Please enter the name or ISBN of the book to return: ");
                                    String bookIdentify = scan.nextLine(); 
                                    Book[] studentBooks = students[i].getBook(); //To allow easy access to indexes
                                    for(int j = 0; j < studentBooks.length; j ++) { //Loop over all Books to find target Book with same name or ISBN
                                        if(studentBooks[j] != null) {
                                            if(bookIdentify.equals(studentBooks[j].getTitle()) || bookIdentify.equals(studentBooks[j].getIsbn())) {
                                                System.out.println("Book with name \"" + studentBooks[j].getTitle() + "\" was returned");
                                                boolean bookExist = false; //Check if Book already exist in Library
                                                for(int k = 0; k < nyfl.getBooks().length; k ++) {
                                                    Book[] libraryBooks = nyfl.getBooks();
                                                    if(studentBooks[j].getIsbn().equals(libraryBooks[k].getIsbn())) {
                                                        libraryBooks[k].setQuantity(libraryBooks[k].getQuantity() + 1); //Add Quantity if Book already exist
                                                        bookExist = true;
                                                        break;
                                                    }
                                                }
                                            
                                                if(bookExist == false) {
                                                    nyfl.addBook(studentBooks[j]); //Add Book if not already exist
                                                }
                                                studentBooks[j] = null; //Set this slot to null
                                                selection = 1; //Indicate this part of module was executed successfully
                                                break;
                                            }
                                        }
                                    }
                                if(selection != 1) {
                                    System.out.println("Book not found! Please try again");
                                }
                            }
                        }
                            selection = 0; //Indicate this module is executed successfully
    
                        } 
                    }
                    else if(idName.equals("0")) { //Exits if user entered 0
                        selection = 0;
                        break;
                    }
                }
                if(selection != 0) {
                    System.out.println("Student not found! Please try again");
                }

            }
        }
        else {
            System.out.println("There is no Registered Student and no book in library yet" );
            System.out.println("Register first before returning a book.");
        }
        selection = -1; //Reset selection to -1 as nothing is executed afterward
        System.out.println("Returning to Main Menu...");
    }

    //Helper method gettimg all info of a book
    public void bookInfo(Book book) {
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Year Published: " + book.getYearPublished());
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Quantity: " + book.getQuantity());
    }
    
    //Helper method getting student info
    public void studentInfo(User student) {
        System.out.println("Name: " + student.getName());
        System.out.println("ID: " + student.getId());
    }

    //Helper method checking if there is registered Student
    public boolean isThereStudent() {
        for(int i = 0; i < students.length; i++) {
            if(students[i] != null) {
                return true;
            }
        }
        return false;
    }
 
}
