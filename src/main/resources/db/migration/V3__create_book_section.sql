CREATE TABLE book_section (
    id SERIAL PRIMARY KEY,
    book_id INT NOT NULL,
    section_number INT NOT NULL,
    text TEXT NOT NULL,
    type VARCHAR(255) NOT NULL,

    CONSTRAINT fk_book_section FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
    CONSTRAINT uk_book_section UNIQUE (book_id, section_number)
);