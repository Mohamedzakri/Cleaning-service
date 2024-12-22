-- Description: Creates a housekeeper_availability table
-- SET search_path TO Housekeeper_service_DB;
SET search_path TO postgres

        CREATE TABLE housekeeper_availability (
            id SERIAL PRIMARY KEY,
            housekeeper_id INTEGER NOT NULL,
            day day_of_week NOT NULL,
            time_slots time_slot[] NOT NULL,
            CONSTRAINT valid_time_slots CHECK (
                -- Ensure end time is after start time for each slot
                array_length(time_slots, 1) > 0 AND
                (time_slots::time_slot[])[1].end_time > (time_slots::time_slot[])[1].start_time
            )
);