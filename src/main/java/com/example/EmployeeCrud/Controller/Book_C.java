package com.example.EmployeeCrud.Controller;

import com.example.EmployeeCrud.Model.Book;
import com.example.EmployeeCrud.Repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Book_C {
    @Autowired
    private BookRepo bookRepo;
    @GetMapping
    public ResponseEntity<List<BookRepo>> getAllBooks(){
        try{
            List<Book> booklist=new ArrayList<>();
            bookRepo.findAll().forEach(booklist::add);
            if (booklist.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getBook_by_ID")
    public ResponseEntity<Book> getBook_by_ID(@PathVariable Long ID){
      Optional<Book> bookData=bookRepo.findById(ID);
      if (bookData.isPresent())
      {
          return new ResponseEntity<>(bookData.get(),HttpStatus.OK);
      }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/addbook")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book bookobj=bookRepo.save(book);
        return new ResponseEntity<>(bookobj, HttpStatus.OK);
    }
    @PostMapping("/updatebook")
    public ResponseEntity<Book> updateBook_by_ID(@PathVariable Long ID, Book newbookdta){
        Optional<Book> oldbookdata=bookRepo.findById(ID);
        if(oldbookdata.isPresent()){
           Book updatebook= oldbookdata.get();
           updatebook.setB_name(newbookdta.getB_name());
           updatebook.setAuthor(newbookdta.getAuthor());
           Book bookobj=bookRepo.save(updatebook);
            return new ResponseEntity<>(bookobj,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deletebook")
    public ResponseEntity<Book> deleteBook_by_ID(@PathVariable Long ID, Book Dbookdta){
        bookRepo.deleteById(ID);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
