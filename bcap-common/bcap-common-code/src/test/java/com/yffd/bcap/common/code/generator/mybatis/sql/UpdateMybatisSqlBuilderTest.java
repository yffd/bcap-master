package com.yffd.bcap.common.code.generator.mybatis.sql;

import com.yffd.bcap.common.code.generator.model.BaseEntity;
import com.yffd.bcap.common.code.generator.model.MyUserEntity;
import com.yffd.bcap.common.code.generator.sql.UpdateMybatisSqlBuilder;
import org.junit.Test;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月20日 下午2:18:23 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UpdateMybatisSqlBuilderTest {

	private UpdateMybatisSqlBuilder builder = new UpdateMybatisSqlBuilder();
	
	@Test
	public void buildSqlUpdateTest() {
		String result = this.builder.buildSqlUpdate(MyUserEntity.class, BaseEntity.class);
		System.out.println(result);
	}
}

