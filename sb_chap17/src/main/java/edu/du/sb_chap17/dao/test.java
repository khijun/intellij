package edu.du.sb_chap17.dao;

import edu.du.sb_chap17.model.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.util.List;

    @Mapper
    public interface test {

        @Select("select count(*) from article")
        public int selectCount();

        @Select("select article_id, group_id, sequence_no, posting_date, read_count, writer_name, password, title," +
                "content from article order by sequence_no desc limit #{firstRow}, #{endRow}")
        public List<Article> select(int firstRow, int endRow);

        //	@Insert("select last_insert_id() from article")
        @Insert("insert into article (group_id, sequence_no, posting_date, read_count, writer_name, password, title, content) " +
                "values (#{groupId}, #{sequenceNumber}, #{postingDate}, 0, #{writerName}, #{password}, #{title}, #{content})")
        public int insert(Article article) throws SQLException;

        @Select("select * from article where article_id = #{articleId}")
        public Article selectById(int articleId) throws SQLException;

        @Update("update article set read_count = read_count + 1 where article_id = #{articleId}")
        public void increaseReadCount(int articleId) throws SQLException;

//					("select min(sequence_no) from article where sequence_no < ? and sequence_no >= ?")
//	public String selectLastSequenceNumber(Connection conn,
//			String searchMaxSeqNum, String searchMinSeqNum)
//	throws SQLException;

//		("update article set title = ?, content = ? where article_id = ?")
//	public int update(Connection conn, Article article) throws SQLException;

//		("delete from article where article_id = ?")
//	public void delete(Connection conn, int articleId) throws SQLException;

    }