-- 코드를 입력하세요
SELECT
    user_id, nickname,
    concat(city, ' ', street_address1, ' ', street_address2) 전체주소,
    concat(left(tlno, 3),'-',substring(tlno,4,4),'-',right(tlno, 4)) `전화번호`
from USED_GOODS_user u
join (
    select writer_id from USED_GOODS_BOARD
    group by writer_id
    having count(*) >= 3
) b
on u.user_id=b.writer_id
Order by user_id desc