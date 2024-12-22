-- Description: Creates a trigger function to automatically update updated_at columns
-- SET search_path TO Housekeeper_service_DB;
SET search_path TO postgres


CREATE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS $$
    BEGIN
        NEW.updated_at = CURRENT_TIMESTAMP;
        RETURN NEW;
   END;
$$ language 'plpgsql';