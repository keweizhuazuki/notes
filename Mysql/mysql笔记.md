### mysql简易操作
1: mysql 启动与关闭
`mysql.server start`
`mysql.server stop`
2: mysql 登录
`mysql -u root -p` (密码为123456)

### SQL分类
1: `DDL(Data Definition Language)` 用来定义数据库对象：数据库、表、列等
2: `DML(Data Manipulation Language)` 用来操作数据库表中的记录
3: `DQL(Data Query Language)` 用来查询数据库中的记录
4: `DCL(Data Control Language)` 用来控制数据库用户访问权限和安全性

### DDL操作 (test为数据库名)
1: 展示数据库：`show databases;`
2: 查询数据库：`select database();`
3: 创建数据库：`create database [if not exists] test [default charset utf8mb4][collate排序规则];`
4: 删除数据库：`drop database [if exists] test;`
5: 使用数据库：`use test;`
6: 展示表：`show tables;`
7: 查询表：`desc test;`
8: 查询指定表的创建语句：`show create table test;`
9: 创建表：
```
CREATE TABLE 表名(
    字段1 字段1类型[COMMENT 字段1注释],
    字段2 字段2类型[COMMENT 字段2注释],
    ...
    字段n 字段n类型[COMMENT 字段n注释]
)[COMMENT 表注释];
```
10: 添加字段：`alter table test add 字段名 字段类型[COMMENT 注释][约束];`
11: 修改数据类型：`alter table test modify 字段名 字段类型;`
12: 修改字段名：`alter table test change 旧字段名 新字段名 字段类型[COMMENT 注释][约束];`
13: 删除字段：`alter table test drop 字段名;`
14: 修改表名：`alter table test rename to 新表名;`
15: 删除表：`drop table [if exists] test;`
16: 删除表并重新创建表: `truncate table test;`

### DML操作 (test为表名)
1: 给指定字段添加数据：`insert into test(字段1, 字段2, ...) values(值1, 值2, ...);`
2: 给所有字段添加数据：`insert into test values(值1, 值2, ...);`
3: 批量添加数据：`insert into test(字段1, 字段2, ...) values(值1, 值2, ...), (值1, 值2, ...), ...;`
4: 批量给所有字段添加数据：`insert into test values(值1, 值2, ...), (值1, 值2, ...), ...;`
5: 修改数据：`update test set 字段1=值1, 字段2=值2, ... [where 条件];`
6: 删除数据：`delete from test [where 条件];`

### DQL操作 (test为表名)
1: 查询多个字段：`select 字段1, 字段2, ... from test;`
2: 查询所有字段：`select * from test;`
3: 查询多个字段并去重：`select distinct 字段1, 字段2, ... from test;`
4: 条件查询：`select * from test where 条件;`
5: 聚合函数：`count max min sum avg`
6: 分组查询：`select 字段1 from test [where 条件] group by 分组字段 [分组后过滤条件];`
7: 排序查询：`select * from test order by 字段1 [asc|desc];`
8: 分页查询：`select * from test limit 起始位置, 查询条数;`

### DCL操作
1: 查询用户：`use mysql; select * from user;`
2: 创建用户：`create user '用户名'@'主机' identified by '密码';`
3: 创建用户在任意主机登录：`create user '用户名'@'%' identified by '密码';`
4: 修改用户密码：`alter user '用户名'@'主机' identified with mysql_native_password by '新密码';`
5: 删除用户：`drop user '用户名'@'主机';`
6: 查询权限：`show grants for '用户名'@'主机';`
7: 授权：`grant 权限列表 on 数据库.表 to '用户名'@'主机';`
8: 撤销授权: `revoke 权限列表 on 数据库.表 from '用户名'@'主机';`

