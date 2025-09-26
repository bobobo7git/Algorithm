-- 코드를 입력하세요
# 세단, suv이고 2022-11-01보다 시작일이 작고 2022-11-30보다 반납일이 크고
# 30일 기준 금액이 50 이상 200 미만
select
    c.car_id, c.car_type, round(daily_fee*30*(1-0.01*discount_rate)) fee
    # *
from CAR_RENTAL_COMPANY_CAR c
join CAR_RENTAL_COMPANY_DISCOUNT_PLAN p
    on c.car_type = p.car_type and p.duration_type like '30%'
where c.car_type in ('세단', 'SUV')
    # and daily_fee * 30 >= 500000 and daily_fee * 30 < 2000000
    and daily_fee*30*(1-0.01*discount_rate) >= 500000
    and daily_fee*30*(1-0.01*discount_rate) < 2000000
    and car_id not in (
        select car_id from CAR_RENTAL_COMPANY_RENTAL_HISTORY
        where start_date <= '2022-11-30' and end_date >= '2022-11-01'
    )
order by daily_fee desc, c.car_type, c.car_id desc