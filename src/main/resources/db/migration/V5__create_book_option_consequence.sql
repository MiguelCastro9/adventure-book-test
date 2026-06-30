CREATE TABLE book_option_consequence (
    id SERIAL PRIMARY KEY,
    option_id INT NOT NULL,
    type VARCHAR(255) NOT NULL,
    consequence_value INT NOT NULL,
    text TEXT NOT NULL,

    CONSTRAINT fk_option_consequence FOREIGN KEY (option_id) REFERENCES book_section_option(id) ON DELETE CASCADE
);