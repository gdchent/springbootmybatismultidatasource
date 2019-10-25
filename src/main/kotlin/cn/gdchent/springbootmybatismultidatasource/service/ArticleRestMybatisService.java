package cn.gdchent.springbootmybatismultidatasource.service;

import cn.gdchent.springbootmybatismultidatasource.vo.ArticleVO;

import java.util.List;

public interface ArticleRestMybatisService {

    ArticleVO saveArticle(ArticleVO article);

    void deleteArticle(Long id);

    void updateArticle(ArticleVO article);

    ArticleVO getArticle(Long id);

    List<ArticleVO> getAll();

}
