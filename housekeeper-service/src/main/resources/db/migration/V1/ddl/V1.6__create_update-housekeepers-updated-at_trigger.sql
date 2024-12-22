-- Description: Creates a housekeeper_availability table
-- SET search_path TO Housekeeper_service_DB;
SET search_path TO postgres

CREATE TRIGGER update_housekeepers_updated_at
    BEFORE UPDATE ON housekeepers
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();