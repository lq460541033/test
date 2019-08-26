package cn.itsource.dao;

import java.util.List;

import org.junit.Test;

import cn.itsource.dao.impl.StudentDaoImpl;
import cn.itsource.domain.Student;

public class StudentDaoTest {
	
IStudentDao dao = new StudentDaoImpl();	
	
	@Test
	public void testSave() {
		Student stu = new Student();
		stu.setUsername("Å®Éñ2");
		stu.setPassword("222");
		dao.save(stu);
	}
 
	@Test
	public void testUpdate() {
		Student stu = dao.queryOne(2l);
		stu.setUsername("Ë§¸ç");
		dao.update(stu);
	}
 
	@Test
	public void testDelete() {
		dao.delete(3L);
	}
 
	@Test
	public void testQueryOne() {
		Student stu = dao.queryOne(2L);
		System.out.println(stu);
	}
 
	@Test
	public void testQueryAll() {
		List<Student> stu = dao.queryAll();
		for (Student student : stu) {
			System.out.println(student);
		}
	}


}
