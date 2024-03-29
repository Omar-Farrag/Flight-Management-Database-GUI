CREATE TABLE UTILITY_CONSUMPTION(
    UTILITY_ID CHAR(10) CONSTRAINT UT_CONS_UTILITY_ID_PK PRIMARY KEY CONSTRAINT UT_CONS_UTILITY_ID_FORM CHECK(UTILITY_ID LIKE 'U%'),
    ELECONS FLOAT CONSTRAINT UT_CONS_ELECONS_NONNEGATIVE CHECK (ELECONS >= 0) CONSTRAINT UT_CONS_ELECONS_NOTNULL NOT NULL,
    ELECHARGE DECIMAL(10, 2) CONSTRAINT UT_CONS_ELECHARGE_NONNEGATIVE CHECK (ELECHARGE >= 0) CONSTRAINT UT_CONS_ELECHARGE_NOTNULL NOT NULL,
    WATCONS FLOAT CONSTRAINT UT_CONS_WATCONS_NONNEGATIVE CHECK (WATCONS >= 0) CONSTRAINT UT_CONS_WATCONS_NOTNULL NOT NULL,
    WATCHARGE DECIMAL(10, 2) CONSTRAINT UT_CONS_WATCHARGE_NONNEGATIVE CHECK (WATCHARGE >= 0) CONSTRAINT UT_CONS_WATCHARGE_NOTNULL NOT NULL,
    WASTEDISPOSED FLOAT CONSTRAINT UT_CONS_WASTE_NONNEGATIVE CHECK (WASTEDISPOSED >= 0) CONSTRAINT UT_CONS_WASTE_NOTNULL NOT NULL,
    WASTECHARGE DECIMAL(10, 2) CONSTRAINT UT_CONS_WASCHARGE_NONNEGATIVE CHECK (WASTECHARGE >= 0) CONSTRAINT UT_CONS_WASCHARGE_NOTNULL NOT NULL
);

CREATE TABLE MAINTENANCE_REQUESTS(
    BILL_NUM CHAR(10),
    REQUEST_NUM CHAR(10) CONSTRAINT MAINT_REQ_REQ_NUM_PK PRIMARY KEY CONSTRAINT MAINT_REQ_REQ_NUM_FORM CHECK (REQUEST_NUM LIKE 'M%'),
    LEASE_NUM CHAR (10) CONSTRAINT MAINT_REQ_LEASE_NUM_NOTNULL NOT NULL,
    DESCRIPTION VARCHAR(1000),
    STATUS VARCHAR(200) CONSTRAINT MAINT_REQ_STATUS_NOTNULL NOT NULL,
    CHARGE DECIMAL(10, 2) CHECK (CHARGE >= 0),
    ASSIGNED_TECH VARCHAR(30)
);

CREATE TABLE BILLS(
    BILL_NUM CHAR(10) CONSTRAINT BILLS_BILL_NUM_PK PRIMARY KEY CONSTRAINT BILLS_BILL_NUM_FORM CHECK(BILL_NUM LIKE 'B%'),
    TOTAL_AMOUNT DECIMAL(10, 2) CONSTRAINT BILLS_TOTAL_AMOUNT_NOTNULL NOT NULL,
    UTILITY_ID CHAR(10) CONSTRAINT BILLS_UTILITY_ID_NOTNULL NOT NULL,
    LEASE_NUM CHAR(10) CONSTRAINT BILLS_LEASE_NUM_NOTNULL NOT NULL,
    DUE_DATE DATE CONSTRAINT BILLS_DUE_DATE_NOTNULL NOT NULL,
    PAID NUMBER(1) CONSTRAINT BILLS_PAID_NOTNULL NOT NULL
);

CREATE TABLE RECEIPTS(
    RECEIPT_NUM CHAR(10) CONSTRAINT RECEIPTS_RECEIPT_NUM_PK PRIMARY KEY CONSTRAINT RECEIPTS_RECEIPT_NUM_FORM CHECK(RECEIPT_NUM LIKE 'R%'),
    DATE_PAID DATE CONSTRAINT RECEIPTS_DATE_PAID_NOTNULL NOT NULL,
    BILL_NUM CHAR(10) CONSTRAINT RECEIPTS_BILL_NUM_NOTNULL NOT NULL,
    PAYER_ID VARCHAR(30) CONSTRAINT RECEIPTS_PAYER_ID_NOTNULL NOT NULL
);

