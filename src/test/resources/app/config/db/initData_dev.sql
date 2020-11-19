select *
  from not_exists_in_prd;
-- 운영 환경에서 돌아갈 수 없는 스크립트를 추가해 둠.. 방지책

-- 제약조건 삭제
ALTER TABLE BOARD DROP CONSTRAINT BFK1;
ALTER TABLE BOARD DROP CONSTRAINT BFK2;
ALTER TABLE BOARD DROP CONSTRAINT BFK3;
ALTER TABLE REPLY DROP CONSTRAINT RFK1;
ALTER TABLE REPLY DROP CONSTRAINT RFK2;
ALTER TABLE BOARDFILE DROP CONSTRAINT BFFK1;

truncate table reply;
truncate table boardfile;
truncate table board;
truncate table kind;
truncate table member;
------------------
-- sequence delete
------------------
drop sequence BOARD_SEQ;
drop sequence BOARDFILE_SEQ;
drop sequence KIND_SEQ;
drop sequence REPLY_SEQ;

create sequence BOARD_SEQ increment by 1 start with 110 nocache;
create sequence BOARDFILE_SEQ increment by 1 start with 65 nocache;
create sequence KIND_SEQ increment by 1 start with 15 nocache;
create sequence REPLY_SEQ increment by 1 start with 40 nocache;
commit;
--------------------------------------------------------
-- SET FOREIGN KEY SETTING
--------------------------------------------------------
ALTER TABLE BOARD 
ADD CONSTRAINTS BFK1 FOREIGN KEY (USER_ID) 
REFERENCES MEMBER(USER_ID);

ALTER TABLE BOARD 
ADD CONSTRAINTS BFK2 FOREIGN KEY (KIND_NO) 
REFERENCES KIND(KIND_NO);

ALTER TABLE BOARD 
ADD CONSTRAINTS BFK3 FOREIGN KEY (BOARD_PARENT_NO) 
REFERENCES BOARD(BOARD_NO);

ALTER TABLE BOARDFILE 
ADD CONSTRAINTS BFFK1 FOREIGN KEY (BOARD_NO) 
REFERENCES BOARD(BOARD_NO);

ALTER TABLE REPLY 
ADD CONSTRAINTS RFK1 FOREIGN KEY (BOARD_NO) 
REFERENCES BOARD(BOARD_NO);

ALTER TABLE REPLY
ADD CONSTRAINTS RFK2 FOREIGN KEY (USER_ID) 
REFERENCES MEMBER(USER_ID);

Insert into WHC_DEV.KIND (KIND_NO,KIND_NAME,KIND_VALID) values ('1','자유게시판','1');
Insert into WHC_DEV.KIND (KIND_NO,KIND_NAME,KIND_VALID) values ('2','공지사항','0');
Insert into WHC_DEV.KIND (KIND_NO,KIND_NAME,KIND_VALID) values ('3','장터게시판','0');
Insert into WHC_DEV.KIND (KIND_NO,KIND_NAME,KIND_VALID) values ('4','질문게시판','1');
Insert into WHC_DEV.KIND (KIND_NO,KIND_NAME,KIND_VALID) values ('7','테스트용게시판','0');
Insert into WHC_DEV.KIND (KIND_NO,KIND_NAME,KIND_VALID) values ('8','신규','0');
Insert into WHC_DEV.KIND (KIND_NO,KIND_NAME,KIND_VALID) values ('9','테스트 코드','0');
Insert into WHC_DEV.KIND (KIND_NO,KIND_NAME,KIND_VALID) values ('10','신규 게시판 등록 ! ','0');

