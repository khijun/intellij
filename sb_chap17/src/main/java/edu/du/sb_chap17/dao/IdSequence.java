package edu.du.sb_chap17.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface IdSequence {
    @Select("select next_value from id_sequence where sequence_name = #{sequenceName} for update")
    public int getValueBySequenceName(String sequenceName);
    @Update("update id_sequence set next_value = #{id} where sequence_name = #{sequenceName}")
    public void updateValueBySequenceName(String sequenceName, int id);
}
