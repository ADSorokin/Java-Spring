
INSERT INTO readers (name, email) VALUES
                                      ('Alice Johnson', 'alice.johnson@example.com'),
                                      ('Bob Smith', 'bob.smith@example.com');


INSERT INTO books (title, author, publisher, publication_year, available_copies, reader_id) VALUES
                                                                                                ('The Catcher in the Rye', 'J.D. Salinger', 'Little, Brown and Company', 1951, 3, 1),
                                                                                                ('To Kill a Mockingbird', 'Harper Lee', 'J.B. Lippincott & Co.', 1960, 5, NULL),
                                                                                                ('1984', 'George Orwell', 'Secker & Warburg', 1949, 2, 2);