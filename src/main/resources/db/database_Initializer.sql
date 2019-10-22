DROP TABLE IF EXISTS request ;
DROP TABLE IF EXISTS estimate ;
DROP TABLE IF EXISTS offer_categories;
DROP TABLE IF EXISTS offer ;
DROP TABLE IF EXISTS category ;
DROP TABLE IF EXISTS user_roles ;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                 NOT NULL,
    city             VARCHAR                 NOT NULL,
    photo              VARCHAR                         ,
    email            VARCHAR                 NOT NULL,
    password         VARCHAR                 NOT NULL,
    registered       TIMESTAMP DEFAULT now() NOT NULL,
    enabled          BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);


CREATE TABLE category
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                 NOT NULL,
    type             VARCHAR                 NOT NULL,
    unit             VARCHAR                 NOT NULL
);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE offer
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    cost             INTEGER NOT NULL ,
    amount           INTEGER NOT NULL ,
    buy_offer        BOOLEAN NOT NULL ,
    date_time        TIMESTAMP DEFAULT now() NOT NULL,
    description      VARCHAR NOT NULL,
    offerer_id       INTEGER NOT NULL,
    category_id       INTEGER NOT NULL,
    UNIQUE (offerer_id,description) ,
    FOREIGN KEY (offerer_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE

);

CREATE TABLE request
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    responced        BOOLEAN NOT NULL DEFAULT false,
    message          VARCHAR NOT NULL,
    offer_id         INTEGER NOT NULL,
    requester_id     INTEGER NOT NULL,
    UNIQUE (offer_id,requester_id) ,
    FOREIGN KEY (offer_id) REFERENCES offer (id) ON DELETE CASCADE,
    FOREIGN KEY (requester_id) REFERENCES users (id) ON DELETE CASCADE

);
CREATE TABLE estimate
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    comment          VARCHAR NOT NULL,
    stars            INTEGER NOT NULL ,
    date_time        TIMESTAMP DEFAULT now() NOT NULL,
    estimated_id     INTEGER NOT NULL,
    estimator_id     INTEGER NOT NULL,
    FOREIGN KEY (estimated_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (estimator_id) REFERENCES users (id) ON DELETE CASCADE

);





