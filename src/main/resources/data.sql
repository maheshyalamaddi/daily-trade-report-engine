---------------------------------------------
-- VALID RECORD
-- FOR CURRENCY AED & SAR - WHERE THE WORK WEEK STARTS FROM SUNDAY AND ENDS ON THURSDAY
INSERT INTO INSTRUCTION (ENTITY, BUY_R_SELL, AGREEDFIX, CURRENCY, INSTUCTION_DATE, SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT) values ('E1', 'S', 0.50, 'AED', '2018-09-16', '2018-09-23', 1, 100.25);
INSERT INTO INSTRUCTION (ENTITY, BUY_R_SELL, AGREEDFIX, CURRENCY, INSTUCTION_DATE, SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT) values ('E2', 'B', 0.25, 'SAR', '2018-09-20', '2018-09-24', 2, 100.25);
-- FOR CURRENCY SGP & OTHER - WHERE THE WORK WEEK STARTS FROM MONDAY AND ENDS ON FRIDAY
INSERT INTO INSTRUCTION (ENTITY, BUY_R_SELL, AGREEDFIX, CURRENCY, INSTUCTION_DATE, SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT) values ('E1', 'S', 0.50, 'SGP', '2018-09-17', '2018-09-24', 1, 100.25);
INSERT INTO INSTRUCTION (ENTITY, BUY_R_SELL, AGREEDFIX, CURRENCY, INSTUCTION_DATE, SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT) values ('E1', 'B', 0.25, 'OTHER', '2018-09-21', '2018-09-28', 2, 100.25);
-- VALID RECORD - SHOULD BE A CHANGE IN SETTLEMENT DATE WHILE PROCESSING AS IT FALLS ON WEEK END PER CURRENCY
-- FOR CURRENCY AED & SAR
INSERT INTO INSTRUCTION (ENTITY, BUY_R_SELL, AGREEDFIX, CURRENCY, INSTUCTION_DATE, SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT) values ('E1', 'S', 0.50, 'AED', '2018-09-16', '2018-09-21', 1, 100.25);
INSERT INTO INSTRUCTION (ENTITY, BUY_R_SELL, AGREEDFIX, CURRENCY, INSTUCTION_DATE, SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT) values ('E2', 'B', 0.25, 'SAR', '2018-09-20', '2018-09-22', 2, 100.25);
-- FOR CURRENCY SGP & OTHER
INSERT INTO INSTRUCTION (ENTITY, BUY_R_SELL, AGREEDFIX, CURRENCY, INSTUCTION_DATE, SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT) values ('E1', 'S', 0.50, 'SGP', '2018-09-17', '2018-09-22', 1, 100.25);
INSERT INTO INSTRUCTION (ENTITY, BUY_R_SELL, AGREEDFIX, CURRENCY, INSTUCTION_DATE, SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT) values ('E1', 'B', 0.25, 'OTHER', '2018-09-21', '2018-09-23', 2, 100.25);
---------------------------------------------
-- IN VALID RECORD - INSTRUCTION DATE IS ON NON WORKING DAY AS PER CURRENCY
-- FOR CURRENCY AED & SAR
INSERT INTO INSTRUCTION (ENTITY, BUY_R_SELL, AGREEDFIX, CURRENCY, INSTUCTION_DATE, SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT) values ('E1', 'S', 0.50, 'AED', '2018-09-21', '2018-09-21', 1, 100.25);
INSERT INTO INSTRUCTION (ENTITY, BUY_R_SELL, AGREEDFIX, CURRENCY, INSTUCTION_DATE, SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT) values ('E2', 'B', 0.25, 'SAR', '2018-09-22', '2018-09-22', 2, 100.25);
-- FOR CURRENCY SGP & OTHER
INSERT INTO INSTRUCTION (ENTITY, BUY_R_SELL, AGREEDFIX, CURRENCY, INSTUCTION_DATE, SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT) values ('E1', 'S', 0.50, 'SGP', '2018-09-22', '2018-09-22', 1, 100.25);
INSERT INTO INSTRUCTION (ENTITY, BUY_R_SELL, AGREEDFIX, CURRENCY, INSTUCTION_DATE, SETTLEMENT_DATE, UNITS, PRICE_PER_UNIT) values ('E1', 'B', 0.25, 'OTHER', '2018-09-23', '2018-09-23', 2, 100.25);