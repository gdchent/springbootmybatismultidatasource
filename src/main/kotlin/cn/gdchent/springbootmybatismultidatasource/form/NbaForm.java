package cn.gdchent.springbootmybatismultidatasource.form;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @auther:gdchent
 * @create:2019-11-12 17:22
 * @Description:
 */
public class NbaForm {
    /**
     * 球队名称
     */
    @JsonProperty("name")
    private String nbaName;

    /**
     * 球员名字
     */
    @JsonProperty("player")
    private String nbaPlayer;
}
