package com.sxt.Auser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxt.Auser.domain.Reply;
import com.sxt.Auser.vo.ReplyVo;

public interface ReplyMapper {
    int deleteByPrimaryKey(Integer rno);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Integer rno);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);

	List<Reply> queryAllReplies();

	int queryCountReplyNum(@Param("replyuser")String replyuser);

	List<Reply> queryInfoByReplyUser(@Param("replyuser")String replyuser);

	ReplyVo queryExpertReplyMoreDetail(@Param("forconsultid")int forconsultid, @Param("replyuser")String replyuser,
			@Param("expert")String expert);

	List<Reply> queryReplyContent(@Param("forconsultid")int forconsultid);

	int queryAboutAddInfo(@Param("expert")String expert, @Param("content")String content, @Param("forconsultid")int forconsultid,
			@Param("replyuser")String replyuser);
}