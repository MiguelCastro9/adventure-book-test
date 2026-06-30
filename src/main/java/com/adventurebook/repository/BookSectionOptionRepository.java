package com.adventurebook.repository;

import com.adventurebook.model.BookSectionOptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookSectionOptionRepository extends JpaRepository<BookSectionOptionModel, Long> {

}