CREATE TABLE DISCOUNTS(
    DISCOUNT_NUM CHAR(10) CONSTRAINT DISCOUNTS_DISCOUNT_NUM_PK PRIMARY KEY CONSTRAINT DISCOUNTS_DISCOUNT_NUM_FORM CHECK(DISCOUNT_NUM LIKE 'D%'),
    BILL_NUM CHAR(10) CONSTRAINT DISCOUNTS_BILL_NUM_NOTNULL NOT NULL,
    LEASE_PERCENTAGE FLOAT DEFAULT 0 CONSTRAINT DISC_LEASE_PER_RANGE CHECK ( LEASE_PERCENTAGE BETWEEN 0 AND 100 ) CONSTRAINT DISC_LEASE_PER_NOTNULL NOT NULL,
    UTILITY_PERCENTAGE FLOAT DEFAULT 0 CONSTRAINT DISC_UTIL_PER_RANGE CHECK ( UTILITY_PERCENTAGE BETWEEN 0 AND 100 ) CONSTRAINT DISC_UTIL_PER_NOTNULL NOT NULL,
    MAINTENANCE_PERCENTAGE FLOAT DEFAULT 0 CONSTRAINT DISC_MAINT_PER_RANGE CHECK ( MAINTENANCE_PERCENTAGE BETWEEN 0 AND 100 ) CONSTRAINT DISC_MAINT_PER_NOTNULL NOT NULL
);

CREATE TABLE LEASES(
    LEASE_NUM CHAR(10) CONSTRAINT LEASES_LEASE_NUM_PK PRIMARY KEY CONSTRAINT LEASES_LEASE_NUM_FORM CHECK(LEASE_NUM LIKE 'L%'),
    START_DATE DATE CONSTRAINT LEASES_START_DATE_NOTNULL NOT NULL,
    END_DATE DATE CONSTRAINT LEASES_END_DATE_NOTNULL NOT NULL,
    PAYMENT_OPTION VARCHAR(50) CONSTRAINT LEASES_PAYMENT_OPTION_ACCEPTED CHECK ( PAYMENT_OPTION IN ( 'MONTHLY', 'QUARTERLY', 'BI-ANNUALLY', 'ANNUALLY' ) ),
    LEASER_ID VARCHAR(30) CONSTRAINT LEASES_LEASER_ID_NOTNULL NOT NULL,
    LOCATION_NUM CHAR(10) CONSTRAINT LEASES_LOCATION_NUM_NOTNULL NOT NULL
);

CREATE TABLE LEASE_REQUESTS(
    LEASE_REQUEST_NUM CHAR(10) CONSTRAINT LEASE_REQS_LEASE_REQ_NUM_PK PRIMARY KEY CONSTRAINT LEASE_REQS_LEASE_REQ_NUM_FORM CHECK(LEASE_REQUEST_NUM LIKE 'LR%'),
    STATUS VARCHAR(200) CONSTRAINT LEASE_REQUESTS_STATUS_NOTNULL NOT NULL,
    PAYMENT_OPTION VARCHAR(50) CONSTRAINT LEASE_REQS_PAY_OPTIONS CHECK ( PAYMENT_OPTION IN ( 'MONTHLY', 'QUARTERLY', 'BI-ANNUALLY', 'ANNUALLY' ) ),
    LEASE_START DATE CONSTRAINT LEASE_REQS_LEASE_START_NOTNULL NOT NULL,
    LEASE_END DATE CONSTRAINT LEASE_REQS_LEASE_END_NOTNULL NOT NULL,
    LOCATION_NUM CHAR(10) CONSTRAINT LEASE_REQS_LOC_NUM_NOTNULL NOT NULL,
    APPLICANT_ID VARCHAR(30) CONSTRAINT LEASE_REQS_APP_ID_NOTNULL NOT NULL
);

CREATE TABLE APPOINTMENT_SLOTS(
    AGENT_ID VARCHAR(30) CONSTRAINT APP_SLOTS_AGENT_ID_NOTNULL NOT NULL,
    START_DATE DATE CONSTRAINT APP_SLOTS_START_DATE_NOTNULL NOT NULL,
    END_DATE DATE CONSTRAINT APP_SLOTS_END_DATE_NOTNULL NOT NULL
);

