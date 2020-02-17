CREATE TABLE public.seller
(
    phone_number    character varying(255) NOT NULL,
    seller_location character varying(255),
    creation_date   timestamp with time zone default current_timestamp,
    change_date     timestamp with time zone,
    PRIMARY KEY (phone_number)
);

CREATE TABLE public.seller_log
(
    phone_number    character varying(255) NOT NULL,
    seller_location character varying(255),
    change_type     text,
    creation_date   timestamp with time zone default current_timestamp,
    change_date     timestamp with time zone,
    db_user         text                     default session_user
);

CREATE TABLE public.advertisement
(
    id                  character varying(255),
    link                character varying(255),
    price               integer                  NOT NULL,
    seller_phone_number character varying(255),
    creation_date       timestamp with time zone default current_timestamp,
    change_date         timestamp with time zone NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (seller_phone_number)
        REFERENCES public.seller (phone_number) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.advertisement_log
(
    id                  character varying(255) NOT NULL,
    link                character varying(255),
    price               integer                NOT NULL,
    seller_phone_number character varying(255),
    creation_date       timestamp with time zone default current_timestamp,
    change_date         timestamp with time zone,
    change_type         text,
    db_user             text                     default session_user
);

CREATE TABLE public.car_param
(
    advertisement_id           character varying(255),
    id                         character varying(255) NOT NULL,
    make                       character varying(255),
    model                      character varying(255),
    vin_code                   character varying(255),
    licence_plate              character varying(255),
    years_prod                 character varying(255),
    mileage_km                 character varying(255),
    color                      character varying(255),
    defects                    character varying(255),
    drive_train                character varying(255),
    engine                     character varying(255),
    first_registration_country character varying(255),
    fuel_type                  character varying(255),
    creation_date              timestamp with time zone default current_timestamp,
    change_date                timestamp with time zone,
    PRIMARY KEY (id),
    FOREIGN KEY (advertisement_id)
        REFERENCES public.advertisement (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.car_param_log
(
    advertisement_id           character varying(255),
    id                         character varying(255) NOT NULL,
    make                       character varying(255),
    model                      character varying(255),
    vin_code                   character varying(255),
    licence_plate              character varying(255),
    years_prod                 character varying(255),
    mileage_km                 character varying(255),
    color                      character varying(255),
    defects                    character varying(255),
    drive_train                character varying(255),
    engine                     character varying(255),
    first_registration_country character varying(255),
    fuel_type                  character varying(255),
    change_type                text,
    creation_date              timestamp with time zone default current_timestamp,
    change_date                timestamp with time zone,
    db_user                    text                     default session_user
);
