CREATE TABLE locationShop(
                             shopId SERIAL PRIMARY KEY NOT NULL,
                             location VARCHAR(255) NOT NULL,
                             rating INTEGER DEFAULT 0 NOT NULL,
                             opening TIME NOT NULL,
                             closing TIME NOT NULL
);