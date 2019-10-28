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