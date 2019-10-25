package cn.gdchent.springbootmybatismultidatasource.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: gdchent
 * @date: 2019/10/15
 * @description:商品详情
 */
@Data
public class ProductInfoVO {

    @JsonProperty("id") //前端返回需要的json字段名称
    private String productId; //后端自己定义的对应表的id名称

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice ;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productionIcon;


}
