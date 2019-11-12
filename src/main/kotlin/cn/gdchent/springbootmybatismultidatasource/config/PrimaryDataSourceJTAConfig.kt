package cn.gdchent.springbootmybatismultidatasource.config

import org.mybatis.spring.SqlSessionTemplate
import org.apache.ibatis.session.SqlSessionFactory
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
 *@create:2019-11-12 10:22
 *@Description:
 */
@Configuration
@EnableConfigurationProperties
@EnableAutoConfiguration
@MapperScan(basePackages = ["cn.gdchent.springbootmybatismultidatasource.generator.gdchent"],
        sqlSessionTemplateRef = "primaryJTASqlSessionTemplate")//注意这里
class PrimaryDataSourceJTAConfig {

    companion object {
        val PACKAGE="cn.gdchent.springbootmybatismultidatasource.generator.gdchent"
    }
    @Bean("primaryJTADataSource")
    @ConfigurationProperties(prefix = "primarydb")
    fun primaryDataSource(): DataSource {
        val ds=AtomikosDataSourceBean()
        //ds.xaDataSourceClassName="com.mysql.cj.jdbc.MysqlXADataSource"  //自己定义 一般是配置在yml文件里面
        return ds
    }

    @Bean("primaryJTASqlSessionFactory")
    @Throws(Exception::class)
    fun primaryJTASqlSessionFactory(@Qualifier("primaryJTADataSource") dataSource: DataSource): SqlSessionFactory? {
        val bean = SqlSessionFactoryBean()
        bean.setDataSource(dataSource)
        //因为Mapper和Mapper.xml我放在同一个文件夹所以不用设资源路径
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        bean.setTypeAliasesPackage(PACKAGE)  //这里需要修改为你的扫描类路径
        return bean.getObject()
    }

    @Bean("primaryJTASqlSessionTemplate")
    @Throws(Exception::class)
    fun primaryJTASqlSessionTemplate(
            @Qualifier("primaryJTASqlSessionFactory") sqlSessionFactory: SqlSessionFactory): SqlSessionTemplate {
        return SqlSessionTemplate(sqlSessionFactory)
    }
}