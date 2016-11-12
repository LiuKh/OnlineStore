create table category(
id varchar(100) primary key,
name varchar(100) not null unique,
description varchar(255)
);


create table product(
	id varchar(100) primary key,
	productName varchar(100) not null unique,
	seller varchar(100) not null,
	price float(10,2),
	path varchar(100),
	fileName varchar(100),
	description varchar(255),
	categoryId varchar(100),
	constraint category_id_fk foreign key(categoryId) references category(id)
);

create table customer(
	id varchar(100) primary key,
	username varchar(100) not null unique,
	password varchar(100) not null,
	nickname varchar(100),
	phone varchar(100) not null,
	email varchar(100) not null,
	address varchar(255) not null
);

create table orders(
	ordernum varchar(100) primary key,
	quantity int,
	money float(10,2),
	status int,
	customerId varchar(100),
	constraint customer_Id_fk foreign key(customerId) references customer(id)
);

create table orderItem(
	id varchar(100) primary key,
	quantity int,
	price float(10,2),
	productId varchar(100),
	ordernum varchar(100),
	constraint product_Id_fk foreign key(productId) references product(id),
	constraint ordernum_fk foreign key(ordernum) references orders(ordernum)
);


create table users(
	id varchar(100) primary key,
	username varchar(100) not null unique,
	password varchar(100) not null
);

create table roles(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	description varchar(255)
);

create table urr(
	u_id varchar(100),
	r_id varchar(100),
	primary key(u_id,r_id),
	constraint urr1_fk foreign key(u_id) references users(id),
	constraint urr2_fk foreign key(r_id) references roles(id)
);

create table functions(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	uri varchar(100) not null,
	description varchar(100)
);

create table rfr(
	r_id varchar(100),
	f_id varchar(100),
	primary key(r_id,f_id),
	constraint rfr1_fk foreign key(f_id) references functions(id),
	constraint rfr2_fk foreign key(r_id) references roles(id)
);