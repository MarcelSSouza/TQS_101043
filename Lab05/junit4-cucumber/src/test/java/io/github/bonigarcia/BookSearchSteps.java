package io.github.bonigarcia;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookSearchSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @Given("a book with the title {string}, written by {string}, published in {string}")
    public void addNewBook(String title, String author, String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date published = dateFormat.parse(date);
        Book book = new Book(title, author, published);
        library.addBook(book);
    }

    @When("the customer searches for books published between {string} and {string}")
    public void setSearchParameters(String year1, String year2) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date from = dateFormat.parse(year1);
        Date to = dateFormat.parse(year2);
        result = library.findBooks(from, to);
    }

    @When("the customer searches for books written by {string}")
    public void searchByAuthor(String author) {
        result = library.findBooksByAuthor(author);
    }

    @When("the customer searches for a book with the title {string}")
    public void searchByTitle(String title) {
        result = library.findBooksByTitle(title);
    }

    @Then("{int} books should have been found")
    public void verifyAmountOfBooksFound(int booksFound) {
        assertEquals(result.size(), booksFound);
    }

    @Then("Book {int} should have the title {string}")
    public void verifyBookAtPosition(int position, String title) {
        assertEquals(result.get(position - 1).getTitle(), title);
    }

    @Then("Book {int} should have the author {string}")
    public void verifyAuthorAtPosition(int position, String author) {
        assertEquals(result.get(position - 1).getAuthor(), author);
    }

    @Then("Book {int} should have the publication date {string}")
    public void verifyPublicationDateAtPosition(int position, String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date published = dateFormat.parse(date);
        assertEquals(result.get(position - 1).getPublished(), published);
    }
}
