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
	
	// XxxUserrootEntity > XxxUserController
	public String getControllerSimpleName(Class<?> rootEntityClazz) {
		return this.rootEntityFmt(rootEntityClazz, null, "rootEntity", null, "Controller");
	}
		
	public String getRequestMapping(Class<?> rootEntityClazz) {
		String tmp = this.rootEntityFmt(rootEntityClazz, null, "rootEntity", null, null);
		String underlineStr = BcapStringUtils.camel2underline(tmp, false, null, null);
		underlineStr = underlineStr.substring(underlineStr.indexOf("_")+1);//去掉前缀
		String ret = "/" + underlineStr.replace("_", "/");
		return ret;
	}
	
	public String getClassHeader(Class<?> rootEntityClazz, Class<?> controllerSuperClazz) {
		StringBuffer sb = new StringBuffer();
		sb.append("public class").append(" ").append(this.getControllerSimpleName(rootEntityClazz));
		if(null!=controllerSuperClazz) {
			sb.append(" ").append("extends ").append(controllerSuperClazz.getSimpleName());
//			sb.append("<").append(pojoClazz.getSimpleName()).append(">");
		}
		return sb.toString();
	}
	
	public void writeAllToFile(String rootEntityRootDirPath, String rootEntityPackageName, Class<?> controllerSuperClazz,
			String controllerPackageName, String servicePackageName, String author, String outRootDirPath, boolean covered) throws Exception {
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
						this.writeToFile(rootEntityClazz, controllerSuperClazz, controllerPackageName, servicePackageName, author, outRootDirPath, covered);
					}
				}
			}
		}
	}
	
	public void writeToFile(Class<?> rootEntityClazz, Class<?> controllerSuperClazz, String controllerPackageName, String servicePackageName, String author, String outRootDirPath, boolean covered) {
		String content = this.makeContent(rootEntityClazz, controllerSuperClazz, controllerPackageName, servicePackageName, author);
		
		if(!outRootDirPath.endsWith(File.separator)) outRootDirPath += File.separator;
		String tmp = controllerPackageName.replace(".", File.separator);
		String packageFullDirPath = outRootDirPath + tmp;
		this.makedirs(packageFullDirPath);
		
		String fileName = packageFullDirPath + File.separator + this.getControllerSimpleName(rootEntityClazz) + FILE_SUFFIX;
		System.out.println("文件位置：" + fileName);
		this.writeToFile(content, fileName, covered);
	}
	
	public void writeToConsole(Class<?> rootEntityClazz, Class<?> controllerSuperClazz, String controllerPackageName, String servicePackageName, String author) {
		String content = this.makeContent(rootEntityClazz, controllerSuperClazz, controllerPackageName, servicePackageName, author);
		System.out.println(content);
	}
	
	protected String makeContent(Class<?> rootEntityClazz, Class<?> controllerSuperClazz, String controllerPackageName, String servicePackageName, String author) {
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
		content = content.replace("##POJO_FULL_NAME##", rootEntityClazz.getName());
		String classNameLine = this.getClassHeader(rootEntityClazz, controllerSuperClazz);
		content = content.replace("##CLASS_HEAD##", classNameLine);
		if(null==controllerSuperClazz) {
			content = content.replace("##IMPORT_SUPER_CLASS_FULL_NAME##", "");
		} else {
			content = content.replace("##IMPORT_SUPER_CLASS_FULL_NAME##", "\r\nimport " + controllerSuperClazz.getName() + ";");
		}
		
		content = content.replace("##POJO_SIMPLE_NAME##", rootEntityClazz.getSimpleName());
		content = content.replace("##SERVICE_FULL_NAME##", serviceGenerator.getServiceFullName(servicePackageName, rootEntityClazz));
		content = content.replace("##SERVICE_SIMPLE_NAME##", serviceGenerator.getServiceSimpleName(rootEntityClazz));
		content = content.replace("##SERVICE_ALIAS_NAME##", serviceGenerator.getServiceAliasName(rootEntityClazz));
		
		content = content.replace("##REQUEST_MAPPING##", this.getRequestMapping(rootEntityClazz));
		return content;
	}
	
	
	
}

