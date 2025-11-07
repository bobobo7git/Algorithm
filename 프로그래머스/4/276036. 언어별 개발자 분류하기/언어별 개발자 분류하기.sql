-- 코드를 작성해주세요
select * from (select
    case
        when group_concat(distinct s.category) like '%Front End%'
            and group_concat(distinct s.name) like '%Python%'
            then 'A'
        when group_concat(distinct s.name) like '%C#%'
            then 'B'
        when group_concat(distinct s.category) like '%Front End%'
            then 'C'
    end as grade,
    d.id, d.email
from developers d
join skillcodes s
on d.skill_code & s.code = s.code
group by d.id, d.email) cte
where grade is not null
order by grade, id