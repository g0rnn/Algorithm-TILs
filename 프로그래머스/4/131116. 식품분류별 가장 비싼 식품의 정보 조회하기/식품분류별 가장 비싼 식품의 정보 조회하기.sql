select p.category, max_price, product_name
from food_product p
join (
    select category, max(price) as max_price
    from food_product
    group by category
    ) f
on p.category=f.category and p.price = f.max_price
where p.category in ('과자', '국', '식용유', '김치')
order by max_price desc;
