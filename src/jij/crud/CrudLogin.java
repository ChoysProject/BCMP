package jij.crud;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import jij.dto.CustomerInfo;

public class CrudLogin {
	//SqlSession ����, ����, ����, ����, ��ȸ
	private final String PATH = "jij/config/mybatis_config.xml";// ȯ�漳�� ���� ���
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
