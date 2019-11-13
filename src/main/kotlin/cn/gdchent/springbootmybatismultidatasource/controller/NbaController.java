package cn.gdchent.springbootmybatismultidatasource.controller;


import cn.gdchent.springbootmybatismultidatasource.form.NbaForm;
import cn.gdchent.springbootmybatismultidatasource.service.NbaMybatisService;
import cn.gdchent.springbootmybatismultidatasource.vo.ResultVO;
import cn.gdchent.springbootmybatismultidatasource.vo.nba.NbateamVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @auther:gdchent
 * @create:2019-11-12 17:20
 * @Description:测试mybatis+jta方式多源是否起作用
 */
@RestController
@RequestMapping("/nba")
@Slf4j
public class NbaController {

    @Resource(name = "nbaMybatisMybatisServiceImpl")
    private NbaMybatisService nbaMybatisService;


    @PostMapping(path= {"/insertNbaTeam"},produces = {"application/json;charset=UTF-8"})
    public @ResponseBody
    ResultVO<NbateamVO> insertDept(@RequestBody NbaForm nbaForm){
        log.info("form:"+nbaForm);
        NbateamVO nbateamVO =nbaMybatisService.insertNba(nbaForm);
        return ResultVO.success(nbateamVO);
    }
}
