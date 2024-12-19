USE dbms_rooted;

CREATE TABLE admin_users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

INSERT INTO admin_user (username, password) 
VALUES ('admin', 'password');

SELECT * FROM admin_user;

CREATE TABLE farmer_users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

SELECT * FROM farmer_users;

CREATE TABLE crops (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    type VARCHAR(100) NOT NULL,
    season_start INT NOT NULL, -- 1-12 representing months
    season_end INT NOT NULL,   -- 1-12 representing months
    watering_schedule INT NOT NULL
);

INSERT INTO crops (name, type, season_start, season_end, watering_schedule) 
VALUES 
('Peanut', 'Legume', 2, 3, 7),
('Monggo', 'Legume', 6, 9, 9),
('Sitaw', 'Legume', 1, 12, 1),
('Pechay', 'Leafy Vegetable', 9, 2, 3),
('Eggplant', 'Fruiting Vegetable', 12, 8, 1),
('Okra', 'Fruiting Vegetable', 1, 8, 8),
('Chili', 'Fruiting Vegetable', 10, 3, 1),
('Squash', 'Squash', 1, 12, 1),
('Ampalaya', 'Gourd', 1, 12, 2),
('Corn', 'Grain', 10, 3, 4);

SELECT * FROM crops;

CREATE TABLE inventory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(150) NOT NULL UNIQUE,
    quantity INT NOT NULL DEFAULT 0
);

INSERT INTO inventory (item_name, quantity) 
VALUES 
('Fertilizer', 30),
('Pesticide Spray', 20),
('Peanut seeds', 10),
('Monggo seeds', 10),
('Sitaw seeds', 10),
('Pechay seeds', 10),
('Eggplant seeds', 10),
('Okra seeds', 10),
('Chili seeds', 10),
('Squash seeds', 10),
('Ampalaya seeds', 10),
('Corn seeds', 10);

SELECT * FROM inventory;

CREATE TABLE crop_growth_stages (
    crop_id INT NOT NULL,
    sprouting_day INT,
    seeding_day INT,
    budding_day INT,
    flowering_day INT,
    harvest_day INT,
    FOREIGN KEY (crop_id) REFERENCES crops(id) ON DELETE CASCADE
);

ALTER TABLE crop_growth_stages
DROP COLUMN stage_name,
DROP COLUMN duration_days;

ALTER TABLE crop_growth_stages
ADD COLUMN sprouting_day INT,
ADD COLUMN seeding_day INT,
ADD COLUMN budding_day INT,
ADD COLUMN flowering_day INT,
ADD COLUMN harvest_day INT;

INSERT INTO crop_growth_stages (crop_id, sprouting_day, seeding_day, budding_day, flowering_day, harvest_day)
VALUES
(1, 5, 10, 20, 40, 80),  -- Peanut
(2, 4, 9, 18, 35, 70),   -- Monggo
(3, 3, 8, 16, 32, 65),   -- Sitaw
(4, 2, 7, 14, 30, 60),   -- Pechay
(5, 5, 10, 18, 36, 75),  -- Eggplant
(6, 4, 9, 17, 34, 68),   -- Okra
(7, 3, 8, 15, 33, 66),   -- Chili
(8, 6, 12, 22, 45, 85),  -- Squash
(9, 4, 9, 19, 38, 76),   -- Ampalaya
(10, 5, 11, 20, 40, 78); -- Corn

SELECT * FROM crop_growth_stages;

CREATE TABLE crop_fertilizer_schedule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    crop_id INT NOT NULL,
    first_application_day INT,
    second_application_day INT,
    third_application_day INT,
    FOREIGN KEY (crop_id) REFERENCES crops(id) ON DELETE CASCADE
);

ALTER TABLE crop_fertilizer_schedule
DROP COLUMN application_stage,
DROP COLUMN application_day;

ALTER TABLE crop_fertilizer_schedule
ADD COLUMN first_application_day INT,
ADD COLUMN second_application_day INT,
ADD COLUMN third_application_day INT;

INSERT INTO crop_fertilizer_schedule (crop_id, first_application_day, second_application_day, third_application_day)
VALUES
(1, 20, 45, 100),  -- Peanut
(2, 12, 23, 55),   -- Monggo
(3, 10, 18, 33),   -- Sitaw
(4, 7, 12, 18),    -- Pechay
(5, 12, 20, 38),   -- Eggplant
(6, 10, 18, 33),   -- Okra
(7, 12, 20, 38),   -- Chili
(8, 14, 24, 43),   -- Squash
(9, 10, 20, 33),   -- Ampalaya
(10, 12, 25, 48);  -- Corn

SELECT * FROM crop_fertilizer_schedule;

CREATE TABLE planted_crops (
    id INT AUTO_INCREMENT PRIMARY KEY,
    farmer_username VARCHAR(50) NOT NULL,
    crop_name VARCHAR(100) NOT NULL,
    planting_date DATE NOT NULL,
    FOREIGN KEY (farmer_username) REFERENCES farmer_users(username) ON DELETE CASCADE
);

SELECT * FROM planted_crops;
