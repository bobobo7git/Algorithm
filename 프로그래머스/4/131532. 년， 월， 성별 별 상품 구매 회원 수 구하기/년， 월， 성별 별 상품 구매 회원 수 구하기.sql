-- 코드를 입력하세요
SELECT
    year(o.sales_date) year,
    month(o.sales_date) month,
    u.gender,
    count(distinct o.user_id) users
from user_info u
join online_sale o
on u.user_id = o.user_id
where u.gender is not null
group by
    year(o.sales_date),
    month(o.sales_date),
    u.gender

order by year, month, u.gender

