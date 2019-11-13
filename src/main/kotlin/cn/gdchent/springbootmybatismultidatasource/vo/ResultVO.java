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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    //函数式泛型 根据参数返回什么类型
    public static <T> ResultVO<T> success(T data){
        ResultVO response=new ResultVO();
        response.setCode(200);
        response.setMsg("success");
        response.setData(data);
        return response ;
    }
}
