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
public class ServiceFileGenerator extends FileGenerator {
	private static final String FILE_SUFFIX = ".java";
	private static final String TEMPLATE_PATH = "com/yffd/bcap/common/code/generator/file/template/XxxService.template";
	private DaoFileGenerator daoGenerator = new DaoFileGenerator();
	
	// XxxUserrootEntity > XxxUserService
	public String getServiceSimpleName(Class<?> rootEntityClazz) {
		return this.rootEntityFmt(rootEntityClazz, null, "rootEntity", null, "Service");
	}
	
	public String getServiceFullName(String servicePackageName, Class<?> rootEntityClazz) {
		return servicePackageName + "." + this.getServiceSimpleName(rootEntityClazz);
	}
	
	public String getServiceAliasName(Class<?> rootEntityClazz) {
		String simpleName = this.getServiceSimpleName(rootEntityClazz);
		return simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
	}
		
	public String getClassHeader(Class<?> rootEntityClazz, Class<?> serviceSuperClazz) {
		StringBuffer sb = new StringBuffer();
		sb.append("public class").append(" ").append(getServiceSimpleName(rootEntityClazz));
		if(null!=serviceSuperClazz) {
			sb.append(" ").append("extends ").append(serviceSuperClazz.getSimpleName());
			sb.append("<").append(rootEntityClazz.getSimpleName()).append(">");
		}
		return sb.toString();
	}
	
	public void writeAllToFile(String rootEntityRootDirPath, String rootEntityPackageName, Class<?> serviceSuperClazz,
			String servicePackageName, String daoPackageName, String author, String outRootDirPath, boolean covered) throws Exception {
		if(!rootEntityRootDirPath.endsWith(File.separator)) rootEntityRootDirPath += File.separator;
		String tmp = rootEntityPackageName.replace(".", File.separator);
		String rootEntityPackageFullDirPath = rootEntityRootDirPath + tmp;
		
		File file = new File(rootEntityPackageFullDirPath);
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File childFile : files) {
				if(childFile.isDirectory()) {
					continue;
				} else {
					boolean skip = false;
					String rootEntityFileName = childFile.getName();	// 不包含路径
					for(String skipName : this.getSkiprootEntityList()) {
						if(rootEntityFileName.contains(skipName)) {
							skip = true;
							continue;
						}
					}
					if(!skip) {
						String rootEntityFullClassName = rootEntityPackageName + "." + rootEntityFileName.substring(0, rootEntityFileName.lastIndexOf(FILE_SUFFIX));
						Class<?> rootEntityClazz = Class.forName(rootEntityFullClassName);
						this.writeToFile(rootEntityClazz, serviceSuperClazz, servicePackageName, daoPackageName, author, outRootDirPath, covered);
					}
				}
			}
		}
	}
	
	public void writeToFile(Class<?> rootEntityClazz, Class<?> serviceSuperClazz, String servicePackageName, String daoPackageName, String author, String outRootDirPath, boolean covered) {
		String content = this.makeContent(rootEntityClazz, serviceSuperClazz, servicePackageName, daoPackageName, author);
		
		if(!outRootDirPath.endsWith(File.separator)) outRootDirPath += File.separator;
		String tmp = servicePackageName.replace(".", File.separator);
		String packageFullDirPath = outRootDirPath + tmp;
		this.makedirs(packageFullDirPath);
		
		String fileName = packageFullDirPath + File.separator + this.getServiceSimpleName(rootEntityClazz) + FILE_SUFFIX;
		System.out.println("文件位置：" + fileName);
		this.writeToFile(content, fileName, covered);
	}
	
	public void writeToConsole(Class<?> rootEntityClazz, Class<?> serviceSuperClazz, String servicePackageName, String daoPackageName, String author) {
		String content = this.makeContent(rootEntityClazz, serviceSuperClazz, servicePackageName, daoPackageName, author);
		System.out.println(content);
	}
	
	protected String makeContent(Class<?> rootEntityClazz, Class<?> serviceSuperClazz, String packageName, String daoPackageName, String author) {
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
		content = content.replace("##PACKAGE_NAME##", packageName);
		content = content.replace("##POJO_FULL_NAME##", rootEntityClazz.getName());
		String classNameLine = this.getClassHeader(rootEntityClazz, serviceSuperClazz);
		content = content.replace("##CLASS_HEAD##", classNameLine);
		if(null==serviceSuperClazz) {
			content = content.replace("##IMPORT_SUPER_CLASS_FULL_NAME##", "");
		} else {
			content = content.replace("##IMPORT_SUPER_CLASS_FULL_NAME##", "\r\nimport " + serviceSuperClazz.getName() + ";");
		}
		
		content = content.replace("##POJO_SIMPLE_NAME##", rootEntityClazz.getSimpleName());
		content = content.replace("##DAO_FULL_NAME##", daoGenerator.getDaoFullName(daoPackageName, rootEntityClazz));
		content = content.replace("##DAO_SIMPLE_NAME##", daoGenerator.getDaoSimpleName(rootEntityClazz));
		content = content.replace("##DAO_ALIAS_NAME##", daoGenerator.getDaoAliasName(rootEntityClazz));
		return content;
	}
	
}

