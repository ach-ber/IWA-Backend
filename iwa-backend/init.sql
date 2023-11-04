-- create the address table
CREATE TABLE address (
    id SERIAL NOT NULL PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    zip VARCHAR(255) NOT NULL
);
-- insert some fake data
INSERT INTO address (street, city, zip) VALUES ('123 Main St', 'Anytown', '12345');
INSERT INTO address (street, city, zip) VALUES ('456 Main St', 'Anytown', '12345');
INSERT INTO address (street, city, zip) VALUES ('789 Main St', 'Anytown', '12345');
