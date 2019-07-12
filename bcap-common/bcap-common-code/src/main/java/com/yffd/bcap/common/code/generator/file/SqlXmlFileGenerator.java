package com.yffd.bcap.common.code.generator.file;

import com.yffd.bcap.common.code.generator.sql.DeleteMybatisSqlBuilder;
import com.yffd.bcap.common.code.generator.sql.InsertMybatisSqlBuilder;
import com.yffd.bcap.common.code.generator.sql.SelectMybatisSqlBuilder;
import com.yffd.bcap.common.code.generator.sql.UpdateMybatisSqlBuilder;

import java.io.File;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月26日 下午4:34:06 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SqlXmlFileGenerator extends FileGenerator {

	private SelectMybatisSqlBuilder selectBuilder = new SelectMybatisSqlBuilder();
	private InsertMybatisSqlBuilder insertBuilder = new InsertMybatisSqlBuilder();
	private UpdateMybatisSqlBuilder updateBuilder = new UpdateMybatisSqlBuilder();
	private DeleteMybatisSqlBuilder deleteBuilder = new DeleteMybatisSqlBuilder();
	
	public void writeAllToFile(Class<?> baserootEntityClazz, String rootEntityRootDirPath, String rootEntityPackageName, String sqlNamespace, String outRootDirPath, boolean covered) throws Exception {
		if(!rootEntityRootDirPath.endsWith(File.separator)) rootEntityRootDirPath += File.separator;
		String rootEntityPackageDirPath = rootEntityPackageName.replace(".", File.separator);
		String fullDirPath = rootEntityRootDirPath + rootEntityPackageDirPath;
		
		File file = new File(fullDirPath);
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File childFile : files) {
				if(childFile.isDirectory()) {
					continue;
				} else {
					boolean skip = false;
					String javaFileName = childFile.getName();	// 不包含路径
					for(String skipName : this.getSkiprootEntityList()) {
						if(javaFileName.contains(skipName)) {
							skip = true;
							continue;
						}
					}
					if(!skip) {
						int endIndex = javaFileName.lastIndexOf(".java");
						if(endIndex!=-1) {
							javaFileName = javaFileName.substring(0, endIndex);
						}
						String rootEntityFullClassName = rootEntityPackageName + "." + javaFileName;
						Class<?> rootEntityClazz = Class.forName(rootEntityFullClassName);
						this.writeToFile(rootEntityClazz, baserootEntityClazz, sqlNamespace, outRootDirPath, covered);
					}
				}
			}
		}
	}
	
	public void writeToFile(Class<?> rootEntityClazz, Class<?> baserootEntityClazz, String sqlNamespace, String outRootDirPath, boolean covered) {
		String fileName = this.rootEntityFmt(rootEntityClazz, null, "rootEntity", null, "Mapper") + ".xml";
		String filePath = outRootDirPath + File.separator + fileName;
		String content = this.makeContent(sqlNamespace, rootEntityClazz, baserootEntityClazz);
		this.makedirs(outRootDirPath);
		System.out.println("文件位置：" + outRootDirPath);
		this.writeToFile(content, filePath, covered);
		
	}
	
	public void writeToConsole(Class<?> rootEntityClazz, Class<?> baserootEntityClazz, String sqlNamespace) {
		String content = this.makeContent(sqlNamespace, rootEntityClazz, baserootEntityClazz);
		System.out.println(content);
		
	}
	
	protected String makeContent(String sqlNamespace, Class<?> rootEntityClazz, Class<?> baserootEntityClazz) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("\r\n");
		sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">").append("\r\n");
		sb.append("<mapper namespace=\"" + sqlNamespace + "\">").append("\r\n");
		
		sb.append("\r\n").append("\r\n");
		sb.append("\t").append("<!-- ######################################################################### -->").append("\r\n");
		sb.append("\t").append("<!-- #########################   base sql begin    ######################### -->").append("\r\n");
		sb.append("\t").append("<!-- ######################################################################### -->").append("\r\n");
		sb.append("\t").append("\r\n");
		
		String tableNameStr = this.selectBuilder.buildTableName(rootEntityClazz);
		sb.append(selectBuilder.linesFmt(tableNameStr, "\t", ""));
//		
		String tableColumnsStr = this.selectBuilder.buildTableColumns(rootEntityClazz, baserootEntityClazz);
		sb.append(selectBuilder.linesFmt(tableColumnsStr, "\t", ""));
		
		String conditionsLimitStr = this.selectBuilder.buildLimit();
		sb.append(selectBuilder.linesFmt(conditionsLimitStr, "\t", ""));
		
		String conditionsOrderbyStr = this.selectBuilder.buildOrderBy();
		sb.append(selectBuilder.linesFmt(conditionsOrderbyStr, "\t", ""));
		
		String conditionsWhereStr = this.selectBuilder.buildWhere(rootEntityClazz, baserootEntityClazz);
		sb.append(selectBuilder.linesFmt(conditionsWhereStr, "\t", ""));
		
		String selectListByStr = this.selectBuilder.buildSelectListBy(rootEntityClazz);
		sb.append(selectBuilder.linesFmt(selectListByStr, "\t", ""));
		
		String selectCountByStr = this.selectBuilder.buildSelectCountBy();
		sb.append(selectBuilder.linesFmt(selectCountByStr, "\t", ""));
		
		String selectOneByStr = this.selectBuilder.buildSelectOneBy(rootEntityClazz);
		sb.append(selectBuilder.linesFmt(selectOneByStr, "\t", ""));
		
		String insertOneByStr = this.insertBuilder.buildSqlInsertOne(rootEntityClazz, baserootEntityClazz);
		sb.append(selectBuilder.linesFmt(insertOneByStr, "\t", ""));
		
		String insertBatchByStr = this.insertBuilder.buildSqlInsertBatch(rootEntityClazz, baserootEntityClazz);
		sb.append(selectBuilder.linesFmt(insertBatchByStr, "\t", ""));
		
		String updateByStr = this.updateBuilder.buildSqlUpdate(rootEntityClazz, baserootEntityClazz);
		sb.append(selectBuilder.linesFmt(updateByStr, "\t", ""));
		
		String deleteByStr = this.deleteBuilder.buildSqlDelete(rootEntityClazz, baserootEntityClazz);
		sb.append(selectBuilder.linesFmt(deleteByStr, "\t", ""));
		
//		if(CommonPartialTreerootEntity.class.isAssignableFrom(pojoClazz)) {
//			CodeMapperSqlTreeGenerator sqlTreeGenerator = new CodeMapperSqlTreeGenerator();
//			String sqlTreeStr = sqlTreeGenerator.makeContent(pojoClazz);
//			sb.append(selectBuilder.linesFmt("<!-- 树结构相关操作 -->", "\t", ""));
//			sb.append(selectBuilder.linesFmt(sqlTreeStr, "\t", ""));
//		}
		sb.append("\r\n");
		sb.append("\t").append("<!-- ######################################################################### -->").append("\r\n");
		sb.append("\t").append("<!-- ##########################   base sql end    ########################## -->").append("\r\n");
		sb.append("\t").append("<!-- ######################################################################### -->").append("\r\n");
		sb.append("\r\n");
		sb.append("</mapper>");
		
		return sb.toString();
	}
	
}

