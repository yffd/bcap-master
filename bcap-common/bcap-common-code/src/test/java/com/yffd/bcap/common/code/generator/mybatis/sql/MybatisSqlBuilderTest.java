package com.yffd.bcap.common.code.generator.mybatis.sql;

import java.util.List;

import com.yffd.bcap.common.code.generator.model.BaseEntity;
import com.yffd.bcap.common.code.generator.model.MyUserEntity;
import com.yffd.bcap.common.code.generator.sql.MybatisSqlBuilder;
import com.yffd.bcap.common.support.util.CollectionUtil;
import org.junit.Test;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月18日 下午4:12:24 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class MybatisSqlBuilderTest {

	private MybatisSqlBuilder sqlBuilder = new MybatisSqlBuilder();
	
	@Test
	public void prop2columnTest() {
		List<MybatisSqlBuilder.TableColumn> result = sqlBuilder.prop2column(MyUserEntity.class, BaseEntity.class);
		if (CollectionUtil.isEmpty(result)) {
			System.out.println("未解析到属性"); 
			return;
		} 
		for (MybatisSqlBuilder.TableColumn tc : result) {
			System.out.println(tc.getColName() + ", " + tc.getPropName() + ", " + tc.getPropType().getName());
		}
	}
	
	@Test
	public void entityName2tableNameTest() {
		String entityPrefix = "Uumc";
		String entitySuffix = "Entity";
		String tablePrefix = "Table";
		String tableSuffix = "View";
		Class<?> entityClazz = MyUserEntity.class;
		String tableName = this.sqlBuilder.entityName2tableName(entityClazz, entityPrefix, entitySuffix, tablePrefix, tableSuffix);
		System.out.println(tableName);
	}
	
	@Test
	public void linesFmtTest() {
		String suffix = null;
		String prefix = "\t";
		StringBuilder sb = new StringBuilder();
		sb.append("aaaaaaaaa").append("\r\n");
		sb.append("bbbbbbbbb");
		String text = sb.toString();
		String result = this.sqlBuilder.linesFmt(text , prefix , suffix );
		System.out.println(result);
	}
}

