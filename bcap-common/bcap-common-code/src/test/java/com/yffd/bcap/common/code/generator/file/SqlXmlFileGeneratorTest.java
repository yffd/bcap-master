package com.yffd.bcap.common.code.generator.file;

import com.yffd.bcap.common.code.generator.model.BaseEntity;
import com.yffd.bcap.common.code.generator.model.MyUserEntity;
import org.junit.Test;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月26日 下午5:07:17 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SqlXmlFileGeneratorTest {
	
	@Test
	public void test() {
		SqlXmlFileGenerator generator = new SqlXmlFileGenerator();
		Class<?> baseEntityClazz = BaseEntity.class;
		Class<?> entityClazz = MyUserEntity.class;
		String sqlNamespace = entityClazz.getName();
		String outRootDirPath = "D:\\ddd\\code";
		
		generator.writeToConsole(entityClazz, baseEntityClazz, sqlNamespace);
		generator.writeToFile(entityClazz, baseEntityClazz, sqlNamespace, outRootDirPath, true);
		System.out.println("已生成XML文件");
	}
	
}

