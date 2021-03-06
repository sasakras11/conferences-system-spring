INSERT INTO locations( max_people, address) VALUES(10000,'Petra 7a');
INSERT INTO locations( max_people, address) VALUES(1000,'Mogilnoho 3b');
INSERT INTO locations( max_people, address) VALUES(11110,'Alexa 76a');
INSERT INTO locations( max_people, address) VALUES(10500,'Vlada 72a');

insert into conferences(name, date, location_id)VALUES ('IT-WEEK',STR_TO_DATE('01/05/2020', '%d/%m/%Y'),1);
insert into conferences(name, date, location_id)VALUES ('BEER-FEST',STR_TO_DATE('02/05/2020', '%d/%m/%Y'),1);
insert into conferences(name, date, location_id)VALUES ('TWENY FEST',STR_TO_DATE('03/05/2010', '%d/%m/%Y'),2);
insert into conferences(name, date, location_id)VALUES ('DIRECTOR-WEEK',STR_TO_DATE('04/05/2050', '%d/%m/%Y'),3);
insert into conferences(name, date, location_id)VALUES ('IT-CONFERENCE',STR_TO_DATE('05/05/2040', '%d/%m/%Y'),1);
insert into conferences(name, date, location_id)VALUES ('LAMA FEST',STR_TO_DATE('06/05/2030', '%d/%m/%Y'),2);
insert into conferences(name, date, location_id)VALUES ('KITTY FEST',STR_TO_DATE('07/05/2019', '%d/%m/%Y'),3);
insert into conferences(name, date, location_id)VALUES ('EDA FEST',STR_TO_DATE('08/05/2070', '%d/%m/%Y'),2);
insert into conferences(name, date, location_id)VALUES ('EKO FEST',STR_TO_DATE('08/05/2050', '%d/%m/%Y'),2);
insert into conferences(name, date, location_id)VALUES ('HALO FEST',STR_TO_DATE('08/05/2040', '%d/%m/%Y'),2);
insert into conferences(name, date, location_id)VALUES ('JOHN FEST',STR_TO_DATE('08/05/2030', '%d/%m/%Y'),2);
insert into conferences(name, date, location_id)VALUES ('ALEX FEST',STR_TO_DATE('08/05/2009', '%d/%m/%Y'),2);
insert into conferences(name, date, location_id)VALUES ('AMB FEST',STR_TO_DATE('08/05/2010', '%d/%m/%Y'),2);
insert into conferences(name, date, location_id)VALUES ('BMV FEST',STR_TO_DATE('08/05/2011', '%d/%m/%Y'),2);
insert into conferences(name, date, location_id)VALUES ('ADIDAS FEST',STR_TO_DATE('08/05/2012', '%d/%m/%Y'),3);
insert into conferences(name, date, location_id)VALUES ('XIAOMI FEST',STR_TO_DATE('08/05/2013', '%d/%m/%Y'),1);
insert into conferences(name, date, location_id)VALUES ('DAG FEST',STR_TO_DATE('08/05/2014', '%d/%m/%Y'),1);
insert into conferences(name, date, location_id)VALUES ('CAT FEST',STR_TO_DATE('08/05/2015', '%d/%m/%Y'),3);
insert into conferences(name, date, location_id)VALUES ('RATING FEST',STR_TO_DATE('08/05/2016', '%d/%m/%Y'),3);
insert into conferences(name, date, location_id)VALUES ('CAR FEST',STR_TO_DATE('08/05/2017', '%d/%m/%Y'),2);
insert into conferences(name, date, location_id)VALUES ('WAR FEST',STR_TO_DATE('08/05/2018', '%d/%m/%Y'),1);
insert into conferences(name, date, location_id)VALUES ('ASR FEST',STR_TO_DATE('08/05/2019', '%d/%m/%Y'),2);

