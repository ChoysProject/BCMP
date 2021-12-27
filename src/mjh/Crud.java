package mjh;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Crud {
	private final String PATH=
			"mjh/mybatis_config.xml";
	
	public int updateReturn(ReturnInfo ri) {
		SqlSession ss = getSession();
		int result =0;
		try {
			result=ss.update("returnmapper.return_update",ri);
			if(result>0)ss.commit();
			ss.rollback();
		}finally {
			ss.close();
		}
		return result;
	}
	public int deleteReturn(HashMap map) {
		SqlSession ss= getSession();
		int result=0;
		try {result=ss.delete("returnmapper.return_delete",map);
			if(result>0)ss.commit();
			ss.rollback();
		}finally {
			ss.close();
		}
		return result;
	}
	
	public int returnCount(int number) {
		SqlSession ss= getSession();
		int result=0;
		try {result=ss.selectOne("returnmapper.return_cnt",number);
			if(result>0)ss.commit();
			ss.rollback();
		}finally {
			ss.close();
		}
		return result;
	}
	public int putReturn(ReturnInfo ri) {
		SqlSession ss =getSession();
		int result =0;
		try {result=ss.insert("returnmapper.return_insert",ri);
			if(result>0)ss.commit();
			ss.rollback();
		}finally {
			ss.close();
		}
		return result;
	}
	
	public List<ReturnInfo> getReturn(HashMap map){
		SqlSession ss = getSession();
		List<ReturnInfo> list = null;
		try {
			list=ss.selectList("returnmapper.getReturnInfo",map);
		}finally {
			ss.close();
		}
		return list;
	}
	
	
	
	
	
	private SqlSession getSession() {
		InputStream is=null;
		try {
			is=Resources.getResourceAsStream(PATH);
		}catch(Exception e) {}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory =builder.build(is);
		SqlSession ss=factory.openSession();
		return ss;
	}
	
	
	
}
