CREATE SEQUENCE hibernate_sequence START 1 INCREMENT 1;

CREATE TABLE store_order
(
    id BIGINT PRIMARY  KEY ,
    created_time  BIGINT,
    shipment_address VARCHAR ,
    receiver_name VARCHAR,
    receiver_phone VARCHAR
);
