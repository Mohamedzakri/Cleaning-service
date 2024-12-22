-- Description: Creates a housekeeper_availability table
-- SET search_path TO Housekeeper_service_DB;
SET search_path TO postgres

-- Index for rating searches
CREATE INDEX idx_housekeepers_rating ON housekeepers(rating);