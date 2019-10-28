package cn.gdchent.springbootmybatismultidatasource.model;

import lombok.Data;

/**
 * @author: gdchent
 * @date: 2019/10/16
 * @description:
 */
@Data
public class AjaxResponse<T> {
    private boolean isok;
    private int code;
    private String message;
    private T data;

    public AjaxResponse() {
    }

    public static AjaxResponse success(){
        AjaxResponse ajaxResponse=new AjaxResponse();
        ajaxResponse.setIsok(true);
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage("success");
        return ajaxResponse ;
    }

    public static AjaxResponse success(Object data){
        AjaxResponse response=new AjaxResponse();
        response.setIsok(true);
        response.setCode(200);
        response.setMessage("success");
        response.setData(data);
        return response ;
    }
}
