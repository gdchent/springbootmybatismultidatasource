package cn.gdchent.springbootmybatismultidatasource.service

import cn.gdchent.springbootmybatismultidatasource.form.NbaForm
import cn.gdchent.springbootmybatismultidatasource.vo.nba.NbateamVO

/**
 *@auther:gdchent
 *@create:2019-11-12 17:22
 *@Description:
 */
interface NbaMybatisService {

    fun insertNba(form: NbaForm): NbateamVO
}