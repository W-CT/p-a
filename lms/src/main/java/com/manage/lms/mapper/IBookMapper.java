package com.manage.lms.mapper;

import com.manage.lms.pojo.Book;
import com.manage.lms.pojo.Directory;
import java.util.List;

public interface IBookMapper {
    List<Book> selectAll();

    List<Directory> selectlist();

    void insert(Book book);

    Book selectById(Long id);

    void update(Book book);

    void delete(Long id);

    List<Book> selectBook(Book book);

}
