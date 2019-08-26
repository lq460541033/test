package cn.itsource.dao;

import java.util.List;

import cn.itsource.domain.Student;

public interface IStudentDao {
	
   void save(Student obj);
	
	void update(Student obj);
	
	void delete(Long id);
	
	Student queryOne(Long id);
	
	List<Student> queryAll();


}
