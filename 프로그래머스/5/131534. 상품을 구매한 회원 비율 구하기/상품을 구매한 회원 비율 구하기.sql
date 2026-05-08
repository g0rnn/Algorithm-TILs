-- unique (sales_date, user_id, product_id)

-- 상품 구매 회원 비율을 년, 월 별로 출력

-- 소수점 두번째에서 반올림, (년 asc, 월 asc)

# select *
# from online_sale s
# join 
#     (select year(joined) as y, count(user_id)
#     from user_info
#     where year(joined) = '2021'
#     group by year(joined)) ui
# on year(s.sales_date) = ui.y;


# select *
# from online_sale s
# where user_id in (select user_id from user_info where year(joined) = '2021');

# select year(sales_date) as year, month(sales_date) as month, count(user_id) as purchased_users
# from online_sale s
# join 
#     (select year(joined) as y, count(user_id)
#     from user_info
#     where year(joined) = '2021'
#     group by year(joined)) ui
# on year(s.sales_date) = ui.y
# where user_id in (select user_id from user_info where year(joined) = '2021')
# group by date_format(sales_date, '%y-%m');

SELECT
    YEAR(s.sales_date) AS YEAR,
    MONTH(s.sales_date) AS MONTH,
    COUNT(DISTINCT s.user_id) AS PURCHASED_USERS,
    ROUND(
        COUNT(DISTINCT s.user_id) / (
            SELECT COUNT(*)
            FROM user_info
            WHERE YEAR(joined) = 2021
        ),
        1
    ) AS PURCHASED_RATIO
FROM online_sale s
JOIN user_info u
    ON s.user_id = u.user_id
WHERE YEAR(u.joined) = 2021
GROUP BY
    YEAR(s.sales_date),
    MONTH(s.sales_date)
ORDER BY
    YEAR(s.sales_date),
    MONTH(s.sales_date);