-- I'm assuming that we would only have the clients' name, so the first step would
-- be to get he's id, given his name, since all the products are referenced by
-- client identificator in the invoice table.

SELECT client.id FROM client WHERE client.name = name;

-- Now that we have the clients' name we will have to get all the invoices that
-- reference him. However, our goal is to get all the products that were sold to
-- this client, so instead of getting the complete invoices we will only get the
-- product identifiers:

SELECT invoice.product_id FROM invoice WHERE invoice.client_id = client_id;

-- For every product identifier we could return the product description, per
-- example, even though the query would be satisfied with the last statement:

SELECT product.description FROM product WHERE product.id = product_id;
