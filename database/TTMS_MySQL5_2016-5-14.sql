?/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/5/16 15:40:40                           */
/*==============================================================*/



/*==============================================================*/
/* Table: data_dict                                             */
/*==============================================================*/
create table data_dict
(
   dict_id              int not null auto_increment,
   dict_parent_id       int,
   dict_index           int,
   dict_name            varchar(200),
   dict_value           varchar(100) not null,
   primary key (dict_id)
);

/*==============================================================*/
/* Table: employee                                              */
/*==============================================================*/
create table employee
(
   emp_id               int not null auto_increment,
   emp_name             varchar(100) not null,
   emp_tel_num          char(20),
   emp_addr             varchar(200),
   emp_email            varchar(100),
   emp_passWord         CHAR(20),
   emp_userName         CHAR(20),
   emp_type             CHAR(20),
   primary key (emp_id)
);

/*==============================================================*/
/* Table: play                                                  */
/*==============================================================*/
create table play
(
   play_id              int not null auto_increment,
   play_type_id         int,
   play_lang_id         int,
   play_name            varchar(200),
   play_introduction    varchar(2000),
   play_image           LONGBLOB,
   play_length          INT(11),
   play_director        CHAR(20),
   play_ticket_price    numeric(10,2),
   play_type            CHAR(20),
   play_protagonist     char(20),
   play_status          char(20),
   primary key (play_id)
);

/*==============================================================*/
/* Table: sale                                                  */
/*==============================================================*/
create table sale
(
   sale_ID              bigint not null auto_increment,
   emp_id               int,
   sale_time            datetime,
   sale_payment        float,
   sale_change         float,
   ticket_id            CHAR(200),
   order_price          float,

   primary key (sale_ID)
);

/*==============================================================*/
/* Table: sale_item                                             */
/*==============================================================*/
create table sale_item
(
   sale_item_id         bigint not null auto_increment,
   ticket_id            bigint,
   sale_ID              bigint,
   sale_item_price      numeric(10,2),
   primary key (sale_item_id)
);

/*==============================================================*/
/* Table: schedule                                              */
/*==============================================================*/
create table schedule
(
   sched_id             int not null auto_increment,
   studio_id            int,
   play_id              int,
   sched_time           char(20),
   schedule_price  float,
   primary key (sched_id)
);

/*==============================================================*/
/* Table: seat                                                  */
/*==============================================================*/
create table seat
(
   seat_id              int not null auto_increment,
   studio_id            int,
   seat_row             int,
   seat_column          int,
   movie_id             int,
   schedule_id          int,
   primary key (seat_id)
);

/*==============================================================*/
/* Table: studio                                                */
/*==============================================================*/
create table studio
(
   studio_id            int not null auto_increment,
   studio_name          varchar(100) not null,
   studio_row_count     int,
   studio_col_count     int,
   studio_introduction  varchar(2000),
   primary key (studio_id)
);

/*==============================================================*/
/* Table: ticket                                                */
/*==============================================================*/
create table ticket
(
   ticket_id            bigint not null auto_increment,
   seat_id              int,
   schedule_id          int,
   play_id              int,
   ticket_price         float,
   ticket_date          VARCHAR(45),
   primary key (ticket_id)
);


