CREATE TABLE usershop(
    user_shop_id SERIAL PRIMARY KEY ,
    user_id INTEGER NOT NULL ,
    shop_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (shop_id) REFERENCES locationShop(shopId) ON DELETE CASCADE
);