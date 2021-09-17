package br.com.study.libraryapi.api.model.repository;

import br.com.study.libraryapi.api.model.entity.Book;
import br.com.study.libraryapi.api.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    BookRepository repository;

    @Autowired
    BookService service;

    @Test
    @DisplayName("Deve retornar verdadeiro quando existir um livro na base com o isbn informado")
    public void returnTrueWhenIsbnExists(){
        //cenario
        String isbn = "123";
        Book book = Book.builder().title("Aventuras").author("Fulano").isbn(isbn).build();
        entityManager.persist(book);
        //exec
        boolean exists = repository.existsByIsbn(isbn);

        //verify
        assertThat(exists).isTrue();

    }
    @Test
    @DisplayName("Deve retornar verdadeiro quando não existir um livro na base com o isbn informado")
    public void returnFalseWhenIsbnDoesntExists(){
        //cenario
        String isbn = "123";


        //exec
        boolean exists = repository.existsByIsbn(isbn);

        //verify
        assertThat(exists).isFalse();

    }
    @Test
    @DisplayName("Deve obter um livro por id")
    public void findByIdTest(){
        Book book = createNewBook("123");
        entityManager.persist(book);

        //exec
        Optional<Book> foundBook = repository.findById(book.getId());

        //verify
        assertThat(foundBook.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Deve salvar um livro")
    public void saveBookTest(){
        Book book = createNewBook("123");

        Book savedBook = repository.save(book);

        assertThat(savedBook.getId()).isNotNull();
    }

    @Test
    @DisplayName("Deve deletar um livro")
    public void deleteBookTest(){
        Book book = createNewBook("123");
        entityManager.persist(book);

        Book foundBook = entityManager.find(Book.class,book.getId());

        repository.delete(foundBook);

        Book deletedBook = entityManager.find(Book.class,book.getId());

    }
    public static Book createNewBook(String isbn) {
        return Book.builder().author("Artur").title("As Aventuras").isbn(isbn).build();
    }
    private Book createValidBook() {
        return Book.builder().isbn("123").author("Fulano").title("As aventuras").build();
    }
}