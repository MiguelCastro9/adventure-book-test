CREATE TABLE book_category (
    id SERIAL PRIMARY KEY,
    book_id INT NOT NULL,
    category VARCHAR(255) NOT NULL,

    CONSTRAINT fk_book_category FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE,
    CONSTRAINT uk_book_category UNIQUE (book_id, category)
);

