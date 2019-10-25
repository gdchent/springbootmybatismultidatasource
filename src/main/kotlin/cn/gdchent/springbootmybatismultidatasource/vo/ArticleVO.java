package cn.gdchent.springbootmybatismultidatasource.vo;


import cn.gdchent.springbootmybatismultidatasource.model.Reader;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * @author: gdchent
 * @date: 2019/10/16
 * @description:这个VO是前端界面的Bean
 */
//@AllArgsConstructor  //创建有参数构造器 用这个构造器的注解 该bean如果有Date属性使用
@NoArgsConstructor   //创建无参数构造器
@Data   //可以设置set跟get方法
//@Builder
@JsonPropertyOrder(value = {"content", "title"})
@DynamicUpdate  //修改时间,对应数据库的updateTime字段自动修改
@Table(name = "article") //如果表名跟类名不一样 java的mysql的表名这里要标明
public class ArticleVO {

    @JsonIgnore  //表示可以忽略 这个属性 排列属性不做序列化
    private Long id;
    @JsonProperty("auther")   //就是为属性起个别名 返回给前端也可以接收前端的这个名字
    private String author;
    private String title;
    @JsonInclude(JsonInclude.Include.NON_NULL) //不为空的时候进行序列化
    private String content;

    @JsonInclude(JsonInclude.Include.NON_NULL) //不为空的时候进行序列化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @JsonProperty("create_time")
    private Date createTime;

    private List<Reader> reader;

}
