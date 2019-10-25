package cn.gdchent.springbootmybatismultidatasource.vo;

import lombok.Data;

/**
 * @author: gdchent
 * @date: 2019/10/15
 * @description: 返回的最外面的bean对象
 * 3个字段：code msg data
 */
@Data
public class ResultVO<T> {
    //错误码
    private Integer code;
    //提示信息
    private String msg;

    //具体数据内容
    private T data;
}
