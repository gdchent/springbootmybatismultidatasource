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