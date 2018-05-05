
package com.gshy.web.pc.utils;

import java.io.File;


public class PathUtil {
	private static String configFoldPath = null;
	public static final String CONFING_PATH = "SYSTEM_CONFIG_PATH";

	static {
		configFoldPath = System.getProperty(CONFING_PATH);
		if (configFoldPath == null) {
			configFoldPath = System.getProperty("user.dir") + "/conf/";
			File file = new File(configFoldPath);
			if (!file.exists()) {
				configFoldPath = getCurrentPath() + "/../conf/";
			}
		}
	}

	public static String getConfigFolderPath() {
		return configFoldPath;
	}
	
	/**
	 * 获取当前jar包所在目录 / 程序bin所在目录
	 * @return
	 */
	public static String getCurrentPath() {
		String path = System.getProperty("serviceframe.config.path");
		System.out.println("serviceframe.config.path:" + path);

		if (path == null || path.equalsIgnoreCase("")) {
			Class<?> caller = getCaller();
			if (caller == null) {
				caller = PathUtil.class;
			}
			path = getCurrentPath(caller);
		}

		System.out.println("utility path getCurrentPath:" + path);
		return path;
	}
	
	public static Class<?> getCaller() {
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		System.out.println("stack length:"+stack.length);
		if(stack.length < 3) {
			return PathUtil.class;
		}
		String className = stack[2].getClassName();
		System.out.println("getCaller class name:" + className);
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取当前class父目录
	 * @param cls
	 * @return 当前class父目录 URL
	 */
	public static String getCurrentPath(Class<?> cls) {
		String path = cls.getProtectionDomain().getCodeSource().getLocation().getPath();
		path = path.replaceFirst("file:/", "");
		path = path.replaceAll("!/", "");
		if(path.lastIndexOf(File.separator) >= 0){
			path = path.substring(0, path.lastIndexOf(File.separator));
		}
		if(path.substring(0,1).equalsIgnoreCase("/")){
			String osName = System.getProperty("os.name").toLowerCase();
			if(osName.indexOf("window") >= 0){
				path = path.substring(1);
			}
		}
		return path;
	}
}
