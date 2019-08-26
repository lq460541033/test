package cn.itsource.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import cn.itsource.dao.IStudentDao;
import cn.itsource.domain.Student;
import cn.itsource.util.MyBatisUtil;

public class StudentDaoImpl   implements IStudentDao {
	 
	@Override
	public void save(Student obj) {
		//通过工具类获取SqlSession对象
		SqlSession session = MyBatisUtil.INSTANCE.openSession();
		
		try {
			session.insert("cn.itsource.domain.StudentMapper.save", obj);
			//提交事务
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
 
	@Override
	public void update(Student obj) {
		//通过工具类获取SqlSession对象
		SqlSession session = MyBatisUtil.INSTANCE.openSession();
		try {
			session.update("cn.itsource.domain.StudentMapper.update", obj);
			//提交事务
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
 
	@Override
	public void delete(Long id) {
		//通过工具类获取SqlSession对象
		SqlSession session = MyBatisUtil.INSTANCE.openSession();
		try {
			session.delete("cn.itsource.domain.StudentMapper.delete", id);
			//提交事务
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
 
	@Override
	public Student queryOne(Long id) {
		//通过工具类获取SqlSession对象
		SqlSession session = MyBatisUtil.INSTANCE.openSession();
		try {
			//通过session执行sql语句，并返回结果
			return session.selectOne("cn.itsource.domain.StudentMapper.queryOne", id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}
 
	@Override
	public List<Student> queryAll() {
		//通过工具类获取SqlSession对象
		SqlSession session = MyBatisUtil.INSTANCE.openSession();
		try {
			return session.selectList("cn.itsource.domain.StudentMapper.queryAll");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return null;
	}


	 
}