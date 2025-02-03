-- Create table for user if not exists
CREATE TABLE IF NOT EXISTS "user" (
                                      id SERIAL PRIMARY KEY,
                                      username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    salt VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    type INTEGER NOT NULL,
    status INTEGER NOT NULL,
    activation_code VARCHAR(100),
    header_url VARCHAR(200),
    create_time TIMESTAMP NOT NULL
    );

-- Create table for discuss_post if not exists
CREATE TABLE IF NOT EXISTS discuss_post (
                                            id SERIAL PRIMARY KEY,
                                            user_id INTEGER NOT NULL REFERENCES "user"(id),
    title VARCHAR(100),
    content TEXT,
    type INTEGER DEFAULT 0,
    status INTEGER DEFAULT 0,
    create_time TIMESTAMP NOT NULL,
    comment_count INTEGER DEFAULT 0,
    score DOUBLE PRECISION DEFAULT 0.0
    );

-- Create table for comment if not exists
CREATE TABLE IF NOT EXISTS comment (
                                       id SERIAL PRIMARY KEY,
                                       user_id INTEGER NOT NULL REFERENCES "user"(id),
    entity_type INTEGER,
    entity_id INTEGER,
    target_id INTEGER DEFAULT 0,
    content TEXT,
    status INTEGER,
    create_time TIMESTAMP NOT NULL
    );

-- Create table for login_ticket if not exists
CREATE TABLE IF NOT EXISTS login_ticket (
                                            id SERIAL PRIMARY KEY,
                                            user_id INTEGER NOT NULL REFERENCES "user"(id),
    ticket VARCHAR(45) NOT NULL,
    status INTEGER,
    expired TIMESTAMP NOT NULL
    );

-- Create table for message if not exists
CREATE TABLE IF NOT EXISTS message (
                                       id SERIAL PRIMARY KEY,
                                       from_id INTEGER,
                                       to_id INTEGER,
                                       conversation_id VARCHAR(45) NOT NULL,
    content TEXT,
    status INTEGER,
    create_time TIMESTAMP NOT NULL
    );

-- 添加索引
CREATE INDEX idx_discuss_post_user_id ON discuss_post(user_id);
CREATE INDEX idx_comment_user_id ON comment(user_id);
CREATE INDEX idx_comment_entity_id ON comment(entity_id);
CREATE INDEX idx_comment_target_id ON comment(target_id);
CREATE INDEX idx_login_ticket_user_id ON login_ticket(user_id);
CREATE INDEX idx_login_ticket_ticket ON login_ticket(ticket);
CREATE INDEX idx_message_from_id ON message(from_id);
CREATE INDEX idx_message_to_id ON message(to_id);
CREATE INDEX idx_message_conversation_id ON message(conversation_id);
