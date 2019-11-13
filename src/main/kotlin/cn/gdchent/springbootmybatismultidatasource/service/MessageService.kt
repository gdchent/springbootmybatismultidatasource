package cn.gdchent.springbootmybatismultidatasource.service

import cn.gdchent.springbootmybatismultidatasource.form.MessageForm
import cn.gdchent.springbootmybatismultidatasource.vo.message.MessageVO

/**
 *@auther:gdchent
 *@create:2019-11-13 10:08
 *@Description:
 */
interface MessageService {
     fun insertMessage(messageForm: MessageForm):MessageVO

}