package guru.springframework.jdbc.repositories;

import guru.springframework.jdbc.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book jpaNamed(String title);

    @Query(value = "SELECT * FROM Book b WHERE b.title = :title", nativeQuery = true)
    Book findBookByTitleWithNativeQuery(String title);

    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Book findBookByTitleWithQueryNamed(String title);

    @Query("SELECT b FROM Book b WHERE b.title =?1")
    Book findBookByTitleWithQuery(String title);

    Optional<Book> findBookByTitle(String title);

    Book readByTitle(String title);

    @Nullable
    Book getByTitle(@Nullable String title);

    Stream<Book> findAllByTitleNotNull();

    @Async
    Future<Book> queryByTitle(String title);
}
