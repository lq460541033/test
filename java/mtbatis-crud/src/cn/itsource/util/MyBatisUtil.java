package cn.itsource.util;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public enum MyBatisUtil {
	
	/**
	 *    MyBatis三大核心对象特点：
	 *    SqlSessionFactoryBuilder——创建了SqlSessionFactory后，这个类就不需要存在了
	 * 	  SqlSessionFactory——被创建，SqlSessionFactory应该在你的应用执行期间都存在
	 * 	  SqlSession——每个线程都应该有它自己的SqlSession实例。SqlSession的实例不能被共享，也是线程不安全的
	 * */
	
	INSTANCE;
	
	//SqlSessionFactory只需要一个
	private static SqlSessionFactory factory;
	
	/**
	 *  把factory放在静态代码块中的原因？
	 *  	因为factory只需要创建一个，而静态代码块中的代码只会执行一次
	 * */
	static{
		try {
			//创建一个SqlSession工厂对象，读取我们的核心配置文件
			factory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsStream("mybatis-config.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  直接返回一个SqlSession对象
	 * */
	public SqlSession openSession(){
		//通过SqlSessionFactory创建SqlSession对象
		return factory.openSession();
	}


}
