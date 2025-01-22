CONNECT TO EBUY;


CREATE SCHEMA "EBUY";

------------------------------------------------
-- DDL Statements for table "EBUY"."BOOK"
------------------------------------------------
CREATE TABLE "EBUY"."BOOK"  (
		  "ISBN" INTEGER NOT NULL , 
		  "TITLE" VARCHAR(254) NOT NULL , 
		  "PRICE" DOUBLE WITH DEFAULT 0 )   
		 IN "USERSPACE1" ; 

ALTER TABLE "EBUY"."BOOK" 
	ADD CONSTRAINT "CC1353574580668" PRIMARY KEY
		("ISBN");

------------------------------------------------
-- DDL Statements for table "EBUY"."ORDER"
------------------------------------------------
CREATE TABLE "EBUY"."USER"  (
		  "USERNAME" VARCHAR(254) NOT NULL , 
		  "PASSWORD" VARCHAR(254) NOT NULL )   
		 IN "USERSPACE1" ; 

ALTER TABLE "EBUY"."USER" 
	ADD CONSTRAINT "CC1353990237605" PRIMARY KEY
		("USERNAME");

------------------------------------------------
-- DDL Statements for table "EBUY    "."ORDER"
------------------------------------------------
CREATE TABLE "EBUY"."ORDER"  (
		  "ID" BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (  
		    START WITH +1  
		    INCREMENT BY +1  
		    MINVALUE +1  
		    MAXVALUE +9223372036854775807  
		    NO CYCLE  
		    NO CACHE  
		    NO ORDER ) , 
		  "ISBN" INTEGER NOT NULL , 
		  "QUANTITY" INTEGER NOT NULL , 
		  "USERNAME" VARCHAR(254) NOT NULL , 
		  "TIME" TIMESTAMP NOT NULL , 
		  "ISPAID" INTEGER NOT NULL WITH DEFAULT 0 )   
		 IN "USERSPACE1" ; 


-- DDL Statements for primary key on Table "EBUY"."ORDER"

ALTER TABLE "EBUY"."ORDER" 
	ADD CONSTRAINT "CC1353575359933" PRIMARY KEY
		("ID");


ALTER TABLE "EBUY"."ORDER" ALTER COLUMN "ID" RESTART WITH 15;



Insert into ebuy.book values('89726372','Sams Teach Yourself Java 2 in 24 Hours','35.5');
Insert into ebuy.book values('67243984','Courtly Art Of The Ancient MAYA','20.6');
Insert into ebuy.book values('82767641','The Best CEOs','50');
Insert into ebuy.book values('19837674','Toes, Ears, Nose!','12.2');




---------------------------------------
-- Authorization statement on table space 
---------------------------------------

 
GRANT USE OF TABLESPACE "SYSTOOLSTMPSPACE" TO  PUBLIC   ;

COMMIT WORK;

CONNECT RESET;

TERMINATE;

;