Insert into WHC_DEV.MEMBER (USER_ID,USER_PASS,FILE_PATH,REALFILENAME,ADDR1,ADDR2,ZIPCODE,USER_NM) values ('brown','brownPass',null,null,null,null,null,null);
Insert into WHC_DEV.MEMBER (USER_ID,USER_PASS,FILE_PATH,REALFILENAME,ADDR1,ADDR2,ZIPCODE,USER_NM) values ('sally','sallyPass',null,null,null,null,null,'sally');

Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('1','자유게시판글수정했음..!',to_date('2020/10/27','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('2','공지사항글1',to_date('2020/10/27','YYYY/MM/DD'),'1',null,'brown','2');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('3','장터게시판글1',to_date('2020/10/27','YYYY/MM/DD'),'1',null,'brown','3');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('4','질문게시판글1???',to_date('2020/10/27','YYYY/MM/DD'),'0',null,'brown','4');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('18','연습',to_date('2020/10/27','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('19','자유게시판테스트33',to_date('2020/10/27','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('20','test_title',to_date('2020/10/27','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('21','test_title',to_date('2020/10/27','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('22','test_title',to_date('2020/10/27','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('23','test_title',to_date('2020/10/27','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('24','sally로 하나 글 쓰고 가요 ㅎㅎ',to_date('2020/10/28','YYYY/MM/DD'),'1',null,'sally','3');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('25','글이 너무 삭제가',to_date('2020/10/28','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('32','시퀀스 값 확인용',to_date('2020/10/28','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('33','시퀀스 값 확인용??',to_date('2020/10/28','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('34','시퀀스 값 확인용',to_date('2020/10/28','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('35','파일잘 들어가나?',to_date('2020/10/28','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('36','파일 테스트',to_date('2020/10/28','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('37','파일 업로드 테스트 22!',to_date('2020/10/28','YYYY/MM/DD'),'1',null,'brown','2');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('38','장터게시판 파일 업로드',to_date('2020/10/28','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('39','장터게시판 파일 업로드',to_date('2020/10/28','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('40','장터게시판 파일 업로드',to_date('2020/10/28','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('41','장터글 테스트! (파일업로드)',to_date('2020/10/28','YYYY/MM/DD'),'1',null,'brown','3');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('42','시퀀스 값 확인용',to_date('2020/10/28','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('43','수정은 잘 되나?',to_date('2020/10/28','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('44','안녕하세요 조웅현씨 !',to_date('2020/10/29','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('45','sally님 답글 하나 달아요',to_date('2020/10/29','YYYY/MM/DD'),'0','24','brown','3');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('46','44번 답글',to_date('2020/10/29','YYYY/MM/DD'),'1','44','sally','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('47','38번 답글',to_date('2020/10/29','YYYY/MM/DD'),'1','38','sally','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('48','43번 답글',to_date('2020/10/29','YYYY/MM/DD'),'1','43','sally','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('49','38번 답글의 답글',to_date('2020/10/29','YYYY/MM/DD'),'1','47','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('50','44번 답글의 답글',to_date('2020/10/29','YYYY/MM/DD'),'1','46','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('51','44번 답글의 답글의 답글',to_date('2020/10/29','YYYY/MM/DD'),'1','50','sally','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('52','ㅋㅋ',to_date('2020/10/29','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('53','자유게시판테스트2',to_date('2020/10/29','YYYY/MM/DD'),'0','46','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('54','43번 답글의 답글',to_date('2020/10/29','YYYY/MM/DD'),'1','48','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('55','44번 답글의 답글의 답글의 답글',to_date('2020/10/29','YYYY/MM/DD'),'1','51','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('56','시퀀스 값 확인용',to_date('2020/10/29','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('57','시퀀스 값 확인용',to_date('2020/10/29','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('58','시퀀스 값 확인용',to_date('2020/10/29','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('59','시퀀스 값 확인용',to_date('2020/10/29','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('60','ㅈㄷㄷㅂㅈㄷ',to_date('2020/10/29','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('61','ㅈㅂㅂㅈㅈㅂ',to_date('2020/10/29','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('62','시퀀스 값 확인용',to_date('2020/10/29','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('63','시퀀스 값 확인용',to_date('2020/10/29','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('64','시퀀스 값 확인용',to_date('2020/10/29','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('65','답글 확인용',to_date('2020/10/29','YYYY/MM/DD'),'1','62','sally','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('66','답글의 답글',to_date('2020/10/29','YYYY/MM/DD'),'1','65','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('67','2번째 답글',to_date('2020/10/29','YYYY/MM/DD'),'1','65','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('68','등록111111',to_date('2020/10/29','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('69','ㅁㄴㅇㄹㄴㅁㅇㄹㄴㅇㅁㄹ',to_date('2020/10/29','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('70','자유게시판테스트2',to_date('2020/10/29','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('71','ㅁㄴㅇㅀㄴㅁㅇㄹ',to_date('2020/10/29','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('72','ㅈㅂㄷㄹㅈㄷㄹㅈㄷㄹ',to_date('2020/10/29','YYYY/MM/DD'),'1',null,'brown','2');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('73','asdfasdfsadfsadfsadf',to_date('2020/10/29','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('74','asdfasdfsdf',to_date('2020/10/29','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('75','장터게시판 파일 업로드',to_date('2020/10/30','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('76','자유게시판테스트2',to_date('2020/10/30','YYYY/MM/DD'),'1',null,'brown','3');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('77','공지사항 글입니다.',to_date('2020/10/30','YYYY/MM/DD'),'1',null,'brown','2');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('78','ㄴㄴㄴ',to_date('2020/10/30','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('79','샐리 답글입니다',to_date('2020/10/30','YYYY/MM/DD'),'1','65','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('80','ㄴㅁㅇㄹㄴㅁㅇㄹㄴㅁㅇㄹ',to_date('2020/10/30','YYYY/MM/DD'),'1','24','brown','3');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('81','?????',to_date('2020/10/30','YYYY/MM/DD'),'1','65','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('82','왜 거기로 달려',to_date('2020/10/30','YYYY/MM/DD'),'1','65','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('83','하이하이',to_date('2020/10/30','YYYY/MM/DD'),'1','78','sally','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('84','샐리글',to_date('2020/10/30','YYYY/MM/DD'),'1',null,'sally','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('85','답글',to_date('2020/10/30','YYYY/MM/DD'),'0','84','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('86','시퀀스 값 확인용',to_date('2020/10/30','YYYY/MM/DD'),'1',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('87','ㄴㅇㄹㄴㅇㄹ',to_date('2020/10/30','YYYY/MM/DD'),'1','84','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('88','스프링테스트',to_date('2020/11/16','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('89','하울의 움직이는 성',to_date('2020/11/17','YYYY/MM/DD'),'0',null,'brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('90','스프링 답글',to_date('2020/11/17','YYYY/MM/DD'),'1','83','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('91','스프리이이이이이잉',to_date('2020/11/17','YYYY/MM/DD'),'1','84','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('92','하이하이에 대한 답글',to_date('2020/11/17','YYYY/MM/DD'),'1','83','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('93','?????',to_date('2020/11/17','YYYY/MM/DD'),'1','24','brown','3');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('94','샐리 질문 게시판 글 작성',to_date('2020/11/17','YYYY/MM/DD'),'1',null,'sally','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('95','샐리 질문 게시판 글 작성',to_date('2020/11/17','YYYY/MM/DD'),'1',null,'sally','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('96','샐리글',to_date('2020/11/17','YYYY/MM/DD'),'1',null,'sally','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('97','샐리글 질문게시판',to_date('2020/11/17','YYYY/MM/DD'),'1',null,'sally','4');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('98','샐리글',to_date('2020/11/17','YYYY/MM/DD'),'1',null,'sally','4');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('99','샐리글 답글1',to_date('2020/11/17','YYYY/MM/DD'),'1','98','brown','4');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('100','샐리글 답글2',to_date('2020/11/17','YYYY/MM/DD'),'1','98','brown','4');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('101','샐리글 답글1',to_date('2020/11/17','YYYY/MM/DD'),'1','96','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('102','샐리글의 답글입니다 :)',to_date('2020/11/17','YYYY/MM/DD'),'1','94','brown','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('103','스프리이이이이이잉에 대한 답글 ㅎㅎ',to_date('2020/11/17','YYYY/MM/DD'),'1','91','sally','1');
Insert into WHC_DEV.BOARD (BOARD_NO,BOARD_TITLE,BOARD_DATE,BOARD_DELETE,BOARD_PARENT_NO,USER_ID,KIND_NO) values ('104','답변 감사합니다  ',to_date('2020/11/17','YYYY/MM/DD'),'1','100','sally','4');

Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('8','수정했음',to_date('2020/10/28','YYYY/MM/DD'),null,'brown');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('4','테스트 코드용 댓글 입력..',to_date('2020/10/28','YYYY/MM/DD'),null,'sally');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('5','테스트 코드용 댓글 입력..',to_date('2020/10/28','YYYY/MM/DD'),null,'sally');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('3','하나 더 달고 가요 ㅎㅎ',to_date('2020/10/28','YYYY/MM/DD'),null,'sally');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('11','ㅁㄹㄴㅁㄹㄴㅁ',to_date('2020/10/28','YYYY/MM/DD'),null,'brown');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('15','댓글2',to_date('2020/10/28','YYYY/MM/DD'),null,'brown');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('16','댓글3',to_date('2020/10/28','YYYY/MM/DD'),null,'brown');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('14','댓글1',to_date('2020/10/28','YYYY/MM/DD'),null,'brown');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('17','잘 안되는듯;;',to_date('2020/10/28','YYYY/MM/DD'),null,'brown');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('18','댓글 테스트 수정함',to_date('2020/10/29','YYYY/MM/DD'),null,'brown');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('23','댓글은 순서대로 잘 가나 ? ',to_date('2020/10/29','YYYY/MM/DD'),null,'brown');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('24','잘 가네 ㅎㅎ',to_date('2020/10/29','YYYY/MM/DD'),null,'brown');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('21','댓글 테스트',to_date('2020/10/29','YYYY/MM/DD'),null,'brown');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('32','테스트 코드용 댓글 입력..',to_date('2020/10/30','YYYY/MM/DD'),'25','sally');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('25','순서대로 가나요 ? ',to_date('2020/10/29','YYYY/MM/DD'),null,'sally');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('27','다음번에 저도 알려주세요 ㅎㅎ',to_date('2020/10/29','YYYY/MM/DD'),'41','brown');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('28','굿굿! ',to_date('2020/10/29','YYYY/MM/DD'),'41','brown');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('33','하이하이',to_date('2020/10/30','YYYY/MM/DD'),'70','brown');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('31','댓글',to_date('2020/10/30','YYYY/MM/DD'),'78','brown');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('35','ㅋㅋㅋㅋㅋㅋㅋㅋㅋ',to_date('2020/11/17','YYYY/MM/DD'),'33','brown');
Insert into WHC_DEV.REPLY (REPLY_NO,REPLY_CONT,REPLY_DATE,BOARD_NO,USER_ID) values ('36','ㅋㅋㅋㅋ',to_date('2020/11/17','YYYY/MM/DD'),'88','brown');

Insert into WHC_DEV.BOARDFILE (FILE_NO,REALFILENAME,FILE_PATH,BOARD_NO) values ('41','cony.png','d:\upload\cony.png','67');
Insert into WHC_DEV.BOARDFILE (FILE_NO,REALFILENAME,FILE_PATH,BOARD_NO) values ('60','brown - 복사본.png','d:\upload\brown - 복사본.png','90');
Insert into WHC_DEV.BOARDFILE (FILE_NO,REALFILENAME,FILE_PATH,BOARD_NO) values ('40','sally.png','d:\upload\sally.png','65');
Insert into WHC_DEV.BOARDFILE (FILE_NO,REALFILENAME,FILE_PATH,BOARD_NO) values ('48','sdfsdfsdfsdf.png','d:\upload\sdfsdfsdfsdf.png','78');
Insert into WHC_DEV.BOARDFILE (FILE_NO,REALFILENAME,FILE_PATH,BOARD_NO) values ('50','moon - 복사본.png','d:\upload\moon - 복사본.png','70');
Insert into WHC_DEV.BOARDFILE (FILE_NO,REALFILENAME,FILE_PATH,BOARD_NO) values ('51','moon.png','d:\upload\moon.png','70');
Insert into WHC_DEV.BOARDFILE (FILE_NO,REALFILENAME,FILE_PATH,BOARD_NO) values ('52','ryan - 복사본.png','d:\upload\ryan - 복사본.png','70');
Insert into WHC_DEV.BOARDFILE (FILE_NO,REALFILENAME,FILE_PATH,BOARD_NO) values ('53','brown - 복사본 - 복사본 - 복사본.png','d:\upload\brown - 복사본 - 복사본 - 복사본.png','70');
Insert into WHC_DEV.BOARDFILE (FILE_NO,REALFILENAME,FILE_PATH,BOARD_NO) values ('54','ryan - 복사본.png','d:\upload\ryan - 복사본.png','70');
Insert into WHC_DEV.BOARDFILE (FILE_NO,REALFILENAME,FILE_PATH,BOARD_NO) values ('33','moon.png','d:\upload\moon.png','60');
COMMIT;