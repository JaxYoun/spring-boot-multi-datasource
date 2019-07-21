# spring-boot-multi-datasource
## 本项目是spring-boot配置MySQL双数据源demo，使用Hikari-cp作为连接池，MyBatis作为ORM框架
1. 在配置文件中配置两个不同的数据库连接。
2. 分别为两个连接配置：数据源、session工厂、事务管理器、SQL模板四种对象。
~~~
a.其中第一个的四个对象全部使用@Primary注解作为主数据源，在未显式指定数据源的地方默认使用主数据源。
b.备数据源只在显式指定的地方才能被调用。
~~~
3. MyBatis-mapper接口要分包存放。
4. 编写Mapper包扫描配置类，使用@MapperScans注解分别为不同的数据源配置不同的mapper接口扫描路径。
5. 测试注解式查询，没有问题，两个数据源都生效了。
6. 【xml式语句，始终无法加载成功，尝试将xml放在resource、类包，尝试在配置文件中写xml的位置、也尝试了在sqlSessionFactory中写xml的位置，将netty换位tomcat，均不生效】
7. 经过多方排查，最终找出xml中sql绑定失败的问题的原因：
~~~
a.所数据源场景下，不能再application.yaml中指定MyBatis的sql-xml，要在sql-session-factory的配置类中指定sql-xml配置文件的位置。
b.此时不论将sql-xml放在resource目录（建议放到资源目录下），还是java类的包下，只需要该sql-session-factory加载配置文件的路径就可以。
【之所以各种尝试都失败了是我将xml目录搞交叉了，导致接口和xml文件不能映射】
~~~
  