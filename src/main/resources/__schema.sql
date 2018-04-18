DROP TABLE IF EXISTS FILES;

--FILES TABLE
CREATE TABLE IF NOT EXISTS FILES (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
    FILENAME VARCHAR(255) NOT NULL,
	FILESIZE BIGINT NOT NULL,
    DATECREATED  TIMESTAMP NOT NULL,
	CSVFILE BLOB
   
);





