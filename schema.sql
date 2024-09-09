CREATE TYPE transport_type AS ENUM ('PLANE', 'TRAIN', 'BUS');
CREATE TYPE partner_status  AS ENUM ('ACTIVE', 'DISABLED', 'SUSPENDED');
CREATE TYPE contract_status AS ENUM ('PENDING', 'DONE', 'SUSPENDED', 'IN_PROGRESS');
CREATE TYPE reduction_type AS ENUM ('PERCENTAGE', 'FIXED_PRICE');
CREATE TYPE offer_status AS ENUM ('ACTIVE', 'SUSPENDED', 'EXPIRED');
CREATE TYPE ticket_status AS ENUM ('SOLD', 'CANCELED', 'PENDING');

CREATE TABLE IF NOT EXISTS partners (
    id UUID,
    name VARCHAR(255) NOT NULL,
    commercial_name VARCHAR(255) NOT NULL,
    commercial_phoneNumber VARCHAR(255) NOT NULL,
    commercial_email VARCHAR(255) NOT NULL,
    geographical_area VARCHAR(255),
    special_condition VARCHAR(255),
    transport_type transport_type,
    partner_status partner_status,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS contracts (
    id UUID,
    partner_id UUID NOT NULL,
    special_priceAmount Float,
    agreement_condition TEXT,
    renewable BOOL,
    started_at TIMESTAMP ,
    ends_at TIMESTAMP ,
    contract_status contract_status,

    PRIMARY KEY (id),
    FOREIGN KEY (partner_id) REFERENCES partners(id)
);


CREATE TABLE IF NOT EXISTS discounts (
    id UUID,
    contract_id UUID,
    name VARCHAR(255),
    description TEXT,
    reduction_value FLOAT,
    conditions TEXT,
    started_at TIMESTAMP ,
    ENDS_AT TIMESTAMP ,
    reduction_type reduction_type,
    offer_status offer_status,

    PRIMARY KEY (id),
    FOREIGN KEY (contract_id) REFERENCES contracts(id)
);

CREATE TABLE IF NOT EXISTS tickets (
    id UUID,
    contract_id UUID,
    journey_id UUID,
    selling_price_amount FLOAT,
    selling_price_currency VARCHAR(3),
    purchase_price_amount FLOAT,
    purchase_price_currency VARCHAR(3),
    selling_date TIMESTAMP ,
    journey_start_date TIMESTAMP,
    journey_end_date TIMESTAMP,
    ticket_status ticket_status,
    transport_type transport_type,

    PRIMARY KEY (id),
    FOREIGN KEY (contract_id) REFERENCES contracts(id),
    FOREIGN KEY (journey_id) REFERENCES journeys(id)
);

CREATE TABLE IF NOT EXISTS stations (
    id UUID,
    name VARCHAR(60),
    city VARCHAR(30),
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP ,
    deleted_at TIMESTAMP ,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS journeys(
    id UUID,
    start_id UUID,
    end_id UUID,
    distance INTEGER,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP ,
    deleted_at TIMESTAMP ,

    PRIMARY KEY (id),
    FOREIGN KEY (start_id) REFERENCES stations(id),
    FOREIGN KEY (end_id) REFERENCES stations(id)
);