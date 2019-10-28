#### spring boot 整合jta-atomikos
第一步引入maven依赖:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jta-atomikos</artifactId>
</dependency>
```
第二步引入双数据源配置
```yml
primarydb:
  uniqueResourceName: primary
  xaDataSourceClassName: com.mysql.cj.jdbc.jdbc2.optional.MysqlXADataSource
  xaProperties:
    url: jdbc:mysql://localhost/gdchent?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
    user: root
    password: gdchent0330
  exclusiveConnectionMode: true
  minPoolSize: 3
  maxPoolSize: 10
  testQuery: SELECT 1 from dual #由于采用HikiriCP，用于检测数据库连接是否存活。

secondarydb:
  uniqueResourceName: secondary
  xaDataSourceClassName: com.mysql.cj.jdbc.jdbc2.optional.MysqlXADataSource
  xaProperties:
    url: jdbc:mysql://localhost/gdchent2?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
    user: root
    password: gdchent0330
  exclusiveConnectionMode: true
  minPoolSize: 3
  maxPoolSize: 10
  testQuery: SELECT 1 from dual #由于采用HikiriCP，用于检测数据库连接是否存活
```

##### 配置多数据源以及事务管理

数据源一：primarydb
```kotlin
package cn.gdchent.springbootmybatismultidatasource.config

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource
import org.mybatis.spring.SqlSessionFactoryBean
import org.apache.ibatis.session.SqlSessionFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.mybatis.spring.SqlSessionTemplate
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean

/**
 * @auther:gdchent
 * @create:2019-10-25 16:40
 * @Description:mybatis多数据源配置
 */
@Configuration
@MapperScan(
        basePackages = ["cn.gdchent.springbootmybatismultidatasource.generator.gdchent"],
        sqlSessionTemplateRef = "primarySqlSessionTemplate"
         )
class PrimaryDataSourceConfig {

    @Bean(name = ["primaryDataSource"])
    @ConfigurationProperties(prefix = "primarydb")
    @Primary
    fun testDataSource(): DataSource {
        return return AtomikosDataSourceBean()  //分布式任务
    }

    @Bean(name = ["primarySqlSessionFactory"])
    @Primary
    @Throws(Exception::class)
    fun primarySqlSessionFactory(@Qualifier("primaryDataSource") dataSource: DataSource): SqlSessionFactory? {
        val bean = SqlSessionFactoryBean()
        bean.setDataSource(dataSource)
        bean.setTypeAliasesPackage("cn.gdchent.springbootmybatismultidatasource.generator.gdchent") //分布式任务这里要加上
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test1/*.xml"));
        return bean.getObject()
    }


    @Bean(name = ["primaryTransactionManager"])
    @Primary
    fun primaryTransactionManager(@Qualifier("primaryDataSource") dataSource: DataSource): DataSourceTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }

    @Bean(name = ["primarySqlSessionTemplate"])
    @Primary
    @Throws(Exception::class)
    fun testSqlSessionTemplate(@Qualifier("primarySqlSessionFactory") sqlSessionFactory: SqlSessionFactory): SqlSessionTemplate {
        return SqlSessionTemplate(sqlSessionFactory)
    }
}
```
数据源二:secondarydb
```kotlin
package cn.gdchent.springbootmybatismultidatasource.config

import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Configuration
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import javax.sql.DataSource
import org.mybatis.spring.SqlSessionTemplate
import org.apache.ibatis.session.SqlSessionFactory
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.mybatis.spring.SqlSessionFactoryBean
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean

/**
 * @auther:gdchent
 * @create:2019-10-25 16:49
 * @Description:mybatis多数据源配置
 */
@Configuration
@MapperScan(basePackages = ["cn.gdchent.springbootmybatismultidatasource.generator.gdchent2"],
        sqlSessionTemplateRef = "secondarySqlSessionTemplate")
class SecondaryDataSourceConfig {

    @Bean(name = ["secondaryDataSource"])
    @ConfigurationProperties(prefix = "secondarydb") //读取application.yml文件对应的secondary
    fun testDataSource(): DataSource {
        return AtomikosDataSourceBean() //分布式任务
    }

    @Bean(name = ["secondarySqlSessionFactory"])
    @Throws(Exception::class)
    fun secondarySqlSessionFactory(@Qualifier("secondaryDataSource") dataSource: DataSource): SqlSessionFactory? {
        val bean = SqlSessionFactoryBean()
        bean.setDataSource(dataSource)
        bean.setTypeAliasesPackage("cn.gdchent.springbootmybatismultidatasource.generator.gdchent2") //使用分布式任务要加上这行
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test1/*.xml"));
        return bean.getObject()
    }

    @Bean(name = ["secondaryTransactionManager"])
    fun secondaryTransactionManager(@Qualifier("secondaryDataSource") dataSource: DataSource): DataSourceTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }

    @Bean(name = ["secondarySqlSessionTemplate"])
    @Throws(Exception::class)
    fun testSqlSessionTemplate(@Qualifier("secondarySqlSessionFactory") sqlSessionFactory: SqlSessionFactory)  : SqlSessionTemplate  {
        return SqlSessionTemplate(sqlSessionFactory)
    }

}
```
事务管理器  
```java
package cn.gdchent.springbootmybatismultidatasource.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * @auther:gdchent
 * @create:2019-10-28 12:42
 * @Description:
 */
@Configuration
@EnableTransactionManagement
public class XATransactionManagerConfig {

    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }

    @Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
    public TransactionManager atomikosTransactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    @Bean(name = "transactionManager")
    @DependsOn({ "userTransaction", "atomikosTransactionManager" })
    public PlatformTransactionManager transactionManager() throws Throwable {
        return new JtaTransactionManager(userTransaction(),atomikosTransactionManager());
    }
}
```
spring boot @Transactional注解事务不回滚不起作用无效  
https://blog.csdn.net/zdyueguanyun/article/details/80236401

