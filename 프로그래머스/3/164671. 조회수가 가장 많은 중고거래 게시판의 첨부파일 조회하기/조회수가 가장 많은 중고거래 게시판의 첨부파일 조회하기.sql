-- 코드를 입력하세요
SELECT
    concat('/home/grep/src/',board_id,'/',file_id,file_name,file_ext) file_path
from used_goods_file
where board_id in (
    select board_id b
    from used_goods_board
    where views in (
        select
            max(rb.views)
        from used_goods_board rb
    ) 
)
order by file_id desc
