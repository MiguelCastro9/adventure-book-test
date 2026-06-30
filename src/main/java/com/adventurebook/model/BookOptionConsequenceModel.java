package com.adventurebook.model;

import com.adventurebook.enums.ConsequenceTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_option_consequence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookOptionConsequenceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConsequenceTypeEnum type;

    @Column(name = "consequence_value", nullable = false)
    private Integer value;

    @Column(columnDefinition = "TEXT")
    private String text;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private BookSectionOptionModel option;
}
