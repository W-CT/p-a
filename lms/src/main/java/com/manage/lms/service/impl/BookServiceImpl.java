package com.manage.lms.service.impl;

import com.manage.lms.mapper.IBookMapper;
import com.manage.lms.pojo.Book;
import com.manage.lms.pojo.Directory;
import com.manage.lms.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class BookServiceImpl implements IBookService {
    @Autowired
    private IBookMapper bookMapper;
    @Override
    public List<Book> selectAll() {
        return bookMapper.selectAll();
    }

    @Override
    public List<Directory> selectlist() {
        return bookMapper.selectlist();
    }

    @Override
    public void insert(Book book) {
        bookMapper.insert(book);
    }

    @Override
    public Book selectById(Long id) {
        return bookMapper.selectById(id);
    }


    @Override
    public void update(Book book) {
        bookMapper.update(book);
    }

    @Override
    public void delete(Long id) {
        bookMapper.delete(id);
    }

    @Override
    public List<Book> selectBook(Book book) {
        return bookMapper.selectBook(book);
    }

    @Override
    public List<Book> selectOrAll(Book book) {

        if(book.getDirectory().getId()==-1 && book.getName()==""){
            System.out.println(book.getDirectory().getId()+book.getName());
            return bookMapper.selectAll();
        }else {
            return bookMapper.selectBook(book);
        }

    }

}
