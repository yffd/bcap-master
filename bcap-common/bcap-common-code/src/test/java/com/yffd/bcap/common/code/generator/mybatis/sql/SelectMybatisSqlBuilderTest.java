package com.yffd.bcap.common.code.generator.mybatis.sql;

import com.yffd.bcap.common.code.generator.model.BaseEntity;
import com.yffd.bcap.common.code.generator.model.MyUserEntity;
import com.yffd.bcap.common.code.generator.sql.SelectMybatisSqlBuilder;
import org.junit.Test;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月26日 下午1:56:31 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SelectMybatisSqlBuilderTest {
	private SelectMybatisSqlBuilder builder = new SelectMybatisSqlBuilder();
	
	@Test
	public void buildSelectOneByTest() {
		String result = this.builder.buildSelectOneBy(MyUserEntity.class);
		System.out.println(result);
	}
	
	@Test
	public void buildSelectCountByTest() {
		String result = this.builder.buildSelectCountBy();
		System.out.println(result);
	}
	
	@Test
	public void buildSelectListByTest() {
		String result = this.builder.buildSelectListBy(MyUserEntity.class);
		System.out.println(result);
	}
	
	@Test
	public void buildWhereTest() {
		String result = this.builder.buildWhere(MyUserEntity.class, BaseEntity.class);
		System.out.println(result);
	}
}

