package se.yrgo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import se.yrgo.domain.Book;

import java.util.*;

@Controller
public class BookController {
    private List<Book> bookList = new ArrayList<>();

    public BookController() {
        bookList.add(new Book("Sagan om ringen", "J.R.R. Tolkien", "Fantasy"));
        bookList.add(new Book("Brott och straff", "Fjodor Dostojevskij", "Klassiker"));
        bookList.add(new Book("1984", "George Orwell", "Dystopi"));
        bookList.add(new Book("Den gamle och havet", "Ernest Hemingway", "Drama"));
        bookList.add(new Book("Män som hatar kvinnor", "Stieg Larsson", "Deckare"));
        bookList.add(new Book("Harry Potter och de vises sten", "J.K. Rowling", "Fantasy"));
        bookList.add(new Book("Da Vinci-koden", "Dan Brown", "Thriller"));
        bookList.add(new Book("Frankenstein", "Mary Shelley", "Skräck"));
        bookList.add(new Book("Stolthet och fördom", "Jane Austen", "Romantik"));
        bookList.add(new Book("The Martian", "Andy Weir", "Science fiction"));

    }

    @GetMapping("/home")
    public String firstPage(Model model) {
        Date dateAndTime = new Date();
        model.addAttribute("dateAndTime", dateAndTime);
        return "home";
    }

    @GetMapping("/books")
    public String bookList(Model model) {
        model.addAttribute("bookList", bookList);
        return "booklist";
    }

    @GetMapping("/genre")
    public ModelAndView showGenre(@RequestParam(required = false) String type) {
        List<Book> filteredBooks = new ArrayList<>();
        Set<String> allGenres = new TreeSet<>();

        for (Book b : bookList) {
            allGenres.add(b.getGenre());
            if (type != null && b.getGenre().equalsIgnoreCase(type)) {
                filteredBooks.add(b);
            }
        }

        ModelAndView mv = new ModelAndView("genre");
        mv.addObject("allGenres", allGenres);

        if (!filteredBooks.isEmpty()) {
            mv.addObject("genre", filteredBooks);
        }

        return mv;
    }
}
