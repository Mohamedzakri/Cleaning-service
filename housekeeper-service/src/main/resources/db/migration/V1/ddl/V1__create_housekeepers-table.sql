-- Description: Creates a housekeeper_availability table
SET search_path TO Housekeeper_service_DB;
-- Create main housekeeper table
CREATE TABLE housekeepers (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    rating DECIMAL(3,2) CHECK (rating >= 0 AND rating <= 5),
    is_premium BOOLEAN DEFAULT false,
    -- Using PostGIS for geographical data
    service_area geography(POLYGON),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    -- Constraints
    CONSTRAINT valid_phone CHECK (phone_number ~ '^\+?[0-9\s-()]+$'),
    CONSTRAINT valid_email CHECK (email ~ '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$')
);