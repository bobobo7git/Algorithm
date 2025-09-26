-- 코드를 입력하세요
/*
리뷰를 가장 많이 작성한 회원들의 리뷰 조회
1. 가장 많은 리뷰 수를 서브쿼리로 뽑기
2. 리뷰수가 가장 많은 리뷰수와 같은 회원 id를 서브쿼리로 뽑기
3. 회원 id가 2의 회원id인 녀석들 뽑기
*/

with top as (
    select
        count(*) cnt
    from rest_review
    group by member_id
    order by count(*) desc
    limit 1
),
best as (
    select member_id from rest_review
    group by member_id
    having count(*) in (select * from top)
)

select
    member_name, review_text, date_format(review_date, '%Y-%m-%d')
from member_profile m
join rest_review r
on m.member_id = r.member_id
where m.member_id in (select * from best)
order by review_date, review_text

