package cn.gdchent.springbootmybatismultidatasource.service.impl

import cn.gdchent.springbootmybatismultidatasource.form.NbaForm
import cn.gdchent.springbootmybatismultidatasource.generator.gdchent3.nbateam.Nbateam
import cn.gdchent.springbootmybatismultidatasource.generator.gdchent3.nbateam.NbateamMapper
import cn.gdchent.springbootmybatismultidatasource.service.NbaMybatisService
import cn.gdchent.springbootmybatismultidatasource.vo.nba.NbateamVO
import org.dozer.Mapper
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 *@auther:gdchent
 *@create:2019-11-12 17:26
 *@Description:插入数据
 */
@Service
class NbaMybatisMybatisServiceImpl : NbaMybatisService {


    // 要注入2个东西   一个是dozermapper 进行对象转换
    @Resource
    private lateinit var dozerMapper: Mapper

    // 就是通过mybatis生成的 mapper对象来操作数据库
    @Resource
    private lateinit var nbateamMapper: NbateamMapper

    override fun insertNba(form: NbaForm): NbateamVO {
        val nbaTeam = dozerMapper.map(form, Nbateam::class.java)
        nbateamMapper.insert(nbaTeam)
        return dozerMapper.map(form, NbateamVO::class.java)
    }
}