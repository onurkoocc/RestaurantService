
-- Create the 'restaurants' table
CREATE TABLE restaurants (
                             id BIGSERIAL PRIMARY KEY,
                             name VARCHAR(255),
                             address VARCHAR(255),
                             table_count INTEGER,
                             account_info VARCHAR(255),
                             created_at TIMESTAMP DEFAULT NOW(),
                             updated_at TIMESTAMP
);

-- Create the 'restaurant_manager_ids' table
CREATE TABLE restaurant_manager_ids (
                                        restaurant_id BIGINT NOT NULL REFERENCES restaurants(id) ON DELETE CASCADE,
                                        manager_id UUID NOT NULL,
                                        PRIMARY KEY (restaurant_id, manager_id)
);

-- Create the 'restaurant_waiter_ids' table
CREATE TABLE restaurant_waiter_ids (
                                       restaurant_id BIGINT NOT NULL REFERENCES restaurants(id) ON DELETE CASCADE,
                                       waiter_id UUID NOT NULL,
                                       PRIMARY KEY (restaurant_id, waiter_id)
);

-- Create the 'restaurant_cook_ids' table
CREATE TABLE restaurant_cook_ids (
                                     restaurant_id BIGINT NOT NULL REFERENCES restaurants(id) ON DELETE CASCADE,
                                     cook_id UUID NOT NULL,
                                     PRIMARY KEY (restaurant_id, cook_id)
);

-- Create the enum type for table_status
CREATE TYPE table_status_enum AS ENUM ('AVAILABLE', 'OCCUPIED', 'RESERVED', 'CLEANING');

-- Create the 'restaurant_tables' table
CREATE TABLE restaurant_tables (
                                   id BIGSERIAL PRIMARY KEY,
                                   table_order INTEGER,
                                   restaurant_id BIGINT NOT NULL REFERENCES restaurants(id) ON DELETE CASCADE,
                                   status table_status_enum
);
