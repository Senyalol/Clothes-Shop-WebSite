CREATE TABLE product_image(
  product_image_id SERIAL PRIMARY KEY ,
  product_id INTEGER NOT NULL ,
  image VARCHAR(1500) NOT NULL,
  FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);