package com.yffd.bcap.common.code.generator.file;

import com.yffd.bcap.common.model.utils.BcapStringUtils;

import java.io.*;
import java.util.Date;

/**
 * @Description  XxxController代码生成器.
 * @Date		 2018年2月6日 下午1:55:18 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class ControllerFileGenerator extends FileGenerator {
	private static final String FILE_SUFFIX = ".java";
	private static final String TEMPLATE_PATH = "com/yffd/bcap/common/code/generator/file/template/XxxController.template";
	private ServiceFileGenerator serviceGenerator = new ServiceFileGenerator();
	
	// XxxUserentity > XxxUserController
	public String getControllerSimpleName(Class<?> entityClazz) {
		return this.entityFmt(entityClazz, null, "entity", null, "Controller");
	}
		
	public String getRequestMapping(Class<?> entityClazz) {
		String tmp = this.entityFmt(entityClazz, null, "entity", null, null);
		String underlineStr = BcapStringUtils.camel2underline(tmp, false, null, null);
		underlineStr = underlineStr.substring(underlineStr.indexOf("_")+1);//去掉前缀
		String ret = "/" + underlineStr.replace("_", "/");
		return ret;
	}
	
	public String getClassHeader(Class<?> entityClazz, Class<?> controllerSuperClazz) {
		StringBuffer sb = new StringBuffer();
		sb.append("public class").append(" ").append(this.getControllerSimpleName(entityClazz));
		if(null!=controllerSuperClazz) {
			sb.append(" ").append("extends ").append(controllerSuperClazz.getSimpleName());
//			sb.append("<").append(pojoClazz.getSimpleName()).append(">");
		}
		return sb.toString();
	}
	
	public void writeAllToFile(String entityRootDirPath, String entityPackageName, Class<?> controllerSuperClazz,
			String controllerPackageName, String servicePackageName, String author, String outRootDirPath, boolean covered) throws Exception {
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
						this.writeToFile(entityClazz, controllerSuperClazz, controllerPackageName, servicePackageName, author, outRootDirPath, covered);
					}
				}
			}
		}
	}
	
	public void writeToFile(Class<?> entityClazz, Class<?> controllerSuperClazz, String controllerPackageName, String servicePackageName, String author, String outRootDirPath, boolean covered) {
		String content = this.makeContent(entityClazz, controllerSuperClazz, controllerPackageName, servicePackageName, author);
		
		if(!outRootDirPath.endsWith(File.separator)) outRootDirPath += File.separator;
		String tmp = controllerPackageName.replace(".", File.separator);
		String packageFullDirPath = outRootDirPath + tmp;
		this.makedirs(packageFullDirPath);
		
		String fileName = packageFullDirPath + File.separator + this.getControllerSimpleName(entityClazz) + FILE_SUFFIX;
		System.out.println("文件位置：" + fileName);
		this.writeToFile(content, fileName, covered);
	}
	
	public void writeToConsole(Class<?> entityClazz, Class<?> controllerSuperClazz, String controllerPackageName, String servicePackageName, String author) {
		String content = this.makeContent(entityClazz, controllerSuperClazz, controllerPackageName, servicePackageName, author);
		System.out.println(content);
	}
	
	protected String makeContent(Class<?> entityClazz, Class<?> controllerSuperClazz, String controllerPackageName, String servicePackageName, String author) {
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
		content = content.replace("##PACKAGE_NAME##", controllerPackageName);
		content = content.replace("##POJO_FULL_NAME##", entityClazz.getName());
		String classNameLine = this.getClassHeader(entityClazz, controllerSuperClazz);
		content = content.replace("##CLASS_HEAD##", classNameLine);
		if(null==controllerSuperClazz) {
			content = content.replace("##IMPORT_SUPER_CLASS_FULL_NAME##", "");
		} else {
			content = content.replace("##IMPORT_SUPER_CLASS_FULL_NAME##", "\r\nimport " + controllerSuperClazz.getName() + ";");
		}
		
		content = content.replace("##POJO_SIMPLE_NAME##", entityClazz.getSimpleName());
		content = content.replace("##SERVICE_FULL_NAME##", serviceGenerator.getServiceFullName(servicePackageName, entityClazz));
		content = content.replace("##SERVICE_SIMPLE_NAME##", serviceGenerator.getServiceSimpleName(entityClazz));
		content = content.replace("##SERVICE_ALIAS_NAME##", serviceGenerator.getServiceAliasName(entityClazz));
		
		content = content.replace("##REQUEST_MAPPING##", this.getRequestMapping(entityClazz));
		return content;
	}
	
	
	
}

