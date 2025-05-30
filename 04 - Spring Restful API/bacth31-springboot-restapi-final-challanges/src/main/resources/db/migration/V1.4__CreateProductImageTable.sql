CREATE SEQUENCE oe.product_images_image_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE oe.product_images
(
    image_id   SERIAL PRIMARY KEY,
    file_name  VARCHAR(255) NOT NULL,
    file_size  REAL,
    file_type  VARCHAR(50),
    file_uri   VARCHAR(100),
    product_id SMALLINT,
    FOREIGN KEY (product_id) REFERENCES oe.products (product_id)
        ON UPDATE CASCADE ON DELETE CASCADE
)
