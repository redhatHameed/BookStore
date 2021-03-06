package com.books;

import com.books.model.Book;
import com.books.service.BookService;
import com.books.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BookStoreApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class BookControllerIntegrationTest
{
    @Autowired
    MockMvc mockMvc;

    /*@Autowired
    BookRepository bookRepository;*/

    @Autowired
    BookService bookService;

    @Test
    public void createPersons_And_GetPersonsTest() throws Exception
    {
        Book book=createBook("Spring Boot Essentials",200,12.23,"Craig");
        mockMvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().
                contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].title", is(book.getTitle())));
    }

    @Test
    public void whenValidInput_createPerson() throws Exception
    {
        Book book=createBook("Java Edition 9",200,12.23,"Craig");
        mockMvc.perform(post("/books/save_book").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(book)));
        List<Book> retrievedBooks = bookService.findAll();
        for(Book retrievedBook : retrievedBooks)
        {
            if(retrievedBook.getTitle().equals("Java Edition 9"))
                System.out.println("Success");
        }
        //assertThat(retrivedBooks).extracting(Book::getTitle).isEqualTo("Spring Essentials");
    }

    private Book createBook(String title, Integer numberOfPages, Double cost, String author)
    {
        Book book=new Book(title,numberOfPages,cost,author);
        bookService.saveAndFlush(book);
        return book;
    }

    @Test
    public void insertMillionRecords()
    {
        Book book=null;
        List<Book> bookList2=null;
        List<Book>  bookList=new ArrayList<>();
        try
        {
            for(int i = 0; i< 1000000L; i++)
            {
                book=new Book("Spring Boot Essentials",200,12.23,"Craig");
                bookList.add(book);
            }

            Iterable<Book> booksIterable=new Iterable<Book>()
            {
                @Override
                public Iterator<Book> iterator()
                {
                    return bookList.iterator();
                }
            };

            LocalDateTime   startDateTime=LocalDateTime.now();
            System.out.println("Before Inserting Million Records, Time: "+startDateTime.toString());
            bookService.saveAll(booksIterable);
            LocalDateTime   endDateTime=LocalDateTime.now();
            System.out.println("After Inserting Million Records, Time: "+endDateTime.toString());
            long hours = startDateTime.until( endDateTime, ChronoUnit.HOURS);
            long minutes = startDateTime.until( endDateTime, ChronoUnit.MINUTES);
            long seconds=startDateTime.until(endDateTime,ChronoUnit.SECONDS);
            long microSeconds=startDateTime.until(endDateTime,ChronoUnit.MICROS);
            System.out.println("Time took to insert million records, "+minutes+" hours "+minutes+" minutes "+seconds+" seconds "+microSeconds+" microSeconds");
            bookList2=bookService.findAll();
            System.out.println("BooksList =>"+bookList2.size());

        }
        finally
        {
            bookList.clear();
            bookList2.clear();
        }


    }
}