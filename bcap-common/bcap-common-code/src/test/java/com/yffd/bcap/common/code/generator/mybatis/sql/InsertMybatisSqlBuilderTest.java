package com.yffd.bcap.common.code.generator.mybatis.sql;

import com.yffd.bcap.common.code.generator.model.BaseEntity;
import com.yffd.bcap.common.code.generator.model.MyUserEntity;
import com.yffd.bcap.common.code.generator.sql.InsertMybatisSqlBuilder;
import org.junit.Test;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月20日 上午11:42:44 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class InsertMybatisSqlBuilderTest {

	private InsertMybatisSqlBuilder builder = new InsertMybatisSqlBuilder();
	
	@Test
	public void buildSqlInsertOneTest() {
		String result = this.builder.buildSqlInsertOne(MyUserEntity.class, BaseEntity.class);
		System.out.println(result);
	}
	
	@Test
	public void buildSqlInsertBatchTest() {
		String result = this.builder.buildSqlInsertBatch(MyUserEntity.class, BaseEntity.class);
		System.out.println(result);
	}
}

