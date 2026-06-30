package com.adventurebook.repository;

import com.adventurebook.model.BookSectionModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookSectionRepository extends JpaRepository<BookSectionModel, Long> {

    List<BookSectionModel> findByBookId(Long bookId);

    Optional<BookSectionModel> findByBookIdAndSectionNumber(Long bookId, Integer sectionNumber);

}
