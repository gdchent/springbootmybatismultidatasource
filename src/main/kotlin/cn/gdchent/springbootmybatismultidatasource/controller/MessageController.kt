package cn.gdchent.springbootmybatismultidatasource.controller

import cn.gdchent.springbootmybatismultidatasource.form.MessageForm
import cn.gdchent.springbootmybatismultidatasource.service.MessageService
import cn.gdchent.springbootmybatismultidatasource.vo.ResultVO
import cn.gdchent.springbootmybatismultidatasource.vo.message.MessageVO
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *@auther:gdchent
 *@create:2019-11-13 10:10
 *@Description:
 */
@RestController  //作用：1 将ArticleRestController注入上下文环境 2 对整个类的所有的方法返回的json xml这种数据的返回
@RequestMapping(path = ["/rest"])
class MessageController {

    @Autowired
    private lateinit var messageService: MessageService
    @PostMapping(path = ["/insertMessage"],produces = ["application/json;charset=UTF-8"])
    fun insertMessage(@RequestBody messageForm: MessageForm):ResultVO<MessageVO>{
        //接收前端参数body
        val messageVO=messageService.insertMessage(messageForm)
        return ResultVO.success(messageVO)
    }
}