CREATE TABLE APPOINTMENTS(
    POTENTIAL_TENANT_ID VARCHAR(30) CONSTRAINT APP_PTEN_ID_NOTNULL NOT NULL,
    AGENT_ID VARCHAR(30) CONSTRAINT APP_AGENT_ID_NOTNULL NOT NULL,
    LOCATION_NUM CHAR(10) CONSTRAINT APP_LOCATION_NUM_NOTNULL NOT NULL,
    START_DATE DATE CONSTRAINT APP_START_DATE_NOTNULL NOT NULL,
    END_DATE DATE CONSTRAINT APP_END_DATE_NOTNULL NOT NULL
);

CREATE TABLE PROPERTIES(
    NAME VARCHAR(100),
    LOCATION_NUM CHAR(10) CONSTRAINT PROPERTIES_LOCATION_NUM_PK PRIMARY KEY,
    CLASS CHAR(1) CONSTRAINT PROPERTIES_CLASS_FORM CHECK(CLASS IN ('A', 'B', 'C', 'D')),
    SPACE FLOAT CONSTRAINT PROPERTIES_SPACE_NOTNULL NOT NULL,
    PURPOSE VARCHAR(100) CONSTRAINT PROPERTIES_PURPOSE_NOTNULL NOT NULL,
    MONTHLY_RATE DECIMAL(10, 2) CONSTRAINT PROPS_MON_RATE_NOTNULL NOT NULL,
    QUARTERLY_RATE DECIMAL(10, 2) CONSTRAINT PROPS_QUART_RATE_NOTNULL NOT NULL,
    BI_ANNUAL_RATE DECIMAL(10, 2) CONSTRAINT PROPS_BIANN_NOTNULL NOT NULL,
    ANNUAL_RATE DECIMAL(10, 2) CONSTRAINT PROPS_ANN_RATE_NOTNULL NOT NULL
);

CREATE TABLE LOCS(
    LOCATION_NUM CHAR(10) CONSTRAINT LOCATIONS_LOCATION_NUM_PK PRIMARY KEY,
    MALL_NUM CHAR(3) CONSTRAINT LOCATIONS_MALL_NUM_NOTNULL NOT NULL,
 /*[GFSTF] --> GROUND/FIRST/SECOND/THIRD/FOURTH FLOORS*/
    STORE_NUM CHAR(3) CONSTRAINT LOCATIONS_STORE_NUM_FORM CHECK (REGEXP_LIKE(STORE_NUM, '^[GFSTF]')) CONSTRAINT LOCATIONS_STORE_NUM_NOTNULL NOT NULL,
    CONSTRAINT LOCATIONS_UNIQUENESS UNIQUE(MALL_NUM, STORE_NUM)
);

CREATE TABLE MALLS(
    MALL_NUM CHAR(3) CONSTRAINT MALLS_MALL_NUM_PK PRIMARY KEY,
    NAME VARCHAR(100),
    ADDRESS VARCHAR(200) CONSTRAINT MALLS_ADDRESS_NOTNULL NOT NULL
);

CREATE TABLE USERS(
    USER_ID VARCHAR(30) CONSTRAINT USERS_USER_ID_PK PRIMARY KEY,
    FNAME VARCHAR(30) CONSTRAINT USERS_FNAME_NOTNULL NOT NULL,
    LNAME VARCHAR(30) CONSTRAINT USERS_LNAME_NOTNULL NOT NULL,
    PHONE_NUMBER CHAR(10) CONSTRAINT USERS_PHONE_NUMBER_NOTNULL NOT NULL,
    EMAIL_ADDRESS VARCHAR(50) CONSTRAINT USERS_EMAIL_ADDRESS_FORM CHECK (REGEXP_LIKE(EMAIL_ADDRESS,
    '^\w+\d*@\w+\d*\.\w{3}$')),
    ROLE_ID CHAR(2) CONSTRAINT USERS_ROLE_ID_NOTNULL NOT NULL
);

CREATE TABLE ROLES(
    ROLE_ID CHAR(2) CONSTRAINT ROLES_ROLE_ID_PK PRIMARY KEY,
    DESCRIPTION VARCHAR(100) CONSTRAINT ROLES_DESCRIPTION_NOTNULL NOT NULL
);

CREATE TABLE CREDENTIALS(
    USER_ID VARCHAR(30) CONSTRAINT CREDENTIALS_USER_ID_PK PRIMARY KEY,
    PASSWORD VARCHAR(50) CONSTRAINT CREDENTIALS_PASSWORD_NOTNULL NOT NULL
);

