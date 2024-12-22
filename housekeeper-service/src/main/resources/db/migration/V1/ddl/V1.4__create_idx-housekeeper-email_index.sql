-- Description: Creates a housekeeper_availability table
-- SET search_path TO Housekeeper_service_DB;
SET search_path TO postgres

-- Index for email searches
CREATE INDEX idx_housekeepers_email ON housekeepers(email);