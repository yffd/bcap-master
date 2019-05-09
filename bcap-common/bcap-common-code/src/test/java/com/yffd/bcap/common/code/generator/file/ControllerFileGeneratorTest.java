package com.yffd.bcap.common.code.generator.file;

import com.yffd.bcap.common.code.generator.model.BaseController;
import com.yffd.bcap.common.code.generator.model.MyUserEntity;
import org.junit.Test;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月27日 下午2:33:11 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class ControllerFileGeneratorTest {

	private ControllerFileGenerator generator = new ControllerFileGenerator();
	
	@Test
	public void test() {
		String author = "ZhangST";
		Class<?> entityClazz = MyUserEntity.class;
		Class<?> controllerSuperClazz = BaseController.class;
		String controllerPackageName = "com.yffd.xxx.controller";
		String servicePackageName = "com.yffd.xxx.service";
		generator.writeToConsole(entityClazz, controllerSuperClazz, controllerPackageName, servicePackageName, author);
		
		String outRootDirPath = "D:\\ddd\\code";
		boolean covered = true;
		
		generator.writeToFile(entityClazz, controllerSuperClazz, controllerPackageName, servicePackageName, author, outRootDirPath, covered);
	
	}
}

