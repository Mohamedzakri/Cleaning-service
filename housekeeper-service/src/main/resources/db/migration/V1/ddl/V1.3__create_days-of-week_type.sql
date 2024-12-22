-- Description: Creates a housekeeper_availability table
-- SET search_path TO Housekeeper_service_DB;
SET search_path TO postgres

-- Create custom types for better data integrity
CREATE TYPE day_of_week AS ENUM ('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY');