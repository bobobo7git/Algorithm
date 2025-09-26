-- 코드를 입력하세요
with cte as (
    select
        b.book_id, b.author_id, a.author_name, b.category, b.price
    from book b
    join author a
        on a.author_id = b.author_id
)

select
    author_id, author_name, category, sum(sales*price) total_sales
from book_sales s
join cte c
    on s.book_id = c.book_id
where date_format(sales_date, '%Y-%m') = '2022-01'
group by author_id, category
order by author_id, category desc