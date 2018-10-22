USE mybatis;
DROP TABLE IF EXISTS t_lecture;
DROP TABLE IF EXISTS t_student;
DROP TABLE IF EXISTS t_student_health_female;
DROP TABLE IF EXISTS t_student_health_male;
DROP TABLE IF EXISTS t_student_lecture;
DROP TABLE IF EXISTS t_student_selfcard;
create table t_lecture
  (
    id   int(20) not null auto_increment comment '编号',
    lecture_name varchar(60) not null comment '课程名称',
    note varchar(1024) comment '备注',
    primary key (id)
  );
CREATE TABLE t_student(
  id int(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  cnname VARCHAR(60) not null COMMENT '学生姓名',
  sex TINYINT(4) not NULL  COMMENT '性别',
  selfcard_no INT(20) NOT NULL COMMENT '学生证号',
  note VARCHAR(1024) COMMENT '备注',
  PRIMARY KEY (id)
);
CREATE TABLE t_student_health_female(
  id int(20) not null AUTO_INCREMENT COMMENT '编号',
  student_id VARCHAR(60) NOT NULL COMMENT '学生编号',
  check_date VARCHAR(60) NOT NULL COMMENT '检查日期',
  heart VARCHAR(60) NOT NULL COMMENT '心',
  liver VARCHAR(60) not NULL COMMENT '肝',
  spleen VARCHAR(60) NOT NULL COMMENT '脾',
  lung VARCHAR(60) NOT NULL COMMENT '肺',
  kidney VARCHAR(60) NOT NULL COMMENT '肾',
  uterus VARCHAR(60) not NULL COMMENT '子宫',
  note VARCHAR(1024) NOT NULL COMMENT '备注',
  PRIMARY KEY (id)
);
CREATE TABLE t_student_health_male(
  id int(20) not null AUTO_INCREMENT COMMENT '编号',
  student_id VARCHAR(60) NOT NULL COMMENT '学生编号',
  check_date VARCHAR(60) NOT NULL COMMENT '检查日期',
  heart VARCHAR(60) NOT NULL COMMENT '心',
  liver VARCHAR(60) not NULL COMMENT '肝',
  spleen VARCHAR(60) NOT NULL COMMENT '脾',
  lung VARCHAR(60) NOT NULL COMMENT '肺',
  kidney VARCHAR(60) NOT NULL COMMENT '肾',
  prostate VARCHAR(60) not NULL COMMENT '前列腺',
  note VARCHAR(1024) NOT NULL COMMENT '备注',
  PRIMARY KEY (id)
);
CREATE TABLE t_student_lecture(
  id int(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  student_id INT(20) NOT NULL COMMENT '学生编号',
  lecture_id int(20) NOT NULL COMMENT '课程编号',
  grade DECIMAL(16,2) not NULL COMMENT '评分',
  note VARCHAR(1024) COMMENT '备注',
  PRIMARY KEY (id)
);
create TABLE t_student_selfcard(
  id INT(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  student_id int(20) not NULL COMMENT '学生编号',
  native VARCHAR(60) NOT NULL comment '籍贯',
  issue_date DATE NOT NULL COMMENT '发证日期',
  end_date date NOT NULL COMMENT '结束日期',
  note VARCHAR(1024) COMMENT '备注',
  PRIMARY KEY (id)
);