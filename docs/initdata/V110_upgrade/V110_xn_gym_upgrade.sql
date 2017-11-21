update tgym_per_course set sk_end_datetime=concat(SUBSTRING(now(),1 ,10),' ',sk_end_datetime);
update tgym_per_course set sk_start_datetime=concat(SUBSTRING(now(),1 ,10),' ',sk_start_datetime);


update tgym_per_course set sk_end_datetime =DATE_ADD(sk_end_datetime,INTERVAL 2 day), 
sk_start_datetime =DATE_ADD(sk_start_datetime,INTERVAL 7 day) where sk_cycle='1';
update tgym_per_course set sk_end_datetime =DATE_ADD(sk_end_datetime,INTERVAL 3 day), 
sk_start_datetime =DATE_ADD(sk_start_datetime,INTERVAL 6 day)  where sk_cycle='2';
update tgym_per_course set sk_end_datetime =DATE_ADD(sk_end_datetime,INTERVAL 4 day), 
sk_start_datetime =DATE_ADD(sk_start_datetime,INTERVAL 5 day)  where sk_cycle='3';
update tgym_per_course set sk_end_datetime =DATE_ADD(sk_end_datetime,INTERVAL 5 day), 
sk_start_datetime =DATE_ADD(sk_start_datetime,INTERVAL 4 day)  where sk_cycle='4';
update tgym_per_course set sk_end_datetime =DATE_ADD(sk_end_datetime,INTERVAL 6 day), 
sk_start_datetime =DATE_ADD(sk_start_datetime,INTERVAL 3 day)  where sk_cycle='5';
update tgym_per_course set sk_end_datetime =DATE_ADD(sk_end_datetime,INTERVAL 7 day), 
sk_start_datetime =DATE_ADD(sk_start_datetime,INTERVAL 2 day)  where sk_cycle='6';
update tgym_per_course set sk_end_datetime =DATE_ADD(sk_end_datetime,INTERVAL 8 day), 
sk_start_datetime =DATE_ADD(sk_start_datetime,INTERVAL 1 day)  where sk_cycle='7';


alter table tgym_per_course modify column sk_start_datetime datetime;
alter table tgym_per_course modify column sk_end_datetime datetime;

alter table tgym_coach ADD id_photo text after pdf; 

update tgym_per_course_order set xk_datetime=concat(SUBSTRING(appoint_datetime,1 ,10),' ',xk_datetime);
update tgym_per_course_order set sk_datetime=concat(SUBSTRING(appoint_datetime,1 ,10),' ',sk_datetime);

alter table tgym_per_course_order modify column xk_datetime datetime;
alter table tgym_per_course_order modify column sk_datetime datetime;

update tgym_per_course set status ='0';
