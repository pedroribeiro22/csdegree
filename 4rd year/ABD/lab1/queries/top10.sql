SELECT product.id, COUNT(product.id) AS count
FROM invoice
GROUP BY product.id
ORDER BY count
LIMIT 10;
