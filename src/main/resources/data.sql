-- 直接插入用户表的初始数据
INSERT INTO "user" (username, password, salt, email, type, status, activation_code, header_url, create_time)
VALUES
    ('user1', 'password1', 'salt1', 'user1@example.com', 0, 1, 'code1', 'http://example.com/header1.png', '2023-01-01 00:00:00'),
    ('user2', 'password2', 'salt2', 'user2@example.com', 0, 1, 'code2', 'http://example.com/header2.png', '2023-01-02 00:00:00'),
    ('user3', 'password3', 'salt3', 'user3@example.com', 0, 1, 'code3', 'http://example.com/header3.png', '2023-01-03 00:00:00');

-- 直接插入讨论帖子表的初始数据
INSERT INTO discuss_post (user_id, title, content, type, status, create_time, comment_count, score)
VALUES
    (1, 'First Post', 'This is the content of the first post.', 0, 0, '2023-01-01 00:00:00', 0, 0.0),
    (2, 'Second Post', 'This is the content of the second post.', 0, 0, '2023-01-02 00:00:00', 0, 0.0),
    (3, 'Third Post', 'This is the content of the third post.', 0, 0, '2023-01-03 00:00:00', 0, 0.0);

-- 直接插入评论表的初始数据
INSERT INTO comment (user_id, entity_type, entity_id, target_id, content, status, create_time)
VALUES
    (1, 1, 1, 0, 'This is a comment on the first post.', 0, '2023-01-01 00:00:00'),
    (2, 1, 2, 0, 'This is a comment on the second post.', 0, '2023-01-02 00:00:00'),
    (3, 1, 3, 0, 'This is a comment on the third post.', 0, '2023-01-03 00:00:00');
