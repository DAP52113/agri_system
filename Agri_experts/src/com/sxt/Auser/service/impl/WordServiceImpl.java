package com.sxt.Auser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxt.Auser.domain.Words;
import com.sxt.Auser.mapper.WordsMapper;
import com.sxt.Auser.service.WordService;

@Service
public class WordServiceImpl implements WordService {

	@Autowired
	private WordsMapper wordsMapper;
	
	@Override
	public List<Words> queryAllWords() {
		// TODO Auto-generated method stub
		return this.wordsMapper.queryAllWordsInfo();
	}

	@Override
	public void addReplyContent(Words words) {
		// 添加回复
		this.wordsMapper.insertSelective(words);
	}

}
