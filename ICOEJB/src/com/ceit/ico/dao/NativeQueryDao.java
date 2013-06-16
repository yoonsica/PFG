package com.ceit.ico.dao;

import java.util.List;
import javax.ejb.Remote;

import com.ceit.ico.dto.CodeNameDTO;

@Remote
public interface NativeQueryDao {
	/**
	 * ��ѯsql���
	 * @param functionId ����id
	 * @param queryTypeId ��ѯ������ͣ�1-��¼��2-������
	 * @return sql���
	 */
	public String getQuerySql(String functionId,String queryTypeId);
	
	/**
	 * ���ҳ���ѯ������sql������������λ�б��豸�����б��
	 * @param id FZ_CONDITION���е�id�ֶ�
	 * @return sql���
	 */
	public String getConditionSql(String id);
	
	/**
	 * ����豸��������λ����ѹ�ȼ��ȣ��ļ���
	 * @param id
	 * @param addAll �Ƿ����ȫ����code=-1��
	 * @return
	 */
	public List<CodeNameDTO> getCodeNameDTOList(String id, boolean addAll);

	
}