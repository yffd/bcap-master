package com.yffd.bcap.common.code.generator.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年5月9日 下午4:16:18 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class DaoFileGenerator extends FileGenerator {
	private static final String FILE_SUFFIX = ".java";
	private static final String TEMPLATE_PATH = "com/yffd/bcap/common/code/generator/file/template/XxxDao.template";
	
	// XxxUserentity > XxxUserDao
	public String getDaoSimpleName(Class<?> entityClazz) {
		return this.entityFmt(entityClazz, null, "entity", null, "Dao");
	}
	
	public String getDaoFullName(String daoPackageName, Class<?> entityClazz) {
		return daoPackageName + "." + this.getDaoSimpleName(entityClazz);
	}
	
	public String getDaoAliasName(Class<?> entityClazz) {
		String simpleName = this.getDaoSimpleName(entityClazz);
		return simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
	}
	
	public String getClassHeader(Class<?> entityClazz, Class<?> daoSuperClazz) {
		StringBuffer sb = new StringBuffer();
		sb.append("public class").append(" ").append(this.getDaoSimpleName(entityClazz));
		if(null!=daoSuperClazz) {
			sb.append(" ").append("extends ").append(daoSuperClazz.getSimpleName());
			sb.append("<").append(entityClazz.getSimpleName()).append(">");
		}
		return sb.toString();
	}
	
	public void writeAllToFile(String entityRootDirPath, String entityPackageName, Class<?> daoSuperClazz,
			String daoPackageName, String author, String outRootDirPath, boolean covered) throws Exception {
		if(!entityRootDirPath.endsWith(File.separator)) entityRootDirPath += File.separator;
		String tmp = entityPackageName.replace(".", File.separator);
		String entityPackageFullDirPath = entityRootDirPath + tmp;
		
		File file = new File(entityPackageFullDirPath);
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File childFile : files) {
				if(childFile.isDirectory()) {
					continue;
				} else {
					boolean skip = false;
					String entityFileName = childFile.getName();	// 不包含路径
					for(String skipName : this.getSkipentityList()) {
						if(entityFileName.contains(skipName)) {
							skip = true;
							continue;
						}
					}
					if(!skip) {
						String entityFullClassName = entityPackageName + "." + entityFileName.substring(0, entityFileName.lastIndexOf(FILE_SUFFIX));
						Class<?> entityClazz = Class.forName(entityFullClassName);
						this.writeToFile(entityClazz, daoSuperClazz, daoPackageName, author, outRootDirPath, covered);
					}
				}
			}
		}
	}
	
	public void writeToFile(Class<?> entityClazz, Class<?> daoSuperClazz, String daoPackageName, String author, String outRootDirPath, boolean covered) {
		String content = this.makeContent(entityClazz, daoSuperClazz, daoPackageName, author);
		
		if(!outRootDirPath.endsWith(File.separator)) outRootDirPath += File.separator;
		String tmp = daoPackageName.replace(".", File.separator);
		String packageFullDirPath = outRootDirPath + tmp;
		this.makedirs(packageFullDirPath);
		
		String fileName = packageFullDirPath + File.separator + this.getDaoSimpleName(entityClazz) + FILE_SUFFIX;
		System.out.println("文件位置：" + fileName);
		this.writeToFile(content, fileName, covered);
	}
	
	public void writeToConsole(Class<?> entityClazz, Class<?> daoSuperClazz, String daoPackageName, String author) {
		String content = this.makeContent(entityClazz, daoSuperClazz, daoPackageName, author);
		System.out.println(content);
	}
	
	protected String makeContent(Class<?> entityClazz, Class<?> daoSuperClazz, String daoPackageName, String author) {
		StringBuilder sb = new StringBuilder();
		try {
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(TEMPLATE_PATH);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
			String line = null;
			while((line=reader.readLine())!=null) {
				sb.append(line).append("\r\n");
			}
			reader.close();
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String content = sb.toString();
		
		content = content.replace("##DATE##", this.dateFmt(new Date()));
		content = content.replace("##AUTHOR##", author);
		content = content.replace("##PACKAGE_NAME##", daoPackageName);
		content = content.replace("##POJO_FULL_NAME##", entityClazz.getName());
		String classNameLine = this.getClassHeader(entityClazz, daoSuperClazz);
		content = content.replace("##CLASS_HEAD##", classNameLine);
		if(null==daoSuperClazz) {
			content = content.replace("##IMPORT_SUPER_CLASS_FULL_NAME##", "");
		} else {
			content = content.replace("##IMPORT_SUPER_CLASS_FULL_NAME##", "\r\nimport " + daoSuperClazz.getName() + ";");
		}
		return content;
	}
	
}

