CREATE TRIGGER advertisement_logger
    AFTER INSERT OR UPDATE OR DELETE
    ON advertisement
    FOR EACH ROW
EXECUTE PROCEDURE log_advertisement();

CREATE TRIGGER advertisement_autofill
    BEFORE INSERT OR UPDATE
    ON advertisement
    FOR EACH ROW
EXECUTE PROCEDURE autofill_change_dates();


CREATE TRIGGER car_param_logger
    AFTER INSERT OR UPDATE OR DELETE
    ON car_param
    FOR EACH ROW
EXECUTE PROCEDURE log_car_param();

CREATE TRIGGER car_param_autofill
    BEFORE INSERT OR UPDATE
    ON car_param
    FOR EACH ROW
EXECUTE PROCEDURE autofill_change_dates();


CREATE TRIGGER seller_logger
    AFTER INSERT OR UPDATE OR DELETE
    ON seller
    FOR EACH ROW
EXECUTE PROCEDURE log_seller();

CREATE TRIGGER seller_autofill
    BEFORE INSERT OR UPDATE
    ON seller
    FOR EACH ROW
EXECUTE PROCEDURE autofill_change_dates();
