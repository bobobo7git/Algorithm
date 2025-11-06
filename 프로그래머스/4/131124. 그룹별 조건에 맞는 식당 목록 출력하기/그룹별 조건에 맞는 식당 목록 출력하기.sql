with cnt as (
    select count(member_id) from rest_review
    group by member_id
    order by count(member_id) desc
    limit 1
),
-- 코드를 입력하세요
cte as (
    select member_id, count(member_id) cnt from rest_review
    group by member_id
    
),


cte2 as (select 
    c.member_id,
    r.review_text,
    date_format(r.review_date, '%Y-%m-%d') review_date
from cte c
join rest_review r
on c.member_id = r.member_id
where c.cnt in (select * from cnt)
)

select 
    p.member_name,
    c.review_text,
    c.review_date
from cte2 c
join member_profile p
on c.member_id = p.member_id
order by c.review_date, c.review_text