-- 식품 id, 식품 이름, 총매출

-- 5월 식품의 총 매출

select p.product_id, p.product_name, sum(o.amount) * p.price as total_sales 
from food_product p
join 
    (select product_id, amount
    from food_order o
    where o.produce_date >= '2022-05-01' and o.produce_date <= '2022-05-31') o
on p.product_id = o.product_id
group by p.product_id
order by total_sales desc, p.product_id;