-- ============================================
-- CƠ SỞ DỮ LIỆU INOTES_DB
-- ============================================

DROP DATABASE IF EXISTS inotes_db;
CREATE DATABASE inotes_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;
USE inotes_db;

-- ============================================
-- BẢNG NOTE_TYPE (Thể loại ghi chú)
-- ============================================
CREATE TABLE note_type (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255)
);

-- ============================================
-- BẢNG NOTES (Ghi chú)
-- ============================================
CREATE TABLE notes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    type_id INT,
    FOREIGN KEY (type_id) REFERENCES note_type(id)
        ON DELETE SET NULL ON UPDATE CASCADE
);

-- ============================================
-- DỮ LIỆU MẪU
-- ============================================
INSERT INTO note_type (name, description) VALUES
    ('Cá nhân', 'Ghi chú về cuộc sống cá nhân'),
    ('Công việc', 'Ghi chú công việc, dự án'),
    ('Học tập', 'Ghi chú học tập, tài liệu');

INSERT INTO notes (title, content, type_id) VALUES
    ('Họp giao ban đầu tuần', 'Chuẩn bị báo cáo tiến độ dự án ABC', 2),
    ('Ôn tập Design Pattern', 'Ôn lại Singleton, Factory, Strategy', 3),
    ('Mua quà sinh nhật mẹ', 'Tìm hiểu sở thích của mẹ', 1);

-- ============================================
-- KIỂM TRA
-- ============================================
SELECT n.id, n.title, n.content, nt.name AS category
FROM notes n
LEFT JOIN note_type nt ON n.type_id = nt.id;