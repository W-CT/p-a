package com.manage.lms.pojo;

public class Book {
    private Long id;
    private String name;
    private String author;
    private String press;
    private Long isbn;
    private Integer numbers;
    private Directory directory;
    private Integer page;//显示第几页
    private Integer limit;//一页显示的数据

    public Book() {
    }

    public Book(Long id, String name, String author, String press, Long isbn, Integer numbers,
                Directory directory, Integer page, Integer limit) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.press = press;
        this.isbn = isbn;
        this.numbers = numbers;
        this.directory = directory;
        this.page = page;
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", isbn=" + isbn +
                ", numbers=" + numbers +
                ", directory=" + directory +
                ", page=" + page +
                ", limit=" + limit +
                '}';
    }
}
