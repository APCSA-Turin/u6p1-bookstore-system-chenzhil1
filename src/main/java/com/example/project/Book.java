package com.example.project;

public class Book{
    //requires 5 attributes String title, String author, int yearPublished, String isbn, int quantity
    private String title;
    private String author;
    private int yearPublished;
    private String isbn;
    private int quantity;

    //requires 1 constructor with 5 arguments that intitialize the attribtues of the class.
    public Book(String title, String author, int yearPublished, String isbn, int quantity) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isbn = isbn;
        this.quantity = quantity;
    }

    // public  getTitle() {}
    public String getTitle() {
        return title;
    }

    // public setTitle() {}
    public void setTitle(String newTitle) {
        title = newTitle;
    }

    // public getAuthor() {}
    public String getAuthor() {
        return author;
    }

    // public setAuthor() {}
    public void setAuthor(String newAuthor) {
        author = newAuthor;
    }

    // public getYearPublished() {}
    public int getYearPublished() {
        return yearPublished;
    }

    // public setYearPublished() {}
    public void setYearPublished(int newYearPublished) {
        yearPublished = newYearPublished;

    }
    // public getIsbn() {}
    public String getIsbn() {
        return isbn;
    }

    // public void setIsbn() {}
    public void setIsbn(String newIsbn) {
        isbn = newIsbn;
    }

    // public int getQuantity() {}
    public int getQuantity() {
        return quantity;
    }

    // public void setQuantity() {}
    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }
    // public  bookInfo(){} //returns "Title: [], Author: [], Year: [], ISBN: [], Quantity: []"
    public String bookInfo() {
        String info = "Title: " + title + ", Author: " + author + ", Year: " + yearPublished;
        info += ", ISBN: " + isbn + ", Quantity: " + quantity;
        return info;
    }
}

//"Title: The Great Gatsby, Author: Scott Fitzgerald, Year: 1925, ISBN: 979-8351145013, Quantity: 3"