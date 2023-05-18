package com.manage.lms.service;

import com.manage.lms.pojo.Book;
import com.manage.lms.pojo.Directory;


import java.util.List;

public interface IBookService {
    List<Book> selectAll();

    List<Directory> selectlist();

    void insert(Book book);

    Book selectById(Long id);

    void update(Book book);

    void delete(Long id);

    List<Book> selectBook(Book book);

    List<Book> selectOrAll(Book book);

}
