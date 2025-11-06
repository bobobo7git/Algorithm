-- 코드를 입력하세요
with cte as (SELECT
    count(*) as cnt
from user_info u
where year(u.joined) = 2021)

select 
    year(o.sales_date) year,
    month(o.sales_date) month,
    count(distinct o.user_id) purchased_users,
    round(count(distinct o.user_id) / (select cnt from cte), 1) purchased_ratio
from user_info u
join online_sale o
on u.user_id=o.user_id
where year(u.joined) = 2021
group by year(o.sales_date), month(o.sales_date)
order by year(o.sales_date), month(o.sales_date)
