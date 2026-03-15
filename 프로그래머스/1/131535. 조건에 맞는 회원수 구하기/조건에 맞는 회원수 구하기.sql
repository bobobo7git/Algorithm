-- 코드를 입력하세요
select
    count(*) users
from user_info
where 1=1
and age between 20 and 29
and to_char(joined, 'YYYY') = '2021'