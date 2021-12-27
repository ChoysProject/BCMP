package cys;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class qnaCrud {
	//SqlSession ���� �� ����, ����,����,��ȸ ��!
	private final String PATH = 
			"cys/mybatis_config.xml";
	private final String NAME="qnamapper";
	
	public List<Qna_2> getqna(HashMap map){
		SqlSession ss = getSession();
		List<Qna_2> list = null;
		try {
			list = ss.selectList(NAME+".getqna", map);
		}finally {
			ss.close();
		}
		return list;
	}
	
	public int deleteqna(HashMap map) {
		SqlSession ss = getSession();
		int result = 0;
		try {
			result = ss.delete("qnamapper.deleteqna",map);
			if(result > 0 ) {
				ss.commit();
			}else {ss.rollback();
			}
		}finally {
			ss.close();
		}
		return result;
	}
	
	public int updateqna(Qna_2 q2) {
		SqlSession ss = getSession();
		int result = 0;
		try {
			result = ss.update("qnamapper.updateqna",q2);
			if(result > 0) {ss.commit();
			}else {
				ss.rollback();
			}
		}finally {
			ss.close();
		}
		return result;
	}
	
	public int putqna(Qna_2 q2) {
		SqlSession ss = getSession();
		int result = 0;
		try {
			result = ss.insert("qnamapper.putqna",q2);
			if(result >0) {ss.commit();
			}else {ss.rollback();}
		}finally {
			ss.close();
		}
		return result;
	}
	private SqlSession getSession(){//SqlSession ����
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(PATH);
		}catch(Exception e) {
		}
		SqlSessionFactoryBuilder builder = 
				new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = 
				builder.build(is);
		SqlSession ss = 
				factory.openSession();
		return ss;
	}
}
