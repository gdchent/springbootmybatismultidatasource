package cn.gdchent.springbootmybatismultidatasource

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
/**
 * @auther:gdchent
 * @create:2019-10-25 16:30
 * @Description:
 */
@SpringBootApplication
//@MapperScan("cn.gdchent.springbootmybatismultidatasource.generator")  //本来如果只是进行单个mybatis数据配置的时候
class SpringbootmybatismultidatasourceApplication

fun main(args: Array<String>) {
    runApplication<SpringbootmybatismultidatasourceApplication>(*args)
}
