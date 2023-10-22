CREATE
    DATABASE project17;

USE
    project17;

CREATE TABLE roles
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL
);

INSERT INTO roles(role_name)
VALUES ('Admin'),
       ('Patient');

CREATE TABLE users
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    username  VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    phone     VARCHAR(255) NOT NULL,
    address   VARCHAR(555) NOT NULL,
    role_id   INT          NOT NULL,
    is_delete INT          NOT NULL DEFAULT 0,
    CONSTRAINT fk_users_role FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- bang dich vu
CREATE TABLE services
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    service_name VARCHAR(255)   NOT NULL,
    description  VARCHAR(1000)  NOT NULL,
    image        VARCHAR(1000)  NOT NULL,
    is_delete    INT            NOT NULL DEFAULT 0
);


-- bang dat lich
CREATE TABLE appointment
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    user_id          INT,
    fullName         VARCHAR(255),
    phone            VARCHAR(255),
    email            VARCHAR(255),
    service_id       INT,
    appointment_date DATE,
    description      VARCHAR(1000),
    status           ENUM ('pending', 'accepted', 'rejected', 'cancel') NOT NULL DEFAULT 'pending',
    CONSTRAINT fk_appointment_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_appointment_service FOREIGN KEY (service_id) REFERENCES services (id)
);


