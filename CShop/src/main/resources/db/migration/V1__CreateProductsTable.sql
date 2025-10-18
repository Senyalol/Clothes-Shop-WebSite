CREATE TABLE product(
    product_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL ,
    price DOUBLE PRECISION NOT NULL ,
    amount INTEGER NOT NULL ,
    availability BOOLEAN NOT NULL ,
    color VARCHAR(255),
    size INTEGER ,
    sex VARCHAR(200),
    category VARCHAR(255) NOT NULL ,
    type VARCHAR(255)
);