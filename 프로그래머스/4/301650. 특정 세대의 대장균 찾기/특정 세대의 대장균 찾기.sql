-- 코드를 작성해주세요
with recursive cte as (
    select 
        id, parent_id, 1 as depth
    from ecoli_data
    where parent_id is null
    
    union all
    select
        e.id, e.parent_id, c.depth+1
    from ecoli_data e
    join cte c
    on e.parent_id = c.id

)

select id from cte
where depth = 3
order by id
