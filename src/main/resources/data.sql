-- INSERT INTO "user" (username, password, salt, email, type, status, activation_code, header_url, create_time)
-- VALUES
--     ('user1', 'password1', 'salt1', 'user1@example.com', 0, 1, 'code1', 'https://ik.imagekit.io/javaforum/default_head_sticker.png?updatedAt=1737969599494', '2023-01-01 00:00:00'),
--     ('user2', 'password2', 'salt2', 'user2@example.com', 0, 1, 'code2', 'https://ik.imagekit.io/javaforum/default_head_sticker.png?updatedAt=1737969599494', '2023-01-02 00:00:00'),
--     ('user3', 'password3', 'salt3', 'user3@example.com', 0, 1, 'code3', 'https://ik.imagekit.io/javaforum/default_head_sticker.png?updatedAt=1737969599494', '2023-01-03 00:00:00');
--
-- INSERT INTO discuss_post (user_id, title, content, type, status, create_time, comment_count, score)
-- VALUES
--     (1, 'First Post', 'This is the content of the first post.', 0, 0, '2023-01-01 00:00:00', 0, 0.0),
--     (2, 'Second Post', 'This is the content of the second post.', 0, 0, '2023-01-02 00:00:00', 0, 0.0),
--     (3, 'Third Post', 'This is the content of the third post.', 0, 0, '2023-01-03 00:00:00', 0, 0.0);

INSERT INTO "user" (username, password, salt, email, type, status, activation_code, header_url, create_time)
VALUES
    ('john_doe', 'hashed_password1', 'random_salt1', 'john.doe@example.com', 0, 1, 'activation_code_1', 'https://ik.imagekit.io/javaforum/default_head_sticker.png?updatedAt=1737969599494', '2023-05-12 08:45:23'),
    ('jane_smith', 'hashed_password2', 'random_salt2', 'jane.smith@example.com', 0, 1, 'activation_code_2', 'https://ik.imagekit.io/javaforum/default_head_sticker.png?updatedAt=1737969599494', '2023-06-15 14:30:45'),
    ('michael_johnson', 'hashed_password3', 'random_salt3', 'michael.johnson@example.com', 0, 1, 'activation_code_3', 'https://ik.imagekit.io/javaforum/default_head_sticker.png?updatedAt=1737969599494', '2023-07-20 17:22:10');

INSERT INTO discuss_post (user_id, title, content, type, status, create_time, comment_count, score)
VALUES
    (1, 'How to learn Java effectively', 'In this post, I share some of the best practices and resources for learning Java.', 0, 0, '2023-05-12 09:00:00', 0, 4.5),
    (2, 'Exploring Spring Boot', 'A detailed guide for beginners to start working with Spring Boot and building web applications.', 0, 0, '2023-06-15 15:00:00', 0, 4.8),
    (3, 'Understanding RESTful APIs', 'This post covers the fundamentals of RESTful APIs and how to use them in web development.', 0, 0, '2023-07-20 18:00:00', 0, 4.2);


