package cn.gdchent.springbootmybatismultidatasource.service.impl

import cn.gdchent.springbootmybatismultidatasource.form.MessageForm
import cn.gdchent.springbootmybatismultidatasource.generator.gdchent2.message.Message
import cn.gdchent.springbootmybatismultidatasource.generator.gdchent2.message.MessageMapper
import cn.gdchent.springbootmybatismultidatasource.service.MessageService
import cn.gdchent.springbootmybatismultidatasource.vo.message.MessageVO
import org.dozer.Mapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *@auther:gdchent
 *@create:2019-11-13 10:08
 *@Description:
 */
@Service
class MessageServiceImpl:MessageService {

    @Autowired
    private lateinit var dozerMapper:Mapper
    @Autowired
    private lateinit var messageMapper:MessageMapper

    override fun insertMessage(messageForm: MessageForm): MessageVO {

        val message=dozerMapper.map(messageForm,Message::class.java)
        //需要传入一个Message对象
        messageMapper.insert(message)
        return dozerMapper.map(messageForm,MessageVO::class.java)
    }
}