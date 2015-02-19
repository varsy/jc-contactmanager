CREATE TABLE cm_contact (
	contact_id	SERIAL,
	sur_name		varchar(255) not null,
	given_name	varchar(255) not null,
	email		varchar(255) not null,
	phone		varchar(255) not null,
	primary key (contact_id)
);

 INSERT INTO cm_contact (sur_name, given_name, email, phone)
 VALUES ('Petrov', 'Ivan', 'ivan@pisem.net', '+7-983-123-45-67');