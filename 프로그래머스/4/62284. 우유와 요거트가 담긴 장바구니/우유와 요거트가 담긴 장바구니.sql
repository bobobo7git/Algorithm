-- Yogurt
with ygrt as (
    select cart_id from cart_products
    where name='Yogurt'
),
-- milk
milk as (
    select cart_id from cart_products
    where name='Milk'
),
ym as (
    select cart_id
    from milk
    where cart_id in (select * from ygrt)
)

select distinct cart_id from ym
order by cart_id
