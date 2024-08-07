package com.sxt.Auser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.Auser.domain.Reply;
import com.sxt.Auser.mapper.ReplyMapper;
import com.sxt.Auser.service.ReplyService;
import com.sxt.Auser.vo.ReplyVo;

/*
 * 专家回复服务类
 * */
@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;
	
	@Override
	public List<Reply> queryAllReplies() {
		// 查询所有的专家回复信息
		return this.replyMapper.queryAllReplies();
	}

	@Override
	public void addReply(Reply reply) {
		// 添加回复
		this.replyMapper.insertSelective(reply);
	}

	@Override
	public int queryCountReplyNum(String replyuser) {
		// TODO Auto-generated method stub
		return this.replyMapper.queryCountReplyNum(replyuser);
	}

	@Override
	public List<Reply> queryInfoByReplyUser(String replyuser) {
		// TODO Auto-generated method stub
		return this.replyMapper.queryInfoByReplyUser(replyuser);
	}

	@Override
	public ReplyVo queryExpertReplyMoreDetail(int forconsultid,
			String replyuser, String expert) {
		// TODO Auto-generated method stub
		return this.replyMapper.queryExpertReplyMoreDetail(forconsultid,replyuser,expert);
	}

	@Override
	public List<Reply> queryReplyContent(int forconsultid) {
		// TODO Auto-generated method stub
		return this.replyMapper.queryReplyContent(forconsultid);
	}

	@Override
	public int queryAboutAddInfo(String expert, String content,
			int forconsultid, String replyuser) {
		// TODO Auto-generated method stub
		return this.replyMapper.queryAboutAddInfo(expert,content,forconsultid,replyuser);
	}



}