insert  into users(username, password, role) VALUES ('alex','$2a$10$P4QuV1idGovyyJPQ3VH.T.PdQ9MSbaKNSLpKh3eaeXQbbtqnRa4Xe','ADMIN');
insert  into users(username, password, role) VALUES ('ivan','$2a$10$4PSZ.AyeMmoikQKQvZzmweG/8hnCbvJan0DqsMyRO9vWW24vL11Li','SPEAKER');
insert  into users(username, password, role) VALUES ('ira','$2a$10$9mNR2BLYAIikbBWnlMGL5e9Cgz9yjeXR.kSVxfMyhL8NX2yCEjycu','MODERATOR');
insert  into users(username, password, role) VALUES ('olga','51788beddf9484765b50585dfb6dc55032f7b8b77cd7c844caacd326a347f4c9cb1cb2b1d4a979d4c301a5859706428c0693093ea2f1f6a2ff9021b7779bb94c','SPEAKER');
insert  into users(username, password, role) VALUES ('egor','879cb7dc8e7417aeaa39d26dba985a7be8a36ae0fb66fba7f2988bb6480d28a0a645a8ae077df08c0bf7438976e3740115d19b2b103da7259239ca2c4df7d219','MODERATOR');
insert  into users(username, password, role) VALUES ('maxim','85eaa3a67f815fc8243475ee674d77173f4647e6388bb0bc9d80f381214e3613bac01055cc5b318313c9583ecfce0c498dbc6ddb1dfd6f3857bac60c47cad11b','VISITOR');
insert  into users(username, password, role) VALUES ('david','e91a8507b5df290a4632359fbf52bc98ea1825cc0c78b3b988d85b55467ee5659bb6d185ae5905471c3334f1b72b7f00fa27f95053a93de53fc7bdba2dfcd9a9','VISITOR');
insert  into users(username, password, role) VALUES ('dima','$2a$10$zO8GwYAJwSPTIckkjw7ESuOQ89ElJ3Db/yH9M4lIC18nsIRltOPGK','VISITOR');
insert  into users(username, password, role) VALUES ('katerina','ac245b3f84ed62649cc4cea19bdffe865c6196f95a78a5a5072a6ea132eca8ba871b6add43141086567569068b61ca33c23b2f9aa29d58ef976ee6140c6c2e29','VISITOR');

insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Как стать програмистом','not given',2,5,2,1);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Топик о Junit','not given',1,2,2,1);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id,  conference_id) values ('Как стать програмистом после 50','not given',2,5,2,1);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Карл саган','not given',3,5,2,2);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('о ГМО','not given',4,5,2,2);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Встреча с Андреем','not given',6,5,2,2);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Смысл жизни','',7,5,2,3);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Встреча со Стивеном Кови','not given',8,5,2,3);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Как писать на Python','',9,5,2,4);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Java-разработка','not given',10,5,2,4);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Доклад о безопасности','not given',11,5,4,4);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Spring Security','not given',12,5,4,5);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Spring boot','not given',13,5,4,5);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Тайны вселенной','not given',14,5,4,5);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('поговорим про Marvel','not given',15,5,4,6);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Человек глазами врача','not given',16,5,4,6);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('О вкусной еде','not given',20,21,4,6);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Топ 10 книг','not given',22,23,4,7);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('О wi-fi','not given',23,24,4,7);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Политика кофиденциальности','not given',0,1,4,7);
insert into speeches(topic, suggested_topic, start_hour, end_hour, user_id, conference_id) values ('Ser Man Meeting','not given',2,3,4,8);


 insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(1,1);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(1,2);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(1,3);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(2,4);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(2,5);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(2,6);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(3,7);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(3,8);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(4,9);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(4,10);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(4,11);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(5,12);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(5,13);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(5,14);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(6,15);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(6,16);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(6,17);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(7,18);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(7,19);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(7,20);
insert  into conferences_speeches(conference_conference_id, speeches_speech_id) valueS(8,21);



insert into conferences_members(conferences_conference_id, members_user_id) VALUES (1,1);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (1,2);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (1,3);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (1,4);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (2,5);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (2,6);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (2,7);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (2,8);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (3,9);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (3,1);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (3,2);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (3,3);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (4,4);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (4,5);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (4,6);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (4,7);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (5,8);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (5,9);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (5,1);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (5,2);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (6,3);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (6,4);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (6,5);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (7,6);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (7,7);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (7,8);
insert into conferences_members(conferences_conference_id, members_user_id) VALUES (7,9);




insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (1,1);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (1,2);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (1,3);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (2,4);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (2,5);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (2,6);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (3,7);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (3,8);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (3,9);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (4,1);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (4,2);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (4,3);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (5,4);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (5,5);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (5,6);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (6,7);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (6,8);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (6,9);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (7,1);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (7,2);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (7,3);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (8,4);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (8,5);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (8,6);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (9,7);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (9,8);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (9,9);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (10,1);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (10,2);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (10,3);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (11,4);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (11,5);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (11,6);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (12,7);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (12,8);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (12,9);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (13,1);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (13,2);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (13,3);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (14,4);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (14,5);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (14,6);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (15,7);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (15,8);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (15,9);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (16,1);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (16,2);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (16,3);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (17,4);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (17,5);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (17,6);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (18,7);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (18,8);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (18,9);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (19,1);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (19,2);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (19,3);

insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (20,4);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (20,5);
insert into speeches_visitors(speeches_speech_id, visitors_user_id) VALUES (20,6);

insert into ratings(rating, user_id) VALUES (5,2);
insert into ratings(rating, user_id) VALUES (5,4);