package com.adventurebook.repository;

import com.adventurebook.model.BookModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {

    @Query(value = """
            SELECT b.*
            FROM book b
            LEFT JOIN book_category bc ON bc.book_id = b.id
            WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')))
              AND (:author IS NULL OR LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%')))
              AND (:category IS NULL OR LOWER(bc.category) LIKE LOWER(CONCAT('%', :category, '%')))
              AND (:difficulty IS NULL OR LOWER(b.difficulty) LIKE LOWER(CONCAT('%', :difficulty, '%')))
            GROUP BY b.id
            """, nativeQuery = true)
    List<BookModel> searchBooks(
            @Param("title") String title,
            @Param("author") String author,
            @Param("category") String category,
            @Param("difficulty") String difficulty
    );
}
