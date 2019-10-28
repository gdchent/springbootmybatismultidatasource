##### spring boot通过mybatis插件生成mybatisMapper
详情参见github文档  
https://github.com/kmaster/better-mybatis-generator 
![image](https://github.com/gdchent/springbootmybatismultidatasource/blob/master/effectImg/指定mybatis插件生成map文件的目录.png)
![image](https://github.com/gdchent/springbootmybatismultidatasource/blob/master/effectImg/测试数据源跟驱动.png)
![image](https://github.com/gdchent/springbootmybatismultidatasource/blob/master/effectImg/选择要连接的数据库.png)
##### spring boot mybatis多数据源配置
1.**application.yml**文件配置mysql
```yml
spring:
  http:
    encoding:
      charset: utf-8
      force: false
      enabled: true
  datasource:
    primary:
      jdbc-url: jdbc:mysql://localhost/gdchent?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
      username: root
      password: gdchent0330
      driver-class-name: com.mysql.cj.jdbc.Driver
    secondary:
      jdbc-url: jdbc:mysql://localhost/gdchent2?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
      username: root
      password: gdchent0330
      driver-class-name: com.mysql.cj.jdbc.Driver
logging:
        level:
          cn.gdchent.springbootmybatismultidatasource: debug

#mybatis:
#  configuration:
#    map-underscore-to-camel-case: true
# 生成的mapper文件都是放在同一个文件下 不用配置
#mybatis
#    mapper-locations: classpath:generator/*.xml
server:
  #   自定义端口号：8888
  port: 8888

```
2.主数据源配置
1.**PrimaryDataSourceConfig.java**文件  
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
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    @Primary
    fun testDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean(name = ["primarySqlSessionFactory"])
    @Primary
    @Throws(Exception::class)
    fun testSqlSessionFactory(@Qualifier("primaryDataSource") dataSource: DataSource): SqlSessionFactory? {
        val bean = SqlSessionFactoryBean()
        bean.setDataSource(dataSource)
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test1/*.xml"));
        return bean.getObject()
    }


    @Bean(name = ["primaryTransactionManager"])
    @Primary
    fun testTransactionManager(@Qualifier("primaryDataSource") dataSource: DataSource): DataSourceTransactionManager {
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

2.**SecondaryDataSourceConfig.java**文件:

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
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    fun testDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean(name = ["secondarySqlSessionFactory"])
    @Throws(Exception::class)
    fun testSqlSessionFactory(@Qualifier("secondaryDataSource") dataSource: DataSource): SqlSessionFactory? {
        val bean = SqlSessionFactoryBean()
        bean.setDataSource(dataSource)
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test1/*.xml"));
        return bean.getObject()
    }

    @Bean(name = ["secondaryTransactionManager"])
    fun testTransactionManager(@Qualifier("secondaryDataSource") dataSource: DataSource): DataSourceTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }

    @Bean(name = ["secondarySqlSessionTemplate"])
    @Throws(Exception::class)
    fun testSqlSessionTemplate(@Qualifier("secondarySqlSessionFactory") sqlSessionFactory: SqlSessionFactory): SqlSessionTemplate {
        return SqlSessionTemplate(sqlSessionFactory)
    }
}
```
**SpringbootmybatismultidatasourceApplication.kotlin**文件
```kotlin
package cn.gdchent.springbootmybatismultidatasource

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.dozer.DozerBeanMapper
import org.dozer.Mapper
import org.springframework.context.annotation.Bean


/**
 * @auther:gdchent
 * @create:2019-10-25 16:30
 * @Description:
 */
@SpringBootApplication
//@MapperScan("cn.gdchent.springbootmybatismultidatasource.generator")  //本来如果只是进行单个mybatis数据配置的时候
class SpringbootmybatismultidatasourceApplication{
    //不加以下代码运行会报错
    //https://stackoverflow.com/questions/48867307/spring-boot-controller-required-a-bean-of-type-org-dozer-mapper-that-could-not
    @Bean
    fun mapper(): Mapper {
        return DozerBeanMapper()
    }
}

fun main(args: Array<String>) {
    runApplication<SpringbootmybatismultidatasourceApplication>(*args)
}


```
注意:如果不在SpringbootmybatismultidatasourceApplication.kt文件中添加以下代码
```kotlin
 @Bean
    fun mapper(): Mapper {
        return DozerBeanMapper()
    }
```
报错如下:
![image](https://github.com/gdchent/springbootmybatismultidatasource/blob/master/effectImg/mybatis多源数据库配置可能会报以下错误图.png)
解决方案如下:
https://stackoverflow.com/questions/48867307/spring-boot-controller-required-a-bean-of-type-org-dozer-mapper-that-could-not
