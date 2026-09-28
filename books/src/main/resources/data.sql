INSERT INTO books (
    isbn, title, author, description, price,
    currency, created_at, updated_at
)
VALUES
    (
        '978-0134685991', 'Effective Java',
        'Joshua Bloch', 'Best practices for writing clear, robust and maintainable Java code.',
        649.00, 'INR', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        '978-0132350884', 'Clean Code', 'Robert C. Martin',
        'A handbook of agile software craftsmanship focused on readable code.',
        599.00, 'INR', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        '978-0596009205', 'Head First Java',
        'Kathy Sierra, Bert Bates', 'A visual, beginner-friendly introduction to Java and object-oriented programming.',
        799.00, 'INR', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        '978-0201633610', 'Design Patterns',
        'Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides',
        'The classic catalog of reusable object-oriented design patterns.',
        850.00, 'INR', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        '978-0134757599', 'Refactoring',
        'Martin Fowler', 'Techniques for improving the design of existing code, updated for the modern era.',
        725.50, 'INR', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        '978-0321349606', 'Java Concurrency in Practice',
        'Brian Goetz', 'A practical guide to writing correct and performant concurrent Java programs.',
        699.00, 'INR', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        '978-1617297571', 'Spring in Action',
        'Craig Walls', 'Hands-on coverage of building applications with Spring and Spring Boot.',
        899.00, 'INR', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        '978-0135957059', 'The Pragmatic Programmer',
        'David Thomas, Andrew Hunt', 'Timeless advice on becoming a more effective and adaptable developer.',
        749.00, 'INR', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        '978-1449373320', 'Designing Data-Intensive Applications',
        'Martin Kleppmann', 'Principles behind reliable, scalable and maintainable data systems.',
        1150.00, 'INR', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    ),
    (
        '978-0262046305', 'Introduction to Algorithms',
        'Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, Clifford Stein',
        'A comprehensive reference on algorithms and data structures.',
        1499.99, 'INR', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP
    );
