package cn.gdchent.springbootmybatismultidatasource.config

import org.springframework.transaction.jta.JtaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.context.annotation.DependsOn
import com.atomikos.icatch.jta.UserTransactionManager
import com.atomikos.icatch.jta.UserTransactionImp
import org.springframework.context.annotation.Bean
import javax.transaction.TransactionManager
import javax.transaction.UserTransaction


/**
 *@auther:gdchent
 *@create:2019-11-12 10:29
 *@Description:配置多数据源事务管理
 * 虽然我们将数据源及其相关配置分成了两组，但这两组数据源使用的事务管理器必须是同一个，这样才能实现分布式事务
 */
class XATransactionManagerConfig {
    //User事务
    @Bean(name = ["userTransaction"])
    @Throws(Throwable::class)
    fun userTransaction(): UserTransaction {
        val userTransactionImp = UserTransactionImp()
        userTransactionImp.setTransactionTimeout(10000)
        return userTransactionImp
    }

    //分布式事务
    @Bean(name = ["atomikosTransactionManager"], initMethod = "init", destroyMethod = "close")
    @Throws(Throwable::class)
    fun atomikosTransactionManager(): TransactionManager {
        val userTransactionManager = UserTransactionManager()
        userTransactionManager.forceShutdown = false
        return userTransactionManager
    }

    //事务管理器
    @Bean(name = ["transactionManager"])
    @DependsOn("userTransaction", "atomikosTransactionManager")
    @Throws(Throwable::class)
    fun transactionManager(): PlatformTransactionManager {
        return JtaTransactionManager(userTransaction(), atomikosTransactionManager())
    }
}