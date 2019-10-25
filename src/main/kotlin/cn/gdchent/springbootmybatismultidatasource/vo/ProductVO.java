package cn.gdchent.springbootmybatismultidatasource.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: gdchent
 * @date: 2019/10/15
 * @description:商品（包含类目）
 */
@Data
public class ProductVO {

    @JsonProperty("name") //前端看到的字段是name
    private String categoryName; //后端定义的对应数据库表中的字段categoryName

    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList ;


}
