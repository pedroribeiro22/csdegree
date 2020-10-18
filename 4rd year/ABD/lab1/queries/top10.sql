SELECT DISTINCT product.id, COUNT(product.id) AS count
FROM invoice
GROUP BY product.id
LIMIT 10;
