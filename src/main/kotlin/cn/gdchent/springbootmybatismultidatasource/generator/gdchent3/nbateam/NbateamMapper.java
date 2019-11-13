package cn.gdchent.springbootmybatismultidatasource.generator.gdchent3.nbateam;

import cn.gdchent.springbootmybatismultidatasource.generator.gdchent3.nbateam.Nbateam;
import cn.gdchent.springbootmybatismultidatasource.generator.gdchent3.nbateam.NbateamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NbateamMapper {
    long countByExample(NbateamExample example);

    int deleteByExample(NbateamExample example);

    int deleteByPrimaryKey(Integer nbaId);

    int insert(Nbateam record);

    int insertSelective(Nbateam record);

    List<Nbateam> selectByExample(NbateamExample example);

    Nbateam selectByPrimaryKey(Integer nbaId);

    int updateByExampleSelective(@Param("record") Nbateam record, @Param("example") NbateamExample example);

    int updateByExample(@Param("record") Nbateam record, @Param("example") NbateamExample example);

    int updateByPrimaryKeySelective(Nbateam record);

    int updateByPrimaryKey(Nbateam record);
}