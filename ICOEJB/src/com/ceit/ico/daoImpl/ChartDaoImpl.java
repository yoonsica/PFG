package com.ceit.ico.daoImpl;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ceit.ico.dao.ChartDao;
import com.vic.beans.Chart;

@Stateless
public class ChartDaoImpl implements ChartDao {

	@PersistenceContext
	protected EntityManager em;

	@Override
	public List<Chart> getCharts(String pageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Chart chart) {
		try {
			em.persist(chart);
		} catch (Exception e) {
			System.out.println("error************");
			e.printStackTrace();
		}
		System.out.println("插入成功");
	}

	@Override
	public void update(Chart chart) {
		try {
			em.merge(chart);
		} catch (Exception e) {
			System.out.println("error************");
			e.printStackTrace();
		}
		System.out.println("更新成功");
	}

	@Override
	public Chart getChartById(String chartId) {
		return em.find(Chart.class, chartId);
	}

	@Override
	public List<Chart> findAllCharts() {
		Query query=null;
		List<Chart> list =null;
		try {
			query = em.createQuery("select t from Chart t order by t.chartId");
			list = query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public List nativeQuery(String sql) {
		Query query=null;
		List list =null;
		try {
			query = em.createNativeQuery(sql);
			list = query.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List nameQuery(String sql, Map<String, String[]> paraMap) {
		Query query=null;
		List list =null;
		try {
			query = em.createNativeQuery(sql);
			Matcher m = Pattern.compile("([.]*):([\\w]+)([\\s]*[,]*)").matcher(sql);
			if (null==paraMap) {
				while (m.find()) {
					System.out.println(m.group(2));
					if (m.group(2).equals("dateStart")) {
						query.setParameter(m.group(2), "2003-1-1");
					}else if (m.group(2).equals("dateEnd")) {
						query.setParameter(m.group(2), "2013-1-1");
					}else {
						query.setParameter(m.group(2), "%%");
					}
				}
			}else {
				while (m.find()) {
					System.out.println(m.group(1)+":"+m.group(2));
					String[] paras = paraMap.get(m.group(2));
					if (paras!=null&&!paras[0].equals("all")) {
						StringBuilder value= new StringBuilder();
						for (String para : paras) {
							value.append(para).append(",");
						}
						query.setParameter(m.group(2), value.substring(0, value.length()-1));
					}else {
						if (m.group(2).equals("dateStart")) {
							query.setParameter(m.group(2), "2003-1-1");
						}else if (m.group(2).equals("dateEnd")) {
							query.setParameter(m.group(2), "2013-1-1");
						}else {
							query.setParameter(m.group(2), "%%");
						}
					}
				}
			}
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
