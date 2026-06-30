package com.adventurebook.repository;

import com.adventurebook.model.BookCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategoryModel, Long> {

}