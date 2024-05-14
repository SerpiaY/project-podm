SET schema 'myschema';
DROP INDEX IF EXISTS IX_Rent;
DROP TABLE IF EXISTS CUSTOMER;
DROP TABLE IF EXISTS BRANCH;
DROP TABLE IF EXISTS VEHICLE;
DROP TABLE IF EXISTS PAYMENT;
DROP TABLE IF EXISTS RENT_BILL;
DROP PROCEDURE IF EXISTS rent_vehicle ();
DROP FUNCTION IF EXISTS vehicle_returned ();
DROP TRIGGER IF EXISTS vehicle_returned ON RENT_BILL;


CREATE TABLE CUSTOMER (
    customerid serial UNIQUE NOT NULL,
    firstname varchar(128) NOT NULL,
    lastname varchar(128) NOT NULL,
    dateBirth date NOT NULL,
	username varchar(20) NOT NULL,
	password varchar(20) NOT NULL,
    licenseNumber varchar(128) NOT NULL,
	phone varchar(20) NOT NULL,
    PRIMARY KEY (customerid)
);

CREATE TABLE BRANCH (
    branchid serial UNIQUE NOT NULL,
    availablecar int,
    address varchar(2000),
    PRIMARY KEY (branchid)
);

CREATE TABLE VEHICLE (
    vehicleid serial UNIQUE NOT NULL,
    brand varchar(13),
    dateBought date,
    branchid int NOT NULL,
    isavailable bool NOT NULL,
	costperday numeric(15, 2),
    PRIMARY KEY (vehicleid),
    FOREIGN KEY (branchid) REFERENCES BRANCH (branchid)
);

CREATE TABLE RENTBILL (
    rentid serial UNIQUE NOT NULL,
    vehicleid int NOT NULL,
    tripduration int NOT NULL,
    customerid int NOT NULL,
    isreturned bool,
    daterented date NOT NULL DEFAULT CURRENT_DATE,
    datereturned date,
    PRIMARY KEY (rentid),
    FOREIGN KEY (vehicleid) REFERENCES VEHICLE (vehicleid),
    FOREIGN KEY (customerid) REFERENCES CUSTOMER (customerid)
);

CREATE TABLE PAYMENT (
    rentid int NOT NULL,
    paymentamount numeric(15, 2) NOT NULL,
    paymentdate date NOT NULL,
    PRIMARY KEY (paymentid)
);

CREATE TABLE RENT_VEHICLE (
	rentid int NOT NULL,
	vehicleid int NOT NULL,
	PRIMARY KEY (rentid, vehicleid),
	FOREIGN KEY (vehicleid) REFERENCES VEHICLE (vehicleid),
	FOREIGN KEY (rentid) REFERENCES RENTBILL (rentid)
);

CREATE TABLE RENT_PAYMENT (
	rentid int NOT NULL,
	paymentid int NOT NULL,
	PRIMARY KEY (rentid, paymentid),
	FOREIGN KEY (rentid) REFERENCES RENTBILL (rentid),
	FOREIGN KEY (paymentid) REFERENCES PAYMENT (paymentid)
);

CREATE UNIQUE INDEX IX_Rent
ON RENT_BILL (customerid, isreturned)
WHERE
    isreturned = FALSE;


/*
PROCEDURE rent_vehicle()
*/
CREATE OR REPLACE PROCEDURE rent_vehicle (vehicleID int, customerID int, duration int, rentdate date)
LANGUAGE plpgsql
AS $$
BEGIN
  /* Check vehicle availability and customer active rentals */
  IF (
      SELECT isavailable
      FROM VEHICLE
      WHERE vehicleid = vehicleID
    ) AND NOT (
      SELECT count(*)
      FROM RENT_BILL
      WHERE customerid = customerID AND isreturned = FALSE
    ) > 0 THEN

    /* Update vehicle availability and branch parking spaces */
    UPDATE VEHICLE
    SET isavailable = FALSE
    WHERE vehicleid = vehicleID;

    UPDATE BRANCH
    SET availablecar = availablecar - 1  -- Adjust if car leaves branch
    WHERE branchid = (
      SELECT branchid
      FROM VEHICLE
      WHERE vehicleid = vehicleID
    );

    /* Insert new rental record */
    INSERT INTO RENT_BILL (vehicle_id, trip_duration, customer_id, is_returned, date_rented)
    VALUES (vehicleID, duration, customerID, FALSE, rentdate);

    /* Success message */
    RAISE NOTICE 'Vehicle rented successfully!';

  ELSE
    /* Raise exception with informative message */
    RAISE EXCEPTION 'Vehicle unavailable or customer already has an active rental.';
  END IF;
END;
$$;

/*
FUNCTION: vehicle_returned()
*/
CREATE OR REPLACE FUNCTION vehicle_returned ()
  RETURNS TRIGGER
  LANGUAGE plpgsql
AS $$
BEGIN
  IF NEW.isreturned AND NEW.tripduration > 0 THEN
    -- Update payment (if rental duration > 0)
    INSERT INTO PAYMENT (rentid, paymentamount, paymentdate)
    VALUES 
	(NEW.rentid, (
      SELECT (NEW.tripduration) * costperday
      FROM VEHICLE
      WHERE vehicleid = NEW.vehicleid),
	 NEW.datereturned);  -- Check for valid duration

    -- Update vehicle availability and branch parking spaces
    UPDATE VEHICLE
    SET isavailable = TRUE
    WHERE vehicleid = NEW.vehicleid;
	
	UPDATE BRANCH
	SET availablecar = availablecar + 1
	WHERE branchid = (
		SELECT branchid
		FROM VEHICLE
		WHERE vehicleid = NEW.vehicleid);
  END IF;

  RETURN NEW;
END;
$$;


/*
TRIGGER:
This trigger calls the function car_returned() for each row that gets updated in the table RENT.
*/
CREATE TRIGGER vehicle_returned
    AFTER UPDATE ON RENT_BILL
    FOR EACH ROW
    EXECUTE FUNCTION vehicle_returned ();