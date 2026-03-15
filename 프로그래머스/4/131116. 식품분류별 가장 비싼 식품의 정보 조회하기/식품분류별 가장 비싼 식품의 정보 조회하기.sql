-- 코드를 입력하세요
-- where category in ('과자', '국', '김치', '식용유')
SELECT
    a.category,
    b.price max_price, 
    a.product_name
from food_product a, (
    select
        category,
        max(price) price
    from food_product
    where category in ('과자', '국', '김치', '식용유')
    group by category
) b
where 1=1
and a.category = b.category
and a.price = b.price
order by b.price desc

