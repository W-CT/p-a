package com.manage.lms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.lms.pojo.Book;
import com.manage.lms.pojo.Directory;
import com.manage.lms.service.IBookService;
import com.manage.lms.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    //查询所有书籍数据
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Book> selectAll() {
        System.out.println(bookService.selectAll());
        return bookService.selectAll();
    }

    //根据书籍ID查询数据段(*编辑数据*)
    @RequestMapping(value = "/getBookById", method = RequestMethod.GET)
    @ResponseBody
    public Book selectById(Long id) {
        return bookService.selectById(id);
    }

    //查询所有分类信息
    @RequestMapping(value = "/input", method = RequestMethod.GET)
    @ResponseBody
    public List<Directory> input() {
        return bookService.selectlist();
    }

    //编辑书籍数据
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@RequestBody Book book) {
        bookService.update(book);
        return "success";
    }

    //删除数据
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String delete(Long id) {
        bookService.delete(id);
        return "success";
    }

    //添加数据
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addbooks(@RequestBody Book book) {
        bookService.insert(book);
        return "success";
    }

    //分页功能
    @RequestMapping(value = "/pageinfo", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<Book> getPageInfo(@RequestBody Book book, int pageNum, int pageSize) {
        //当前第几页和每页记录条数
        PageHelper.startPage(pageNum, pageSize);
        //获取总数据
        List<Book> list = bookService.selectOrAll(book);
        System.out.println(500);
        //获得当前分页对象
        PageInfo<Book> pageInfo = new PageInfo<Book>(list);
        System.out.println(pageInfo);
        return pageInfo;
    }


}
