package jij.crud;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import jij.dto.CustomerInfo;

public class CrudLogin {
	//SqlSession 생성, 삽입, 삭제, 변경, 조회
	private final String PATH = "jij/config/mybatis_config.xml";// 환경설정 파일 경로
	private final String NAME = "LoginMapper.";
	
	public CustomerInfo getId(CustomerInfo id) {
		SqlSession ss = getSession();
		CustomerInfo ci = null;
		try {
			ci = ss.selectOne(NAME + "get_id", id);
		}finally {
			ss.close();
		}
		return ci;
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
