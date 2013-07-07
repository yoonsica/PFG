package com.vic.ico.serviceImp;

import java.util.List;

import com.ceit.ico.dao.CodeNameDao;
import com.vic.beans.CodeName;
import com.vic.ico.service.CodeNameService;

public class CodeNameServiceImp implements CodeNameService {
	private CodeNameDao codeNameDao;
	public CodeNameDao getCodeNameDao() {
		return codeNameDao;
	}
	public void setCodeNameDao(CodeNameDao codeNameDao) {
		this.codeNameDao = codeNameDao;
	}
	@Override
	public List<CodeName> getAllCodeName() {
		List<CodeName> list = codeNameDao.getAllCodeNames();
		return list;
	}

}