### 函数
#### 字符串函数
1: 拼接字符串：`concat(str1, str2, ...);`
2: 转为大写：`upper(str);`
3: 转为小写：`lower(str);`
4: 左填充：`lpad(str, length, pad);`
5: 右填充：`rpad(str, length, pad);`
6: 去除左右空格：`trim(str);`
7: 截取字符串：`substring(str, start, length);`
#### 数值函数
1: 向上取整：`ceil(num);`
2: 向下取整：`floor(num);`
3: 返回x/y的模: `mod(x, y);`
4: 返回0-1之间的随机数: `rand();`
5: 四舍五入：`round(num, scale);`
#### 日期函数
1: 获取当前日期：`curdate();`
2: 获取当前时间：`curtime();`
3: 获取当前日期时间：`now();`
4: 获取指定date的年份: `year(date);`
5: 获取指定date的月份: `month(date);`
6: 获取指定date的天数: `day(date);`
7: 返回一个加上指定天数的日期：`date_add(date, INTERVAL day 时间类型);`
8: 返回起始日期到结束日期的天数：`datediff(date1, date2);`
#### 流程控制函数
1: if函数: `if(条件, 真值, 假值);`
2: ifnull函数: `ifnull(字段, 替代值);`
3: case函数: `case 字段 when 值1 then 结果1 when 值2 then 结果2 else 结果3 end;`
4: case表达式: `case [expression] when 值1 then 结果1 when 值2 then 结果2 else 结果3 end;`

### 约束
1: 主键约束：`primary key`
2: 非空约束：`not null`
3: 唯一约束：`unique`
4: 外键约束：`foreign key`
5: 默认约束：`default`
6: 检查约束：`check`
7: 自增约束：`auto_increment`

#### 外键约束
1: 创建外键约束：`alter table test add constraint 外键名 foreign key(字段) references 主表(字段);`
2: 创建表时创建外键约束：`create table test(字段1 字段1类型, 字段2 字段2类型, foreign key(字段1) references 主表(字段1));`
3: 删除外键约束：`alter table test drop foreign key 外键名;`
4: 如果要删除对应记录，检查记录是否有外键，如果有不允许删除：`NO ACTION 同 RESTRICT`
5: 如果要删除对应记录，检查记录是否有外键，如果有删除外键对应记录：`CASCADE`
6: 如果要删除对应记录, 检查记录是否有外键, 如果有设置为null: `SET NULL`
7: 如果要删除对应记录，检查记录是否有外键，如果有设置为默认值：`SET DEFAULT`
8: 修改行为：`alter table test drop foreign key 外键名, add constraint 外键名 foreign key(字段) references 主表(字段) on update CASCADE on delete CASCADE;`

### 多表查询
1: 内连接查询: `select * from 表1 inner join 表2 on 表1.字段=表2.字段;`
2: 左外连接查询:  `select * from 表1 left join 表2 on 表1.字段=表2.字段;`
3: 右外连接查询: `select * from 表1 right join 表2 on 表1.字段=表2.字段;`
4: 自连接查询: `select * from 表1 as t1 inner join 表1 as t2 on t1.字段=t2.字段;`
5: 联合查询: `select * from 表1 union select * from 表2; union all 不去重`
#### 字查询
1: 标量字查询: 常用操作符: `=,<>,<,<=,>,>=;`
2: 列子查询: 常用操作符: `in, not in, any, some, all; some等价于any`
3: 行子查询: 常用操作符: `=,<>,in, not in`
4: 表子查询: 常用操作符: `in`

### 事务
1: 查看事务提交方式: `select @@autocommit;`
2: 设置事务提交方式: `set autocommit=0;`
3: 提交事务: `commit;`
4: 回滚事务: `rollback;`
5: 开启事务: `start transaction;/begin;`
#### 四大特性
1: 原子性: 事务中的所有操作要么全部成功，要么全部失败
2: 一致性: 事务执行前后，数据的完整性保持一致
3: 隔离性: 事务之间互不干扰，在并发环境下，事务之间是相互隔离的
4: 持久性: 事务一旦提交，对数据的修改是永久性的
#### 并发事务问题
1: 脏读: 一个事务读取到另一个事务未提交的数据
2: 不可重复读: 一个事务读取到另一个事务已提交的数据
3: 幻读: 一个事务读取到另一个事务插入的数据
#### 事务隔离级别
1: 读未提交: `read uncommitted`
2: 读已提交: `read committed`
3: 可重复读: `repeatable read`
4: 串行化: `serializable`
5: 查看事务隔离级别: `select @@transaction_isolation;`
6: 设置事务隔离级别: `set [session/global] transaction isolation level 隔离级别;`