CREATE TABLE NOTIFICATIONS(
    SENDER_ID VARCHAR(30) CONSTRAINT NOTIF_SENDER_ID_NOTNULL NOT NULL,
    RECEIVER_ID VARCHAR(30) CONSTRAINT NOTIF_RECEIVER_ID_NOTNULL NOT NULL,
    DATE_SENT TIMESTAMP CONSTRAINT NOTIF_DATE_SENT_NOTNULL NOT NULL,
    MESSAGE VARCHAR(200) CONSTRAINT NOTIF_MESSAGE_NOTNULL NOT NULL,
    CONSTRAINT NOTIFICATIONS_PK PRIMARY KEY(SENDER_ID, DATE_SENT)
);

ALTER TABLE  APPOINTMENTS DROP CONSTRAINT APP_LOCATION_NUM_NOTNULL;

ALTER TABLE  APPOINTMENTS DROP CONSTRAINT APP_PTEN_ID_NOTNULL;

ALTER TABLE  MAINTENANCE_REQUESTS DROP CONSTRAINT MAINT_REQ_LEASE_NUM_NOTNULL;

ALTER TABLE  LEASES DROP CONSTRAINT LEASES_LEASER_ID_NOTNULL;

ALTER TABLE  DISCOUNTS DROP CONSTRAINT DISCOUNTS_BILL_NUM_NOTNULL;

ALTER TABLE  LEASES DROP CONSTRAINT LEASES_LOCATION_NUM_NOTNULL;

ALTER TABLE  USERS DROP CONSTRAINT USERS_ROLE_ID_NOTNULL;

ALTER TABLE  APPOINTMENT_SLOTS DROP CONSTRAINT APP_SLOTS_AGENT_ID_NOTNULL;

ALTER TABLE  RECEIPTS DROP CONSTRAINT RECEIPTS_BILL_NUM_NOTNULL;

ALTER TABLE  LEASE_REQUESTS DROP CONSTRAINT LEASE_REQS_LOC_NUM_NOTNULL;

ALTER TABLE  BILLS DROP CONSTRAINT BILLS_UTILITY_ID_NOTNULL;

ALTER TABLE  LEASE_REQUESTS DROP CONSTRAINT LEASE_REQS_APP_ID_NOTNULL;

ALTER TABLE  APPOINTMENTS DROP CONSTRAINT APP_AGENT_ID_NOTNULL;

ALTER TABLE  RECEIPTS DROP CONSTRAINT RECEIPTS_PAYER_ID_NOTNULL;

ALTER TABLE NOTIFICATIONS DROP CONSTRAINT NOTIF_RECEIVER_ID_NOTNULL;
ALTER TABLE NOTIFICATIONS DROP CONSTRAINT NOTIF_SENDER_ID_NOTNULL;

ALTER TABLE MAINTENANCE_REQUESTS ADD CONSTRAINT MAINT_REQUESTS_LEASE_NUM_FK FOREIGN KEY(LEASE_NUM) REFERENCES LEASES (LEASE_NUM);

ALTER TABLE MAINTENANCE_REQUESTS ADD CONSTRAINT MAINT_REQS_ASSIGNED_TECH_FK FOREIGN KEY(ASSIGNED_TECH) REFERENCES USERS (USER_ID);

ALTER TABLE BILLS ADD CONSTRAINT BILLS_UTILITY_ID_FK FOREIGN KEY(UTILITY_ID) REFERENCES UTILITY_CONSUMPTION(UTILITY_ID);

ALTER TABLE BILLS ADD CONSTRAINT BILLS_LEASE_NUM_FK FOREIGN KEY(LEASE_NUM) REFERENCES LEASES(LEASE_NUM);

ALTER TABLE RECEIPTS ADD CONSTRAINT RECEIPTS_BILL_NUM_FK FOREIGN KEY(BILL_NUM) REFERENCES BILLS(BILL_NUM);

ALTER TABLE RECEIPTS ADD CONSTRAINT RECEIPTS_PAYER_ID_FK FOREIGN KEY(PAYER_ID) REFERENCES USERS(USER_ID);

ALTER TABLE DISCOUNTS ADD CONSTRAINT DISCOUNTS_BILL_NUM_FK FOREIGN KEY(BILL_NUM) REFERENCES BILLS(BILL_NUM);

