CREATE TABLE contacts (
	contact_id	SERIAL,
	surname		varchar(255) not null,
	givenname	varchar(255) not null,
	email		varchar(255) not null,
	phone		varchar(255) not null,
	primary key (contact_id)
)