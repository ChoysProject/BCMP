package cys;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class itemCrud {
	//SqlSession 생성 및 삽입, 삭제,변경,조회 등!
	private final String PATH = 
			"cys/mybatis_config.xml";
	private final String NAME="Itemmapper";
	
	public List<Item_info2> getiteminfo(HashMap map){
		SqlSession ss = getSession();
		List<Item_info2> list = null;
		try {
			list = ss.selectList(NAME+".getiteminfo", map);
		}finally {
			ss.close();
		}
		return list;
	}
	
	public int updateitem(Item_info2 si) {
		SqlSession ss = getSession();
		int result = 0;
		try {
			result = ss.update("Itemmapper.updateitem",si);
			if(result > 0) {ss.commit();
			}else {
				ss.rollback();
			}
		}finally {
			ss.close();
		}
		return result;
	}
	
	public int deleteitem(HashMap map) {
		SqlSession ss = getSession();
		int result = 0;
		try {
			result = ss.delete("Itemmapper.deleteitem",map);
			if(result > 0 ) {
				ss.commit();
			}else {ss.rollback();
			}
		}finally {
			ss.close();
		}
		return result;
	}
	
	public int putitem(Item_info2 ii) {
		SqlSession ss = getSession();
		int result = 0;
		try {
			result = ss.insert("Itemmapper.putitem",ii);
			if(result >0) {ss.commit();
			}else {ss.rollback();}
		}finally {
			ss.close();
		}
		return result;
	}
	
	private SqlSession getSession(){//SqlSession 생성
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
