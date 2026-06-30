package com.adventurebook.repository;

import com.adventurebook.model.BookOptionConsequenceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOptionConsequenceRepository extends JpaRepository<BookOptionConsequenceModel, Long> {

}
