package cn.gdchent.springbootmybatismultidatasource.service.impl;


import cn.gdchent.springbootmybatismultidatasource.generator.gdchent.Article;
import cn.gdchent.springbootmybatismultidatasource.generator.gdchent.ArticleMapper;
import cn.gdchent.springbootmybatismultidatasource.generator.gdchent2.Message;
import cn.gdchent.springbootmybatismultidatasource.generator.gdchent2.MessageMapper;
import cn.gdchent.springbootmybatismultidatasource.service.ArticleRestMybatisService;
import cn.gdchent.springbootmybatismultidatasource.utils.DozerUtils;
import cn.gdchent.springbootmybatismultidatasource.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ArticleRestMybatisServiceImpl implements ArticleRestMybatisService {

    @Resource
    private Mapper dozerMapper;

    @Resource
    private ArticleMapper articleMapper ; //通过mybatis的插件生成的mapper对象

    @Resource
    private MessageMapper messageMapper ;//通过mybatis的插件生成的mapper对象


    @Override
    public ArticleVO saveArticle(ArticleVO articleVO) {

        Article articlePO=dozerMapper.map(articleVO, Article.class); //这个Article.class是generator目录下的
        articleMapper.insert(articlePO);
        Message message=new Message();
        message.setName("装逼messageName");
        message.setContent("装逼内容");

        //然后通过messageMapper插入数据
        messageMapper.insert(message);
        return articleVO;
    }

    @Override
    public void deleteArticle(Long id) {
        articleMapper.deleteByPrimaryKey(Math.toIntExact(id));
    }

    @Override
    public void updateArticle(ArticleVO article) {
       Article articlePO=dozerMapper.map(article,Article.class);
       articleMapper.updateByPrimaryKeySelective(articlePO);
    }

    @Override
    public ArticleVO getArticle(Long id) {
        return dozerMapper.map(articleMapper.selectByPrimaryKey(Math.toIntExact(id)),ArticleVO.class);
    }

    @Override
    public List<ArticleVO> getAll() {
        List<Article> articles=articleMapper.selectByExample(null);
        return DozerUtils.mapList(articles,ArticleVO.class);
    }

}
