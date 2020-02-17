CREATE OR REPLACE FUNCTION log_advertisement() RETURNS TRIGGER AS
$$
BEGIN
    IF TG_OP = 'INSERT' OR TG_OP = 'UPDATE' THEN
        INSERT INTO advertisement_log(change_type, id, link, price, seller_phone_number, creation_date, change_date)
        VALUES (TG_OP, NEW.*);
        RETURN NEW;
    ELSIF TG_OP = 'DELETE' THEN
        OLD.change_date := current_timestamp;
        INSERT INTO advertisement_log(change_type, id, link, price, seller_phone_number, creation_date, change_date)
        VALUES (TG_OP, OLD.*);
        RETURN OLD;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION log_car_param() RETURNS TRIGGER AS
$$
BEGIN
    IF TG_OP = 'INSERT' OR TG_OP = 'UPDATE' THEN
        INSERT INTO car_param_log(change_type, id, color, defects, drive_train, engine, first_registration_country,
                                  fuel_type, licence_plate, make, mileage_km, model, vin_code, years_prod,
                                  advertisement_id, creation_date, change_date)
        VALUES (TG_OP, NEW.*);
        RETURN NEW;
    ELSIF TG_OP = 'DELETE' THEN
        OLD.change_date := current_timestamp;
        INSERT INTO car_param_log(change_type, id, color, defects, drive_train, engine, first_registration_country,
                                  fuel_type, licence_plate, make, mileage_km, model, vin_code, years_prod,
                                  advertisement_id, creation_date, change_date)
        VALUES (TG_OP, OLD.*);
        RETURN OLD;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION log_seller() RETURNS TRIGGER AS
$$
BEGIN
    IF TG_OP = 'INSERT' OR TG_OP = 'UPDATE' THEN
        INSERT INTO seller_log(change_type, phone_number, seller_location, creation_date, change_date)
        VALUES (TG_OP, NEW.*);
        RETURN NEW;
    ELSIF TG_OP = 'DELETE' THEN
        OLD.change_date := current_timestamp;
        INSERT INTO seller_log(change_type, phone_number, seller_location, creation_date, change_date)
        VALUES (TG_OP, OLD.*);
        RETURN OLD;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION autofill_change_dates() RETURNS TRIGGER AS
$$
BEGIN
    NEW.change_date := current_timestamp;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
