package cn.gdchent.springbootmybatismultidatasource.config

import org.mybatis.spring.SqlSessionTemplate
import org.apache.ibatis.session.SqlSessionFactory
import org.springframework.context.annotation.Primary
import org.mybatis.spring.SqlSessionFactoryBean
import org.springframework.boot.context.properties.ConfigurationProperties
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


/**
 *@auther:gdchent
 *@create:2019-11-12 11:06
 *@Description:
 */
@Configuration
@EnableConfigurationProperties
@EnableAutoConfiguration
@MapperScan(basePackages = ["cn.gdchent.springbootmybatismultidatasource.generator.gdchent3"],
        sqlSessionTemplateRef = "secondaryJTASqlSessionTemplate")//注意这里
class SecondaryDataSourceJTAConfig {
    @Primary
    @Bean("secondaryJTADataSource")
    @ConfigurationProperties(prefix = "secondarydb")
    fun secondaryDataSource(): DataSource {
        val ds=AtomikosDataSourceBean()
        //ds.xaDataSourceClassName="com.mysql.cj.jdbc.MysqlXADataSource"
        return ds
    }
    @Primary
    @Bean("secondaryJTASqlSessionFactory")
    @Throws(Exception::class)
    fun secondaryJTASqlSessionFactory(@Qualifier("secondaryJTADataSource") dataSource: DataSource): SqlSessionFactory? {
        val bean = SqlSessionFactoryBean()
        bean.setDataSource(dataSource)
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        bean.setTypeAliasesPackage("cn.gdchent.springbootmybatismultidatasource.generator.gdchent3")   //注意这里
        return bean.getObject()
    }
    @Primary
    @Bean("secondaryJTASqlSessionTemplate")
    @Throws(Exception::class)
    fun secondaryJTASqlSessionTemplate(
            @Qualifier("secondaryJTASqlSessionFactory") sqlSessionFactory: SqlSessionFactory): SqlSessionTemplate {
        return SqlSessionTemplate(sqlSessionFactory)
    }
}
