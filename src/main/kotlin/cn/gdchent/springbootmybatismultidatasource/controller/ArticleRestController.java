package cn.gdchent.springbootmybatismultidatasource.controller;

import cn.gdchent.springbootmybatismultidatasource.model.AjaxResponse;
import cn.gdchent.springbootmybatismultidatasource.service.ArticleRestMybatisService;
import cn.gdchent.springbootmybatismultidatasource.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: gdchent
 * @date: 2019/10/16
 * @description:
 */
@Slf4j
@RestController //作用：1 将ArticleRestController注入上下文环境 2 对整个类的所有的方法返回的json xml这种数据的返回
@RequestMapping(value = "/rest")
public class ArticleRestController {


    @Resource(name = "articleRestMybatisServiceImpl") //mybatis方式操作数据库
    private ArticleRestMybatisService articleRestMybatisService;



//    @PostMapping(value = "/article") //接收带一个一个的参数
//    public @ResponseBody
//    AjaxResponse saveArticle(
//            @RequestParam Long id,
//            @RequestParam String author
//    ) {
//        log.info("post_params");
//        //第一步 拿到前端传递过来的参数  设置给bean对象
//        ArticleVO articleVO = new ArticleVO();
//        articleVO.setAuthor(author);
//        articleVO.setId(id);
//        //第二步 将实体bean通过fastjson解析为字符串 返回给前端
//        String string = JSON.toJSONString(articleVO);
//        log.info("string:" + string);
//        log.info("saveArticle");
//        return AjaxResponse.success(articleVO);
//    }

    //保存数据
    //@RequestMapping(value = "/article",method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
    @PostMapping(value = "/article", produces = "application/json;charset=UTF-8")
    public @ResponseBody
    AjaxResponse saveArticle(@RequestBody ArticleVO articleVO) { //前端要传入这个参数 这个接收对象
        log.info("saveArticle");
        log.info(articleVO.toString());
        articleRestMybatisService.saveArticle(articleVO);
        return AjaxResponse.success(articleVO);
    }



    //删除数据
    //@RequestMapping(value = "/article",method = RequestMethod.DELETE,produces = "application/x-www-form-urlencoded;charset=UTF-8")
    @DeleteMapping(value = "/article/{id}")
    public @ResponseBody
    AjaxResponse deleteArticle(@PathVariable Long id) {
        log.info("deleteArticle", id);
        return AjaxResponse.success(id);
    }

    //通过id修改某一遍文章
    @PutMapping("article/{id}")
    public @ResponseBody
    AjaxResponse updateArticle(@PathVariable Long id, @RequestBody ArticleVO articleVO) {
        articleVO.setId(id);
        return AjaxResponse.success(articleVO);
    }

    //通过id获取某一遍文章
    @GetMapping("/article/{id}") //通过restful风格 这里要爨地id的
    public @ResponseBody
    AjaxResponse getArticle(@PathVariable Long id) {
        log.info("id:" + id);
        return AjaxResponse.success();
    }

    //获取所有的数据
    @GetMapping("/article")
    public @ResponseBody
    AjaxResponse getAllArticle() {
        List<ArticleVO> articleVOList=articleRestMybatisService.getAll();
        return AjaxResponse.success(articleVOList);
    }


}
