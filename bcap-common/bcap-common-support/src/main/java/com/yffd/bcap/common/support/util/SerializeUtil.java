package com.yffd.bcap.common.support.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SerializeUtil {
	private static final Logger LOG = LoggerFactory.getLogger(SerializeUtil.class);

	// 私有构造方法，将该工具类设为单例模式。
    private SerializeUtil() {}
    
	public static byte[] serialize(Object value) {
		if(null==value) throw new NullPointerException("Can't serialize null");
		byte[] result = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream os = null;
		try {
			os = new ObjectOutputStream(bos);
			os.writeObject(value);
			os.close();
            bos.close();
            result = bos.toByteArray();
		} catch (IOException e) {
			LOG.error(String.format("serialize error【value:%s】", value), e);
		} finally {
			close(os);
			close(bos);
		}
		return result;
	}
	
	public static Object deserialize(byte[] bytes) {
		return deserialize(bytes, Object.class);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(byte[] bytes, Class<T> clazz) {
		if(null==bytes) throw new NullPointerException("Can't deserialize null");
		Object result = null;
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(bis);
			result = ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			LOG.error(String.format("deserialize error【bytes:%s】", bytes), e);
		}
		return (T) result;
	}
	
	private static void close(Closeable closeable) {
        if(closeable!=null) {
        	try {
                closeable.close();
            } catch (IOException e) {
            	LOG.error("close stream error");
            }
        }
    }
}

