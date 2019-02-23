DROP TABLE IF EXISTS customer_owned_account;
DROP TABLE IF EXISTS bank_registered_customer;
DROP TABLE IF EXISTS customer_transactione_with_bank;
DROP TABLE IF EXISTS customer_access_with_bank;
DROP TABLE IF EXISTS account_transactione;
DROP TABLE IF EXISTS bank;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS transactione;

CREATE TABLE bank(
    bankid SERIAL PRIMARY KEY NOT NULL,
    bankname VARCHAR(250) NOT NULL
);

CREATE TABLE customer(
    custid SERIAL PRIMARY KEY NOT NULL,
    firstname VARCHAR(250) NOT NULL,
    middlename VARCHAR(250),
    lastname VARCHAR(250) NOT NULL,
	ssn INT UNIQUE NOT NULL
);

CREATE TABLE account(
    acntid SERIAL PRIMARY KEY NOT NULL,
    acntname VARCHAR(250) NOT NULL,
	balance NUMERIC(12,2) NOT NULL
);

CREATE TABLE transactione(
    transid SERIAL PRIMARY KEY NOT NULL,
    description VARCHAR(1000) NOT NULL,
    form VARCHAR(20) NOT NULL,
    amount NUMERIC(12, 2)
);

CREATE TABLE customer_access_with_bank(
    custid INT REFERENCES customer ON DELETE CASCADE,
	bankid INT REFERENCES bank ON DELETE CASCADE,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR NOT NULL,
	salt VARCHAR NOT NULL
);

CREATE TABLE customer_transactione_with_bank(
    custid INT REFERENCES customer ON DELETE CASCADE,
    bankid INT REFERENCES bank ON DELETE CASCADE,
    transid INT REFERENCES transactione ON DELETE CASCADE,
	datecreated TIMESTAMP WITH TIME ZONE
);

CREATE TABLE account_transactione(
    acntid INT REFERENCES account ON DELETE CASCADE,
    transid INT REFERENCES transactione ON DELETE CASCADE,
    datecreated TIMESTAMP WITH TIME ZONE
);

CREATE TABLE bank_registered_customer(
    bankid INT REFERENCES bank ON DELETE CASCADE,
    custid INT REFERENCES customer ON DELETE CASCADE,
	datecreated TIMESTAMP WITH TIME ZONE
);

CREATE TABLE customer_owned_account(
    custid INT REFERENCES customer ON DELETE CASCADE,
    acntid INT REFERENCES account ON DELETE CASCADE,
	datecreated TIMESTAMP WITH TIME ZONE
);

DROP FUNCTION getCustomerTransactions;
CREATE OR REPLACE FUNCTION getCustomerTransactions(_custid INTEGER, _bankid INTEGER, _acntid INTEGER)
RETURNS TABLE(transid INTEGER ,description VARCHAR,form VARCHAR,amount NUMERIC(12,2))
AS $$
BEGIN
	RETURN QUERY																											  
		SELECT * 
		FROM transactione
		WHERE transactione.transid = ANY (
			SELECT customer_transactione_with_bank.transid
			FROM customer_transactione_with_bank
			WHERE bankid = _bankid AND custid = ANY (
				SELECT custid
				FROM customer
				WHERE custid = ANY (
					SELECT custid
					FROM customer_owned_account
					WHERE custid = _custid AND acntid = _acntid)));
END;
$$ LANGUAGE plpgsql;
					
INSERT INTO bank(bankid,bankname) VALUES (1,'Wells Fargo');
