package com.ceit.ico.dao;

import java.util.List;
import javax.ejb.Remote;
import com.vic.beans.CodeName;

@Remote
public interface CodeNameDao {
	public List<CodeName> getAllCodeNames();
	public List<CodeName> getCodeNamesByCodes(String[] codeNameSelected);
	public boolean insertCodeName(CodeName codeName);
	public boolean updateCodeName(CodeName codeName);
}