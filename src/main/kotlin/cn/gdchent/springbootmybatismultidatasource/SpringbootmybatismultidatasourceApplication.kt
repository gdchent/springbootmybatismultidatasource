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
