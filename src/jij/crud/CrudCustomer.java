package jij.crud;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import jij.dto.CustomerInfo;

public class CrudCustomer {
	//SqlSession ����, ����, ����, ����, ��ȸ
	private final String PATH = "jij/config/mybatis_config.xml";// ȯ�漳�� ���� ���
	private final String NAME = "CustomerMapper.";
	
	public List<CustomerInfo> selectInfo(Map<String, Object> c){
		SqlSession ss = getSession();
		List<CustomerInfo> list = null;
		try {
			list = ss.selectList(NAME + "cust_info", c);
		}finally {
			ss.close();
		}
		return list;
	}
	
	public int selectCount(String id) {
		SqlSession ss = getSession();
		int result = 0;
		try {
			result = ss.selectOne(NAME + "cust_cnt", id);
		}finally {
			ss.close();
		}
		return result;
	}
	
	public int insertCustom(CustomerInfo info) {
		SqlSession ss = getSession();
		int result = 0;
		try {
			result = ss.insert(NAME + "cust_insert", info);
			if(result > 0) {
				ss.commit();
			}else {
				ss.rollback();
			}
		}finally {// ���ܰ� ������ ����
			ss.close();
		}
		return result;
	}
	
	public int deleteCustomer(HashMap map){// ����
		SqlSession ss = getSession();
		int result = 0;
		try {
			result = ss.delete(NAME + "cust_delete", map);
			if(result > 0) {
				ss.commit();
			}else {
				ss.rollback();
			}
		}finally {
			ss.close();
		}
		return result;
	}
	
	public int updateCustomer(CustomerInfo info){// ����
		SqlSession ss = getSession();
		int result = 0;
		try {
			result = ss.update(NAME + "cust_update", info);
			if(result > 0) {
				ss.commit();
			}else {
				ss.rollback();
			}
		}finally {
			ss.close();
		}
		return result;
	}
	
	private SqlSession getSession() {// SqlSession �����ϰ� ����
		InputStream is = null;//������ ó���ϴ� ��ü
		try {
			is = Resources.getResourceAsStream(PATH);// ȯ�漳�� ������ ��
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		// SqlSession��  SqlSessionFactory�� ������
		// SqlSessionFactory�� SqlSessionFactoryBuilder�� ������
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(is);
		SqlSession ss = factory.openSession();
		return ss;
	}
}
