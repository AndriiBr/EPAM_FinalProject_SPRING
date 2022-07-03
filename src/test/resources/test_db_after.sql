delete from role;
delete from user_edition;
delete from usr;
delete from edition;

alter sequence hibernate_sequence restart 1;
alter sequence usr_id_seq restart 1;
alter sequence edition_id_seq restart 1;