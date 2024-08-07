package com.sxt.Auser.service;

import java.util.List;

import com.sxt.Auser.domain.Diagnosereply;

public interface DiagnoseReplyService {

	List<Diagnosereply> queryAllDiagnoseReplies();

	void addDiagnoseReply(Diagnosereply diagnosereply);

}
