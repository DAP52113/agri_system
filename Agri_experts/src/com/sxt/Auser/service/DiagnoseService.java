package com.sxt.Auser.service;

import java.util.List;

import com.sxt.Auser.domain.Diagnose;

public interface DiagnoseService {

	void addDiagnose(Diagnose diagnose);

	List<Diagnose> queryAllDiagnoses();

}
