-- Description: Creates a housekeeper_availability table
-- SET search_path TO Housekeeper_service_DB;
SET search_path TO postgres

-- Create a type for time slot
CREATE TYPE time_slot AS (
    start_time time,
    end_time time
);