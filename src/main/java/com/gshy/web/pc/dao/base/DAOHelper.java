package com.gshy.web.pc.dao.base;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.darengong.tools.dao.v2.DAOHelperV2;
import com.gshy.web.pc.utils.PathUtil;

/**
 * 功能：创建DAOHelperV2
 * 作者：王鑫
 * 日期：2017年9月1日
 */
public abstract class DAOHelper {

	private static Logger log = LoggerFactory.getLogger(DAOHelper.class);
	
	public static DAOHelperV2 daoHelper = null;

	static {
		initDAOHelper(PathUtil.getConfigFolderPath());
	}

	private static void initDAOHelper(String rootPath) {
		log.info("config path:" + rootPath);
		String carPath = rootPath + "/car.properties";
		System.out.println("财务DAO PATH:"+carPath);
		try {
			File carDB = new File(carPath);
			log.info("1config path:" + carPath);
			if (carDB.exists()) {
				log.info("3config path:" + carPath);
				daoHelper = DAOHelperV2.createIntrance(carPath);
			}
			log.info("2config path:" + daoHelper);
		} catch (Exception e) {
			log.error("create auctionDao error(" + carPath, e);
		}
	}
}