ALTER TABLE LEASES ADD CONSTRAINT LEASES_VALID_DATES CHECK (END_DATE > START_DATE);

ALTER TABLE LEASES ADD CONSTRAINT LEASES_LEASER_ID_FK FOREIGN KEY(LEASER_ID) REFERENCES USERS(USER_ID);

ALTER TABLE LEASES ADD CONSTRAINT LEASES_LOCATION_NUM_FK FOREIGN KEY(LOCATION_NUM) REFERENCES LOCS(LOCATION_NUM);

ALTER TABLE LEASE_REQUESTS ADD CONSTRAINT LEASE_REQUESTS_VALID_DATES CHECK (LEASE_END > LEASE_START);

ALTER TABLE LEASE_REQUESTS ADD CONSTRAINT LEASE_REQUESTS_LOCATION_NUM_FK FOREIGN KEY(LOCATION_NUM) REFERENCES LOCS (LOCATION_NUM);

ALTER TABLE LEASE_REQUESTS ADD CONSTRAINT LEASE_REQUESTS_APPLICANT_FK FOREIGN KEY(APPLICANT_ID) REFERENCES USERS (USER_ID);

ALTER TABLE APPOINTMENT_SLOTS ADD CONSTRAINT APP_SLOTS_AGENT_ID_FK FOREIGN KEY(AGENT_ID) REFERENCES USERS(USER_ID);

ALTER TABLE APPOINTMENTS ADD CONSTRAINT APP_PTENANT_FK FOREIGN KEY(POTENTIAL_TENANT_ID) REFERENCES USERS(USER_ID);

ALTER TABLE APPOINTMENTS ADD CONSTRAINT APP_AGENT_ID_FK FOREIGN KEY(AGENT_ID) REFERENCES USERS(USER_ID);

ALTER TABLE APPOINTMENTS ADD CONSTRAINT APP_LOCATION_NUM_FK FOREIGN KEY(LOCATION_NUM) REFERENCES LOCS(LOCATION_NUM);

ALTER TABLE PROPERTIES ADD CONSTRAINT PROPERTIES_LOCATION_NUM_FK FOREIGN KEY(LOCATION_NUM) REFERENCES LOCS(LOCATION_NUM);

ALTER TABLE USERS ADD CONSTRAINT USERS_ROLE_ID_FK FOREIGN KEY(ROLE_ID) REFERENCES ROLES(ROLE_ID);

ALTER TABLE CREDENTIALS ADD CONSTRAINT CRED_USER_ID_FK FOREIGN KEY(USER_ID) REFERENCES USERS(USER_ID);

ALTER TABLE NOTIFICATIONS ADD CONSTRAINT NOTIF_SENDER_ID_FK FOREIGN KEY(SENDER_ID) REFERENCES USERS(USER_ID);

ALTER TABLE NOTIFICATIONS ADD CONSTRAINT NOTIF_RECEIVER_ID_FK FOREIGN KEY(RECEIVER_ID) REFERENCES USERS(USER_ID);

CREATE OR REPLACE TRIGGER MAINT_REQUESTS_LEASE_NUM_TG
BEFORE UPDATE OF  LEASE_NUM ON LEASES
FOR EACH ROW
BEGIN
UPDATE MAINTENANCE_REQUESTS A SET
A.LEASE_NUM = :new.LEASE_NUM
WHERE A.LEASE_NUM = :old.LEASE_NUM;
end;

CREATE OR REPLACE TRIGGER MAINT_REQS_ASSIGNED_TECH_TG
BEFORE UPDATE OF  USER_ID ON USERS
FOR EACH ROW
BEGIN
UPDATE MAINTENANCE_REQUESTS A SET
A.ASSIGNED_TECH = :new.USER_ID
WHERE A.ASSIGNED_TECH = :old.USER_ID;
end;

CREATE OR REPLACE TRIGGER BILLS_UTILITY_ID_TG
BEFORE UPDATE OF UTILITY_ID ON UTILITY_CONSUMPTION
FOR EACH ROW
BEGIN
UPDATE BILLS A SET
A.UTILITY_ID = :new.UTILITY_ID
WHERE A.UTILITY_ID = :old.UTILITY_ID;
end;

