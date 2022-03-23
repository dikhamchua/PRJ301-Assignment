use PRJ301_Assignment

create table Role
(
	[id] int primary key identity,
	[name] nvarchar(255) not null ,
	[code] nvarchar(255) not null,
	[create_date] date null,
	[modified_date] date  null,
	[created_by] nvarchar(255) null,
	[modified_by] nvarchar(255) null
)

create table [User]
(
	[id] int primary key identity,
	[username] nvarchar(150) not null,
	[password] nvarchar(150) not null,
	[full_name] nvarchar(150) null,
	[status] int not null,
	[role_id] int not null,
	[create_date] date null,
	[modified_date] date  null,
	[created_by] nvarchar(255) null,
	[modified_by] nvarchar(255) null
)

--drop table [role]
--drop table [user]

ALTER TABLE [User] 
ADD CONSTRAINT fk_user_role 
FOREIGN KEY (role_id) REFERENCES [Role](id);

create table [News]
(
	[id] int primary key identity,
	[title] nvarchar(255) null,
	[thumbnail] nvarchar(150) null,
	[short_description] ntext  null,
	[content] ntext null,
	[category_id] int not null,
	[create_date] date null,
	[modified_date] date  null,
	[created_by] nvarchar(255) null,
	[modified_by] nvarchar(255) null
)

create table Category 
(
	id int primary key identity,
	[name] nvarchar(255) not null,
	[code] nvarchar(255) not null,
	[created_date] date null,
	[modified_date] date null,
	[created_by] nvarchar(255) null,
	[modified_by] nvarchar(255) null
	
	
)
drop table Category

ALTER TABLE [News] 
ADD CONSTRAINT fk_news_category 
FOREIGN KEY (category_id) REFERENCES [Category](id);

create table [Comment]
(
	id int primary key identity,
	content ntext not null,
	[user_id] int not null,
	[news_id] int not null,
	[created_date] date null,
	[modified_date] date null,
	[created_by] nvarchar(255) null,
	[modified_by] nvarchar(255) null
)

drop table [Comment]

ALTER TABLE [Comment]
ADD CONSTRAINT fk_comment_user
FOREIGN KEY ([user_id]) REFERENCES [User](id);

ALTER TABLE [Comment]
ADD CONSTRAINT fk_comment_news
FOREIGN KEY ([news_id]) REFERENCES [News](id);