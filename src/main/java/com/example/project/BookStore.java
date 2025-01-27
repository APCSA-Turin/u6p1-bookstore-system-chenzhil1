package com.example.project;

public class BookStore{

    //requires at least 2 attributes Book[] books, User[] users (initialized to an empty array of 10 max users) 
    private Book[] books = new Book[0];
    private User[] users = new User[10];
    //requires 1 empty constructor
    public BookStore() {}

    // public getUsers(){}
    public User[] getUsers() {
        return users;
    }

    // public setUsers(){}
    public void setUsers(User[] newUsers) {
        users = newUsers;
    }

    // public  getBooks(){}
    public Book[] getBooks() {
        return books;
    }

    // public void addUser(User user){} 
    public void addUser(User user) {
        for(int i = 0; i < users.length; i ++) { //Loop and check when a user is null or not exist
            if(users[i] == null) {
                users[i] = user; 
                break;
            }
        }
    }
    // public void removeUser(User user){}
    public void removeUser(User user) {
        for(int i = 0; i < users.length; i ++) { //Loop to check if user exist
            if(users[i] != null) { 
                if(users[i].getId().equals(user.getId())) { //Check if user is the target by comparing ID
                    users[i] = null;
                }
            }
        }
        consolidateUsers();

    }
    // public void consolidateUsers(){}
    public void consolidateUsers() {
        int slot = 0; 
        for(int i = 0; i < users.length; i ++) { //Loop to check if user exists
            if(users[i] != null) {
                users[slot] = users[i]; //Set user to first avaliable index.
                if(i != slot) {
                    users[i] = null;
                }
                slot ++; //Add 1 indicating the index was occupied.
            }
        }
    }
    // public void addBook(Book book){}
    public void addBook(Book book) {
        Book[] temp = new Book[books.length + 1]; //Make a new book list that allow one more book
        for(int i = 0; i < books.length; i ++) { //Copy original to new book List
            temp[i] = books[i];
        }
        temp[temp.length - 1] = book; //Add book
        books = temp; //Set existing Book list to new Book List
    }
    // public void insertBook(Book book, int index){}
    public void insertBook(Book book, int index) { 
        Book[] temp = new Book[books.length + 1]; //Make a new book list that allow one more book
        for(int i = 0; i < index; i ++) { //Copy original to new book List first half
            temp[i] = books[i];

        }
        temp[index] = book; //Set inserted book to destinated index.
        for(int i = index; i < books.length; i ++) {//Copy original to new book List second half
            temp[i + 1] = books[i];

        }
        books = temp; //Set existing Book list to new Book List

    }
    // public void removeBook(Book book){}
    public void removeBook(Book book) {
        for(int i = 0; i < books.length; i ++) { //Check books to find target with same ISBN
            if(books[i].getIsbn().equals(book.getIsbn())) {
                if(books[i].getQuantity() > 1) { //Remove 1 Quantity when more than one
                    books[i].setQuantity(books[i].getQuantity() - 1);
                }
                else {
                    books[i] = null; //Else remove book entirely
                }

            }
        }
        consolidateBooks();
        if(books[books.length - 1] == null) { //Check if the remove was complete
            Book[] temp = new Book[books.length - 1]; //Make a new book list that allow one less book
            for(int i = 0; i < temp.length; i ++) {//Copy original to new book List
                temp[i] = books[i];
    
            }
            books = temp;
        }
    }

    public void consolidateBooks() {
        int slot = 0; 
        for(int i = 0; i < books.length; i ++) { //Loop over all books to check if any is null
            if(books[i] != null) { //Set first avaliable index to existing books
                books[slot] = books[i];
                if(i != slot) { //Remove book from original when it is moved
                    books[i] = null;
                }
                slot ++;
            }
        }
    }  

    public void setBooks(Book[] books) {
        this.books = books;
    }
       
    // public String bookStoreBookInfo(){} //you are not tested on this method but use it for debugging purposes

    // public String bookStoreUserInfo(){} //you are not tested on this method but use it for debugging purposes

}