CREATE OR REPLACE TRIGGER BILLS_LEASE_NUM_TG
BEFORE UPDATE OF LEASE_NUM ON LEASES
FOR EACH ROW
BEGIN
UPDATE BILLS A SET
A.LEASE_NUM = :new.LEASE_NUM
WHERE A.LEASE_NUM = :old.LEASE_NUM;
end;

CREATE OR REPLACE TRIGGER RECEIPTS_BILL_NUM_TG
BEFORE UPDATE OF BILL_NUM ON BILLS
FOR EACH ROW
BEGIN
UPDATE RECEIPTS A SET
A.BILL_NUM = :new.BILL_NUM
WHERE A.BILL_NUM = :old.BILL_NUM;
end;

CREATE OR REPLACE TRIGGER RECEIPTS_PAYER_ID_TG
BEFORE UPDATE OF USER_ID ON USERS
FOR EACH ROW
BEGIN
UPDATE RECEIPTS A SET
A.PAYER_ID = :new.USER_ID
WHERE A.PAYER_ID = :old.USER_ID;
end;

CREATE OR REPLACE TRIGGER DISCOUNTS_BILL_NUM_TG
BEFORE UPDATE OF BILL_NUM ON BILLS
FOR EACH ROW
BEGIN
UPDATE DISCOUNTS A SET
A.BILL_NUM = :new.BILL_NUM
WHERE A.BILL_NUM = :old.BILL_NUM;
end;

CREATE OR REPLACE TRIGGER LEASES_LEASER_ID_TG
BEFORE UPDATE OF USER_ID ON USERS
FOR EACH ROW
BEGIN
UPDATE LEASES A SET
A.LEASER_ID = :new.USER_ID
WHERE A.LEASER_ID = :old.USER_ID;
end;

CREATE OR REPLACE TRIGGER LEASES_LOCATION_NUM_TG
BEFORE UPDATE OF LOCATION_NUM ON LOCS
FOR EACH ROW
BEGIN
UPDATE LEASES A SET
A.LOCATION_NUM = :new.LOCATION_NUM
WHERE A.LOCATION_NUM = :old.LOCATION_NUM;
end;

CREATE OR REPLACE TRIGGER LEASE_REQUESTS_LOCATION_NUM_TG
BEFORE UPDATE OF  LOCATION_NUM ON LOCS
FOR EACH ROW
BEGIN
UPDATE LEASE_REQUESTS A SET
A.LOCATION_NUM = :new.LOCATION_NUM
WHERE A.LOCATION_NUM = :old.LOCATION_NUM;
end;

CREATE OR REPLACE TRIGGER LEASE_REQUESTS_APPLICANT_TG
BEFORE UPDATE OF  USER_ID ON USERS
FOR EACH ROW
BEGIN
UPDATE LEASE_REQUESTS A SET
A.APPLICANT_ID = :new.USER_ID
WHERE A.APPLICANT_ID = :old.USER_ID;
end;

CREATE OR REPLACE TRIGGER APP_SLOTS_AGENT_ID_TG
BEFORE UPDATE OF USER_ID ON USERS
FOR EACH ROW
BEGIN
UPDATE APPOINTMENT_SLOTS A SET
A.AGENT_ID = :new.USER_ID
WHERE A.AGENT_ID = :old.USER_ID;
end;

CREATE OR REPLACE TRIGGER APP_PTENANT_TG
BEFORE UPDATE OF USER_ID ON USERS
FOR EACH ROW
BEGIN
UPDATE APPOINTMENTS A SET
A.POTENTIAL_TENANT_ID = :new.USER_ID
WHERE A.POTENTIAL_TENANT_ID = :old.USER_ID;
end;

CREATE OR REPLACE TRIGGER APP_AGENT_ID_TG
BEFORE UPDATE OF USER_ID ON USERS
FOR EACH ROW
BEGIN
UPDATE APPOINTMENTS A SET
A.AGENT_ID = :new.USER_ID
WHERE A.AGENT_ID = :old.USER_ID;
end;

CREATE OR REPLACE TRIGGER APP_LOCATION_NUM_TG
BEFORE UPDATE OF LOCATION_NUM ON LOCS
FOR EACH ROW
BEGIN
UPDATE APPOINTMENTS A SET
A.LOCATION_NUM = :new.LOCATION_NUM
WHERE A.LOCATION_NUM = :old.LOCATION_NUM;
end;

