
CREATE TABLE category (
    id UUID PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE product (
    id UUID PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    available_quantity INTEGER NOT NULL,
    price NUMERIC(19, 2) NOT NULL,
    category_id UUID,
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category (id)
);
