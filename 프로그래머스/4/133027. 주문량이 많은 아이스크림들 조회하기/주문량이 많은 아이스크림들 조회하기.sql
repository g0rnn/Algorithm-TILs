-- 같은 맛의 아이스크림이라도 다른 출하번호(SHIPMENT_ID)를 가질 수 있음

select flavor
from
    (select flavor, total_order from first_half
    union all
    select flavor, total_order from july) icecream
group by flavor
order by sum(total_order) desc
limit 3;