CREATE OR REPLACE TRIGGER PROPERTIES_LOCATION_NUM_TG
BEFORE UPDATE OF LOCATION_NUM ON LOCS
FOR EACH ROW
BEGIN
UPDATE PROPERTIES A SET
A.LOCATION_NUM = :new.LOCATION_NUM
WHERE A.LOCATION_NUM = :old.LOCATION_NUM;
end;

CREATE OR REPLACE TRIGGER USERS_ROLE_ID_TG
BEFORE UPDATE OF ROLE_ID ON ROLES
FOR EACH ROW
BEGIN
UPDATE USERS A SET
A.ROLE_ID = :new.ROLE_ID
WHERE A.ROLE_ID = :old.ROLE_ID;
end;

CREATE OR REPLACE TRIGGER CRED_USER_ID_TG
BEFORE UPDATE OF USER_ID ON USERS
FOR EACH ROW
BEGIN
UPDATE CREDENTIALS A SET
A.USER_ID = :new.USER_ID
WHERE A.USER_ID = :old.USER_ID;
end;



INSERT INTO ROLES VALUES(
    'IT',
    'It Officer'
);

INSERT INTO ROLES VALUES(
    'DE',
    'Data Entry'
);

INSERT INTO ROLES VALUES(
    'MD',
    'Maintenance Department Officer'
);

INSERT INTO ROLES VALUES(
    'TC',
    'Technician'
);

INSERT INTO ROLES VALUES(
    'LA',
    'Leasing Agent'
);

INSERT INTO ROLES VALUES(
    'CT',
    'Current Tenant'
);

INSERT INTO ROLES VALUES(
    'PT',
    'Potential Tenant'
);

INSERT INTO ROLES VALUES(
    'AC',
    'Accountant'
);

INSERT INTO USERS VALUES(
    'A1',
    'Layth',
    'Al Khairulla',
    '0501234567',
    'Layth@RealEstate.edu',
    'IT'
);

INSERT INTO USERS VALUES(
    'A2',
    'Omar',
    'Farrag',
    '0503214567',
    'Omar@RealEstate.edu',
    'DE'
);

INSERT INTO USERS VALUES(
    'A3',
    'Ahmed',
    'Wael',
    '0508894567',
    'Ahmed@RealEstate.edu',
    'MD'
);

INSERT INTO USERS VALUES(
    'A4',
    'Hassan',
    'Shafei',
    '0503434567',
    'Hassan@RealEstate.edu',
    'TC'
);

INSERT INTO USERS VALUES(
    'A5',
    'Waleed',
    'Al Mwallem',
    '0505554567',
    'Waleed@RealEstate.edu',
    'LA'
);

INSERT INTO USERS VALUES(
    'A6',
    'Mohamed',
    'Al Zinati',
    '0501244567',
    'Mohamed@RealEstate.edu',
    'CT'
);

INSERT INTO USERS VALUES(
    'A7',
    'Zayn',
    'Mansoori',
    '0501054567',
    'Zayn@RealEstate.edu',
    'PT'
);

INSERT INTO USERS VALUES(
    'A8',
    'Hala',
    'Ehsan',
    '0501904567',
    'Hala@RealEstate.edu',
    'AC'
);

INSERT INTO CREDENTIALS VALUES(
    'A1',
    'A1'
);

INSERT INTO CREDENTIALS VALUES(
    'A2',
    'A2'
);

INSERT INTO CREDENTIALS VALUES(
    'A3',
    'A3'
);

INSERT INTO CREDENTIALS VALUES(
    'A4',
    'A4'
);

INSERT INTO CREDENTIALS VALUES(
    'A5',
    'A5'
);

INSERT INTO CREDENTIALS VALUES(
    'A6',
    'A6'
);

INSERT INTO CREDENTIALS VALUES(
    'A7',
    'A7'
);

INSERT INTO CREDENTIALS VALUES(
    'A8',
    'A8'
);

SELECT *
FROM USERS;

SELECT *
FROM ROLES;

SELECT *
FROM CREDENTIALS;

