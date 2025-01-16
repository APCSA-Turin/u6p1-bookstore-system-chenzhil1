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
        for(int i = 0; i < users.length; i ++) {
            if(users[i] == null) {
                users[i] = user;
                break;
            }
        }
    }
    // public void removeUser(User user){}
    public void removeUser(User user) {
        for(int i = 0; i < users.length; i ++) {
            if(users[i] != null) {
                if(users[i].getId().equals(user.getId())) {
                    users[i] = null;
                }
            }
        }
        consolidateUsers();

    }
    // public void consolidateUsers(){}
    public void consolidateUsers() {
        int slot = 0;
        for(int i = 0; i < users.length; i ++) {
            if(users[i] != null) {
                users[slot] = users[i];
                if(i != slot) {
                    users[i] = null;
                }
                slot ++;
            }
        }
    }
    // public void addBook(Book book){}
    public void addBook(Book book) {
        Book[] temp = new Book[books.length + 1];
        for(int i = 0; i < books.length; i ++) {
            temp[i] = books[i];
        }
        temp[temp.length - 1] = book;
        books = temp;
    }
    // public void insertBook(Book book, int index){}
    public void insertBook(Book book, int index) {
        Book[] temp = new Book[books.length + 1];
        for(int i = 0; i < index; i ++) {
            temp[i] = books[i];

        }
        temp[index] = book;
        for(int i = index; i < books.length; i ++) {
            temp[i + 1] = books[i];

        }
        books = temp;

    }
    // public void removeBook(Book book){}
    public void removeBook(Book book) {
        for(int i = 0; i < books.length; i ++) {
            if(books[i].getIsbn().equals(book.getIsbn())) {
                if(books[i].getQuantity() > 1) {
                    books[i].setQuantity(books[i].getQuantity() - 1);
                }
                else {
                    books[i] = null;
                }

            }
        }
        consolidateBooks();
        if(books[books.length - 1] == null) {
            Book[] temp = new Book[books.length - 1];
            for(int i = 0; i < temp.length; i ++) {
                temp[i] = books[i];
    
            }
            books = temp;
        }
    }

    public void consolidateBooks() {
        int slot = 0;
        for(int i = 0; i < books.length; i ++) {
            if(books[i] != null) {
                books[slot] = books[i];
                if(i != slot) {
                    books[i] = null;
                }
                slot ++;
            }
        }
    }  

       
    // public String bookStoreBookInfo(){} //you are not tested on this method but use it for debugging purposes

    // public String bookStoreUserInfo(){} //you are not tested on this method but use it for debugging purposes

}