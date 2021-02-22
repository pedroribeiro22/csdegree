-- Usually when you're buying a product you only know it's name, so it makes
-- sense to get it's id first:

-- Let's assume the description as a name, since there are no product names
-- in this case...

SELECT product.id FROM product WHERE product.description = description;

-- Now that we have the product identificator we have to find our client
-- identificator, given our name:

SELECT client.id FROM client WHERE client.name = name;

-- At this point, a program would generate an invoice identificator that you
-- would then feed to the SQL statement, like so:

-- I'm assuming that `data` is some information that people might want to add
-- to the invoice.

INSERT INTO invoice VALUES (id, product_id, client_id, data);
