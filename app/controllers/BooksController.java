package controllers;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.*;

import java.util.Set;
import java.util.List;

import models.Book;
import views.html.Books.*;
import javax.inject.Inject;
import views.html.Books.*;
import views.html.errors.*;

public class BooksController extends Controller{



    @Inject
    FormFactory formFactory;

    // for all books
    public Result index(){
        // Set<Book> books = Book.allBooks();
        // return ok(index.render(books));

        List<Book> books = Book.find.all();
        return ok(index.render(books));
    }

    // to create book
    public Result create(){
        Form<Book> bookForm = formFactory.form(Book.class);

        return ok(create.render(bookForm));
    }

    // to save book
    public Result save(){
        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        if(bookForm.hasErrors()){
            flash("danger","Please Correct the form!! ");
            return badRequest(create.render(bookForm));
        }
        Book book = bookForm.get();
        // Book.add(book);
        // return TODO;

        book.save();
        flash("success","Book Successfully Created!!");
        return redirect(routes.BooksController.index());
    }

    // to edit book

    public Result edit(Integer id){
        // Book book = Book.findById(id);

        Book book = Book.find.byId(id);
        if(book == null){

            // return notFound("Book Not found to edit");
            return notFound(_404.render());
        }
        Form<Book> bookForm = formFactory.form(Book.class).fill(book);
        // return TODO;
        return ok(edit.render(bookForm));
    }

    public Result update(){

        Form<Book> bookForm = formFactory.form(Book.class).bindFromRequest();
        if(bookForm.hasErrors()){
            flash("danger","Please Correct the form!");
            return badRequest(edit.render(bookForm));
        }
        Book book = bookForm.get();
        // Book oldBook = Book.findById(book.id);
        Book oldBook = Book.find.byId(book.id);
        if(oldBook == null){
            flash("danger","Book not found");
            // return notFound("Book Not Found!");
            return notFound(_404.render());

        }
        oldBook.title = book.title;
        oldBook.price = book.price;
        oldBook.author = book.author;
        // return TODO;
        oldBook.update();

        flash("success","Book Updated Successfully !!");
        // return redirect(routes.BooksController.index());
        return ok();
    }
    public Result destroy(Integer id){
        // Book book = Book.findById(id);
        Book book = Book.find.byId(id);

        if (book == null){
            flash("danger","Book not found");
            return notFound("Book not found!!");
        }
        // Book.remove(book);
        book.delete();
        flash("success","Book Deleted Successfully!");
        // return redirect(routes.BooksController.index());
        return ok(); // 200 Status Code
        // return TODO;
    }


    // for book details

    public Result show(Integer id){
        //  Book book = Book.findById(id);
        Book book = Book.find.byId(id);
        if (book == null){
            // return notFound("Book Not found to Show");
            return notFound(_404.render());

        }
        // return TODO;
        return ok(show.render(book));
    }
}