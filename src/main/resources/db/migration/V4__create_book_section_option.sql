CREATE TABLE book_section_option (
    id SERIAL PRIMARY KEY,
    section_id INT NOT NULL,
    description VARCHAR(255) NOT NULL,
    goto_section_number INT NOT NULL,

    CONSTRAINT fk_book_section_option FOREIGN KEY (section_id) REFERENCES book_section(id) ON DELETE CASCADE
);