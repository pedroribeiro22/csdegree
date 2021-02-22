create materialized view Top10 as
	select product.id, count(product.id) as count from invoice group by product.id order by count limit 10;
