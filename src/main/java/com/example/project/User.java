package com.example.project;

public class User{
    //requires 3 private attributes String name, String Id, Book book that is initialized to empty
    private String name;
    private String Id;
    private Book[] books = new Book[5];
    //requires 1 contructor with two parameters that will initialize the name and id
    public User(String name, String Id) {
        this.name = name;
        this.Id = Id;
    }

    // public  getName() {}
    public String getName() {
        return name;
    }
    // public  setName()  {}
    public void setName(String newName) {
        name = newName;
    }

    // public  getId() {}
    public String getId() {
        return Id;
    }

    // public void setId() {}
    public void setId(String newID) {
        Id = newID;
    }

    // public getBooks() {}
    public Book[] getBook() {
        return books;
    }

    // public setBooks() {}
    public void setBooks(Book[] newBooks) {
        books = newBooks;
    }

    // public String bookListInfo(){} //returns a booklist for the user, if empty, output "empty"
    public String bookListInfo() {
        String bookList = "";
        for(Book book: books) {
            if(book != null) {
                bookList += book.bookInfo() + "\n";
            }
            else {
                bookList += "empty\n";
            }
        }
        return bookList;
        
    }
    // public String userInfo(){} //returns  "Name: []\nID: []\nBooks:\n[]"
    public String userInfo() {
        String info = "Name: " + name + "\nId: " + Id + "\nBooks: \n" + bookListInfo();
        return info;
    }
}