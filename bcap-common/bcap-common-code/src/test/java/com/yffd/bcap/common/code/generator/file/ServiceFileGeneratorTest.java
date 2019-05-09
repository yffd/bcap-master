package com.yffd.bcap.common.code.generator.file;

import com.yffd.bcap.common.code.generator.model.BaseService;
import com.yffd.bcap.common.code.generator.model.MyUserEntity;
import org.junit.Test;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月27日 上午10:45:00 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class ServiceFileGeneratorTest {

	private ServiceFileGenerator generator = new ServiceFileGenerator();
	
	@Test
	public void test() {
		String author = "ZhangST";
		Class<?> entityClazz = MyUserEntity.class;
		Class<?> serviceSuperClazz = BaseService.class;
		String servicePackageName = "com.yffd.xxx.service";
		String daoPackageName = "com.yffd.xxx.dao";
		generator.writeToConsole(entityClazz, serviceSuperClazz, servicePackageName, daoPackageName, author);
		
		String outRootDirPath = "D:\\ddd\\code";
		boolean covered = true;
		
		generator.writeToFile(entityClazz, serviceSuperClazz, servicePackageName, daoPackageName, author, outRootDirPath, covered);
	
	}
}

