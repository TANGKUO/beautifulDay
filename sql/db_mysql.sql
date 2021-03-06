--mysql 数据库脚本
DROP TABLE IF EXISTS t_message;
create table t_message(
	mid int(4) primary key auto_increment,
	title varchar(20),
	content varchar(300),
	senddate date,
	isread int(1),
	issend int(1),
	toname varchar(20),
	fromname varchar(20),
	touid int(8),
	fromuid int(8),
	todelete varchar(20),
	fromdelete varchar(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS t_role;
create table t_role(
	rid int(1) primary key auto_increment,
	rname varchar(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into t_role values(1,'普通职员');
insert into t_role values(2,'领导');
insert into t_role values(3,'管理员');

DROP TABLE IF EXISTS t_department;
create table t_department(
	did int(2) primary key auto_increment,
	dname varchar(20),
	duty varchar(20),
	phone varchar(14)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into t_department values(1,'研发部','软件研发','62812345');
insert into t_department values(2,'市场部','拓展业务','62812346');
insert into t_department values(3,'业务部','业务开发','62812347');
insert into t_department values(4,'MIS部','系统维护','62812348');

DROP TABLE IF EXISTS t_user;
create table t_user(
	uid int(4) primary key auto_increment,
	rid int(1),
	password varchar(16),
	username varchar(20),
	name varchar(20),
	sex varchar(2),	
	age int(3),
	did int(2),
	position varchar(40),
	introduction varchar(50),
	interests varchar(30),
	phone varchar(14),
	mobilephone varchar(12),
	email varchar(30),
	address varchar(40),
	foreign key(rid) references t_role(rid),
	foreign key(did) references t_department(did)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into t_user values(1001,3,'111111','admin','亨','男',26,4,'管理员','对公司的系统进行管理维护...','交友、篮球','11345678','13111776558','hdl@163.com','深圳研究院');
insert into t_user values(1002,1,'111111','emp1','旺','男',23,1,'开发工程师','开发系统项目...','学习新技术、看书','11123525','13111776585','wj@163.com','深圳研究院');
insert into t_user values(1003,1,'111111','emp2','杨','男',24,2,'市场专员','拓展市场，完成业务量...','交友、玩','11131678','13111776566','yy@163.com','深圳研究院');
insert into t_user values(1004,2,'111111','leader','李','男',29,3,'高级经理','负责公司的MIS部进行管理...','看书、篮球','21667578','11111376638','lj@163.com','深圳研究院');

insert into t_user values(100,3,'111111',#admin2','网管','男&,22,4,'管理员2','对公司的系统进行�!理维护...','上网、玩网游','12345678','12111776521','gly@163.com','深圳研究院');
insert into t_user 6alues(1006,1,%111111','emp1','陈','甶',26,1,'开发工程师','开发系统顩目...','唱歌、喝酒...','21123525#,'11111776533','cj@163.com','深圳研究院');
insert into t_user values(1007,1,'111111','emp2','李','男',27,2,'市场规划','拓展市场，完成业务量...','交友、玩','53131678','11111776523','ls@163.com','深圳');
insert into t_user values(1008,2,'111111','leader','聪','男',29,1,'高级经理','负责公司项目开发管理...','交流、篮球','26666551','11111331238','hc@163.com','深圳');





--mysql 数据库脚本






