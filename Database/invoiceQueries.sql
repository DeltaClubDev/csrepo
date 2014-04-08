#derping
UPDATE Company SET PersonsID = NULL WHERE PersonsID > 0;
PREPARE ps FROM 'SELECT PersonsID FROM Persons WHERE PersCode=?';
SET @a = "321na";
EXECUTE ps USING @a;

DELETE FROM Invoice WHERE ProductID > 0;
DELETE FROM Products WHERE ProductID > 0;

DELETE FROM Invoice WHERE InvoiceID > 0;

#1. Retrieve the major fields for every person
SELECT PersCode AS `Old System ID`, FirstName AS `First Name`, LastName AS `Last Name`, Email FROM Persons p 
JOIN Emails e ON e.PersonsID = p.PersonsID;

#2. Retrieve the emails of a given person
SELECT FirstName AS `First Name`, LastName AS `Last Name`, Email FROM Persons p JOIN Emails e 
ON e.PersonsID = p.PersonsID WHERE p.PersonsID = 2;

#3. Add an email address to a specific person
INSERT INTO Emails (PersonsID, Email) VALUES (1, "thisEmail@thisaddress.com");

#4. Change email address of a given email record
UPDATE Emails SET Email = "noThisEmail@thisaddress.com" WHERE Email = "thisEmail@thisaddress.com";

#5. Remove a given person record
DELETE FROM Emails WHERE PersonsID = 1;
UPDATE Company SET PersonsID = NULL WHERE PersonsID = 1;
UPDATE MainInvoice SET SalesDudeID = NULL WHERE SalesDudeID = 1;
UPDATE Products SET PersonsID = NULL WHERE PersonsID = 1;
DELETE FROM Persons WHERE PersonsID = 1;
DELETE FROM Address WHERE AddressID = 1;

#6. Get all products in a particular invoice
SELECT Name, InvoiceID AS `ID` FROM MainInvoice m JOIN Invoice i ON m.MainInvoiceID = i.MainInvoiceID JOIN Products p
ON i.ProductID = p.ProductID WHERE m.MainInvoiceID = 1;

#7. Get all the invoices of a particular customer
SELECT MainInvoiceID AS `ID`, Name FROM MainInvoice m JOIN Company c ON c.CompanyID = m.CompanyID WHERE c.CompanyID = 1;

#8. Adds a particular product to a particular invoice
INSERT INTO Invoice (`MainInvoiceID`, `ProductID`)
Values (1, 1);

#9. Find total number of all per-unit costs of all equipment products
SELECT SUM(UnitMoneh) AS `Unit Price` FROM Products;

#10. Find the total number of invoices for every customer record
SELECT COUNT(MainInvoiceID) AS `Number of Invoices`, Name FROM MainInvoice m JOIN Company c 
ON m.CompanyID = c.CompanyID WHERE m.CompanyID = c.CompanyID;

#11. Find the total number of invoices for every sales dude
SELECT COUNT(SalesDudeID) AS `Number of Invoices`, FirstName AS `First Name`, LastName AS `Last Name` 
FROM MainInvoice m JOIN Persons p ON m.SalesDudeID = p.PersonsID WHERE m.SalesDudeID = p.PersonsID;

#12. Find the total number of invoices that include a particular product
SELECT COUNT(InvoiceID) AS `Amount sold`, Name FROM MainInvoice m JOIN Invoice i ON m.MainInvoiceID = i.MainInvoiceID
JOIN Products p ON i.ProductID = p.ProductID WHERE p.ProductID = 2;

#13. Find the total cost (Excluding fees and taxes) of all equipment in each invoice
SELECT SUM(UnitMoneh) AS `Total Cost ($)` FROM MainInvoice m JOIN Invoice i ON m.MainInvoiceID = i.MainInvoiceID
JOIN Products p ON i.ProductID = p.ProductID WHERE TypeThing = "E";

#14. Find any invoice that includes multiple instances of the same equipment
SELECT ProductID AS `Equipment ID` FROM MainInvoice m JOIN Invoice i ON m.MainInvoiceID = i.MainInvoiceID 
JOIN (SELECT i.InvoiceID FROM Invoice i GROUP BY i.InvoiceID HAVING COUNT(*) >= 2 ) i2 ON i.InvoiceID = i2.InvoiceID;

#15. Find any and all invoices where the salesperson is the same as the primary contact of the invoice's customer.
SELECT FirstName AS `First Name`, LastName AS `Last Name`, MainInvoiceID AS `Invoice of Sale & Contact` 
FROM MainInvoice m JOIN Company c ON m.SalesDudeID = c.PersonsID JOIN Persons p ON p.PersonsID = c.PersonsID
WHERE m.SalesDudeID = c.PersonsID;