package khj.crud;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import khj.dto.OrderDTO;

public class CrudOrder {
//	SqlSession 생성 및 삽입, 삭제, 변경, 조회
	private final String PATH = "khj/config/mybatis_config.xml";
//	Mybatis Config 파일의 경로 및 이름
	private final String NAME = "orderMapper.";

	public int updateOrder(OrderDTO od) {
		SqlSession ss = getSession();
		int result = 0;
		try {
			result = ss.update(NAME + "updateOrder", od);
			if (result > 0)
				ss.commit();
			else
				ss.rollback();
		} finally {
			// TODO: handle finally clause
			ss.close();
		}
		return result;
	}

	public int deleteOrder(HashMap map) {
		SqlSession ss = getSession();
		int result = 0;
		try {
			result = ss.insert(NAME + "deleteOrder", map);
			if (result > 0)
				ss.commit();
			else
				ss.rollback();
		} finally {
			// TODO: handle finally clause
			ss.close();
		}
		return result;
	}

	public int insertOrder(OrderDTO od) {
		SqlSession ss = getSession();
		int result = 0;
		try {
			result = ss.insert(NAME + "insertOrder", od);
			if (result > 0)
				ss.commit();
			else
				ss.rollback();
		} finally {
			// TODO: handle finally clause
			ss.close();
		}
		return result;
	}

	public List<OrderDTO> allOrder() {
		SqlSession ss = getSession();
		List<OrderDTO> list = null;
		try {
			list = ss.selectList(NAME + "allOrder");
		} finally {
			// TODO: handle finally clause
			ss.close();
		}
		return list;
	}

	public int duplicateCheck(String id) {
		SqlSession ss = getSession();
		int result = 0;
		try {
			result = ss.selectOne(NAME + "duplicateCheck", id);
		} finally {
			// TODO: handle finally clause
			ss.close();
		}
		return result;
	}

	public List<OrderDTO> getOrderDTO(HashMap map) {
		SqlSession ss = getSession();
		List<OrderDTO> list = null;
		try {
			list = ss.selectList(NAME + "searchOrder", map);
		} finally {
			// TODO: handle finally clause
			ss.close();
		}
		return list;
	}

	private SqlSession getSession() {
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(PATH);
		} catch (Exception e) {
			// TODO: handle exception
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(is);
		SqlSession ss = factory.openSession();
		return ss;
	}
}