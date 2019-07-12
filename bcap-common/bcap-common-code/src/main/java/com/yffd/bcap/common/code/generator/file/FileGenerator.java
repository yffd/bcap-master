package com.yffd.bcap.common.code.generator.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yffd.bcap.common.support.util.StringUtils;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月26日 下午4:39:28 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class FileGenerator {
	public static final String DEF_rootEntity_SUFFIX = "rootEntity";
	private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	
	private List<String> skiprootEntityList = new ArrayList<String>();
	
	{
		skiprootEntityList.add("EasyPersistrootEntity");
		skiprootEntityList.add("BaserootEntity");
		skiprootEntityList.add("CommonrootEntity");
	}
	
	public List<String> getSkiprootEntityList() {
		return skiprootEntityList;
	}

	public String dateFmt(Date date) {
		return DATE_FMT.format(new Date());
	}
	
	public String rootEntityFmt(Class<?> rootEntityClazz, String rootEntityPrefix, String rootEntitySuffix,
			String fmtPrefix, String fmtSuffix) {
		String simpleName = rootEntityClazz.getSimpleName();
		int beginIndex = StringUtils.isBlank(rootEntityPrefix) ? 0 : simpleName.indexOf(rootEntityPrefix) + rootEntityPrefix.length();
		int endIndex = StringUtils.isBlank(rootEntitySuffix) ? simpleName.length() : simpleName.lastIndexOf(rootEntitySuffix);
		String tableName = simpleName.substring(beginIndex, endIndex);
		tableName = StringUtils.isBlank(fmtPrefix) ? tableName : fmtPrefix + tableName;
		tableName = StringUtils.isBlank(fmtSuffix) ? tableName : tableName + fmtSuffix;
		return tableName;
	}
	
	/**
	 * 创建多级目录
	 * @Date	2018年2月6日 上午10:34:03 <br/>
	 * @author  zhangST
	 * @param dirPath
	 */
	protected void makedirs(String dirPath) {
		File file = new File(dirPath);
		if(!file.exists()) {
			file.mkdirs();
		}
	}
	
	/**
	 * 
	 * @Date	2018年2月6日 下午2:27:51 <br/>
	 * @author  zhangST
	 * @param content
	 * @param outRootDirPath
	 * @param covered			是否覆盖旧文件
	 */
	protected void writeToFile(String content, String outRootDirPath, boolean covered) {
		try {
			if(new File(outRootDirPath).exists()) {
				if(covered) {
					System.out.println("覆盖文件：" + outRootDirPath + "， 原因：文件已存在！");
				} else {
					System.out.println("跳过文件：" + outRootDirPath + "， 原因：文件已存在！");
					return;
				}
			}
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outRootDirPath))));
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(content.getBytes("utf8"))));
			String line = null;
			while((line = reader.readLine()) != null) {
				writer.write(line + "\r\n");
			}
			writer.close();
			reader.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

