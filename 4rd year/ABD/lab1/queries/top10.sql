select product_id, count(product_id) as count from invoice
group by product_id
order by count desc
limit 10;
