with recursive cte as (
    select 9 as hour
    
    union all
    select hour+1 from cte
    where hour<19
)

select
    hour(a.datetime) hour,
    count(*) count
from animal_outs a
right join cte c
on c.hour = hour(a.datetime)
group by c.hour
order by c.hour