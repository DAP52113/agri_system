package com.sxt.Auser.service;

import java.util.List;

import com.sxt.Auser.domain.Reply;
import com.sxt.Auser.vo.ReplyVo;

public interface ReplyService {

	List<Reply> queryAllReplies();

	void addReply(Reply reply);

	int queryCountReplyNum(String replyuser);

	List<Reply> queryInfoByReplyUser(String replyuser);

	ReplyVo queryExpertReplyMoreDetail(int forconsultid, String replyuser,
			String expert);

	List<Reply> queryReplyContent(int forconsultid);

	int queryAboutAddInfo(String expert, String content, int forconsultid,
			String replyuser);

	

}
