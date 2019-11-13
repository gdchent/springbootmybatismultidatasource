package cn.gdchent.springbootmybatismultidatasource.vo.nba;

import lombok.Data;

/**
 * nbateam
 * @author 
 */
@Data
public class NbateamVO {
    /**
     * nba球队id
     */
    private Integer nbaId;

    /**
     * 球队名称
     */
    private String nbaName;

    /**
     * 球员名字
     */
    private String nbaPlayer;


}