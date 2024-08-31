CREATE TABLE led_power_entity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    led_name VARCHAR(255),
    current_power FLOAT,
    total_power FLOAT,
    date DATE,
    user_id INT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user_entity(id)
);
