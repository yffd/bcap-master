package com.yffd.bcap.common.code.generator.mybatis.sql;

import com.yffd.bcap.common.code.generator.model.BaseEntity;
import com.yffd.bcap.common.code.generator.model.MyUserEntity;
import com.yffd.bcap.common.code.generator.sql.DeleteMybatisSqlBuilder;
import org.junit.Test;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月19日 下午3:22:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class DeleteMybatisSqlBuilderTest {
	private DeleteMybatisSqlBuilder builder = new DeleteMybatisSqlBuilder();
	
	@Test
	public void buildSqlDeleteTest() {
		String result = this.builder.buildSqlDelete(MyUserEntity.class, BaseEntity.class);
		System.out.println(result);
	}
}