/*
 ___________________________________________________________________________________________________________________
 |-------------------------------------------------------------------------------------------------------------------|
 |-------------------------------------------------------------------------------------------------------------------|
 |--------------------------------TO DELETE EVERYTHING AND START FROM SCRATCH----------------------------------------|
 |-------------------------------------------------------------------------------------------------------------------|
 |-------------------------------------------------------------------------------------------------------------------|
 |___________________________________________________________________________________________________________________|
 
DROP TRIGGER MAINT_REQUESTS_LEASE_NUM_TG;

DROP TRIGGER MAINT_REQS_ASSIGNED_TECH_TG;

DROP TRIGGER BILLS_UTILITY_ID_TG;

DROP TRIGGER BILLS_LEASE_NUM_TG;

DROP TRIGGER RECEIPTS_BILL_NUM_TG;

DROP TRIGGER RECEIPTS_PAYER_ID_TG;

DROP TRIGGER DISCOUNTS_BILL_NUM_TG;

DROP TRIGGER LEASES_LEASER_ID_TG;

DROP TRIGGER LEASES_LOCATION_NUM_TG;

DROP TRIGGER LEASE_REQUESTS_LOCATION_NUM_TG;

DROP TRIGGER LEASE_REQUESTS_APPLICANT_TG;

DROP TRIGGER APP_SLOTS_AGENT_ID_TG;

DROP TRIGGER APP_PTENANT_TG;

DROP TRIGGER APP_AGENT_ID_TG;

DROP TRIGGER APP_LOCATION_NUM_TG;

DROP TRIGGER PROPERTIES_LOCATION_NUM_TG;

DROP TRIGGER USERS_ROLE_ID_TG;

DROP TRIGGER CRED_USER_ID_TG;

ALTER TABLE MAINTENANCE_REQUESTS DROP CONSTRAINT MAINT_REQUESTS_LEASE_NUM_FK;

ALTER TABLE MAINTENANCE_REQUESTS DROP CONSTRAINT MAINT_REQS_ASSIGNED_TECH_FK;

ALTER TABLE BILLS DROP CONSTRAINT BILLS_UTILITY_ID_FK;

ALTER TABLE BILLS DROP CONSTRAINT BILLS_LEASE_NUM_FK;

ALTER TABLE RECEIPTS DROP CONSTRAINT RECEIPTS_BILL_NUM_FK;

ALTER TABLE RECEIPTS DROP CONSTRAINT RECEIPTS_PAYER_ID_FK;

ALTER TABLE DISCOUNTS DROP CONSTRAINT DISCOUNTS_BILL_NUM_FK;

ALTER TABLE LEASES DROP CONSTRAINT LEASES_VALID_DATES;

ALTER TABLE LEASES DROP CONSTRAINT LEASES_LEASER_ID_FK;

ALTER TABLE LEASES DROP CONSTRAINT LEASES_LOCATION_NUM_FK;

ALTER TABLE LEASE_REQUESTS DROP CONSTRAINT LEASE_REQUESTS_VALID_DATES;

ALTER TABLE LEASE_REQUESTS DROP CONSTRAINT LEASE_REQUESTS_LOCATION_NUM_FK;

ALTER TABLE LEASE_REQUESTS DROP CONSTRAINT LEASE_REQUESTS_APPLICANT_FK;

ALTER TABLE APPOINTMENT_SLOTS DROP CONSTRAINT APP_SLOTS_AGENT_ID_FK;

ALTER TABLE APPOINTMENTS DROP CONSTRAINT APP_PTENANT_FK;

ALTER TABLE APPOINTMENTS DROP CONSTRAINT APP_AGENT_ID_FK;

ALTER TABLE APPOINTMENTS DROP CONSTRAINT APP_LOCATION_NUM_FK;

ALTER TABLE PROPERTIES DROP CONSTRAINT PROPERTIES_LOCATION_NUM_FK;

ALTER TABLE USERS DROP CONSTRAINT USERS_ROLE_ID_FK;

ALTER TABLE CREDENTIALS DROP CONSTRAINT CRED_USER_ID_FK;

DROP TABLE UTILITY_CONSUMPTION;

DROP TABLE MAINTENANCE_REQUESTS;

DROP TABLE BILLS;

DROP TABLE RECEIPTS;

DROP TABLE DISCOUNTS;

DROP TABLE LEASES;

DROP TABLE LEASE_REQUESTS;

DROP TABLE APPOINTMENT_SLOTS;

DROP TABLE APPOINTMENTS;

DROP TABLE PROPERTIES;

DROP TABLE LOCS;

DROP TABLE MALLS;

DROP TABLE USERS;

DROP TABLE ROLES;

DROP TABLE CREDENTIALS;

DROP TABLE NOTIFICATIONS;

*/