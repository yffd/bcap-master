package com.yffd.bcap.common.code.generator.file;

import com.yffd.bcap.common.code.generator.model.BaseDao;
import com.yffd.bcap.common.code.generator.model.MyUserEntity;
import org.junit.Test;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月27日 上午10:25:02 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class DaoFileGeneratorTest {
	private DaoFileGenerator generator = new DaoFileGenerator();
	
	@Test
	public void test() {
		String author = "ZhangST";
		Class<?> entityClazz = MyUserEntity.class;
		Class<?> daoSuperClazz = BaseDao.class;
		String daoPackageName = "com.yffd.xxx.dao";
		generator.writeToConsole(entityClazz, daoSuperClazz, daoPackageName, author);
		
		String outRootDirPath = "D:\\ddd\\code";
		boolean covered = true;
		
		generator.writeToFile(entityClazz, daoSuperClazz, daoPackageName, author, outRootDirPath, covered);
	}
}

