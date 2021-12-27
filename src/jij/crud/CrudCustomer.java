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
	//SqlSession 생성, 삽입, 삭제, 변경, 조회
	private final String PATH = "jij/config/mybatis_config.xml";// 환경설정 파일 경로
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
		}finally {// 예외가 없으면 종료
			ss.close();
		}
		return result;
	}
	
	public int deleteCustomer(HashMap map){// 삭제
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
	
	public int updateCustomer(CustomerInfo info){// 변경
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
	
	private SqlSession getSession() {// SqlSession 생성하고 리턴
		InputStream is = null;//파일을 처리하는 객체
		try {
			is = Resources.getResourceAsStream(PATH);// 환경설정 파일을 염
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		// SqlSession은  SqlSessionFactory가 생성함
		// SqlSessionFactory는 SqlSessionFactoryBuilder가 생성함
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(is);
		SqlSession ss = factory.openSession();
		return ss;
	}
}
