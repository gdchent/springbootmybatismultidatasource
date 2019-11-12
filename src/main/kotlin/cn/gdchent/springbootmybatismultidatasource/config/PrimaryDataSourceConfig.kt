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

    //@Primary  //整个工程只允许有一个Primary的datasource
    @Bean(name = ["primaryDataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    fun testDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }
    //@Primary
    @Bean(name = ["primarySqlSessionFactory"])
    @Throws(Exception::class)
    fun testSqlSessionFactory(@Qualifier("primaryDataSource") dataSource: DataSource): SqlSessionFactory? {
        val bean = SqlSessionFactoryBean()
        bean.setDataSource(dataSource)
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/test1/*.xml"));
        return bean.getObject()
    }

    //@Primary
    @Bean(name = ["primaryTransactionManager"])
    fun testTransactionManager(@Qualifier("primaryDataSource") dataSource: DataSource): DataSourceTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }
    //@Primary
    @Bean(name = ["primarySqlSessionTemplate"])
    @Throws(Exception::class)
    fun testSqlSessionTemplate(@Qualifier("primarySqlSessionFactory") sqlSessionFactory: SqlSessionFactory): SqlSessionTemplate {
        return SqlSessionTemplate(sqlSessionFactory)
    }

}