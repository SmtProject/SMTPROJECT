CREATE TABLE PAYMENT_DETAILS
(
 ID BIGINT AUTO_INCREMENT PRIMARY KEY,
 PAYMENT_ID BIGINT NOT NULL,
 NUMBER BIGINT NOT NULL,
 PAYED INT DEFAULT 0 ,
 AMOUNT INT DEFAULT 0 ,
 STATUS VARCHAR(64),
 CREATEDBY VARCHAR(64),
 UPDATEDBY VARCHAR(64),
 CREATEDATE date,
 UPDATEDATE date,
 FOREIGN KEY (PAYMENT_ID) REFERENCES PAYMENT(ID)
);
