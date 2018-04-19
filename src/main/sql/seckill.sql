---存储过程
DELIMITER $$
--定义存储过程
--select ROW_COUNT() 返回上一条修改类型的sql(insert,update,delete)的影响行数
CREATE PROCEDURE dream.execute_seckill
  (in v_seckill_id bigint, in v_phone bigint,
    in v_kill_time TIMESTAMP , out r_result int)
  BEGIN
    DECLARE insert_count int DEFAULT 0;
    START TRANSACTION ;
    INSERT ignore into success_killed
      (seckill_id, user_phone,create_time)
      VALUES (v_seckill_id, v_phone, v_kill_time);
    SELECT ROW_COUNT() into insert_count;
    if(insert_count = 0) THEN
      ROLLBACK ;
      set r_result = -1;
    elseif(insert_count < 0) THEN
      ROLLBACK ;
      set r_result = -2;
    ELSE
      update seckill
       set stock=stock-1
       where seckill_id = v_seckill_id
       and v_kill_time >= start_time
       and v_kill_time <= end_time
       and stock > 0;
        select ROW_COUNT() into insert_count;
        if(insert_count = 0) THEN
          ROLLBACK ;
          set r_result = 0;
        elseif(insert_count < 0) THEN
          ROLLBACK ;
          set r_result = -2;
        ELSE
          COMMIT ;
          set r_result= 1;
          END IF;
        END IF;
    END$$

--存储过程定义结束

DELIMITER ;
set @r_result = -3;
--执行存储过程
call execute_seckill(1000, 18234132483, now(), @r_result);
--打印结果
select @r_result;