package edu.du.sb1011.mapper;

import edu.du.sb1011.dto.MemberDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Select("select * from t_member where member_idx= #{memberIdx}")
    MemberDto getOneByIdx(int memberIdx);
    @Select("select * from t_member where id = #{id}")
    MemberDto getOneById(String id);
    @Select("select * from t_member")
    List<MemberDto> getAll();
    @Insert("insert into t_member(name, id, password, join_datetime) values(#{name}, #{id}, #{password}, now())")
    int addOne(MemberDto dto);
    @Update("update t_member set name = #{name}, password=#{password} where member_idx = #{memberIdx}")
    int setOne(MemberDto dto);
    @Delete("delete from t_member where member_idx = #{memberIdx}")
    int delOne(int memberIdx);
    @Select("select max(member_idx) from t_member")
    int getMaxIdx();
    @Select("select password from t_member where member_idx = #{memberIdx}")
    String getPasswordByIdx(int memberIdx);
}

