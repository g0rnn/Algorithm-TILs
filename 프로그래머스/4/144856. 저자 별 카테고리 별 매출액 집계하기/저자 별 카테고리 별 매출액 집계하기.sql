-- author_id asc, category desc
-- (저자, 카테고리)별 매출액, author_id, author_name, category, sales

select b.author_id, a.author_name, b.category, sum(b.price * bs.sales) as total_sales
from book b
join author a 
    on b.author_id = a.author_id
join (select book_id, sum(sales) as sales from book_sales where date_format(sales_date, '%Y-%m') = '2022-01' group by book_id) bs
    on b.book_id = bs.book_id
group by b.author_id, b.category
order by b.author_id, b.category desc;















# SELECT 
#     AUTHOR_ID, 
#     AUTHOR_NAME, 
#     CATEGORY, 
#     PRICE * cnt as TOTAL_SALES
# FROM (
#     SELECT * 
#     FROM BOOK b
#     JOIN AUTHOR a
#     ON b.author_id = a.author_id
#     WHERE PUBLISHED_DATE BETWEEN '2022-01-01' AND '2022-01-31'
# ) ba
# JOIN (
#     SELECT COUNT(*) AS cnt, book_id 
#     FROM BOOK_SALES 
#     GROUP BY BOOK_ID
# ) bs
#     ON ba.book_id = bs.book_id
# ORDER BY AUTHOR_ID, CATEGORY DESC;

# SELECT 
#     a.AUTHOR_ID,
#     a.AUTHOR_NAME,
#     b.CATEGORY,
#     b.PRICE * bs.cnt AS TOTAL_SALES
# FROM BOOK b
# JOIN AUTHOR a 
#     ON b.author_id = a.author_id
# JOIN (
#     SELECT COUNT(*) AS cnt, book_id
#     FROM BOOK_SALES
#     GROUP BY BOOK_ID
# ) bs
#     ON b.book_id = bs.book_id
# WHERE b.PUBLISHED_DATE BETWEEN '2022-01-01' AND '2022-01-31'
# ORDER BY a.AUTHOR_ID, b.CATEGORY DESC;