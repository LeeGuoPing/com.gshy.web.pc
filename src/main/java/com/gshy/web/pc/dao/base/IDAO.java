package com.gshy.web.pc.dao.base;

import java.util.List;

import com.darengong.tools.dao.v2.DBQuery;
import com.darengong.tools.dao.v2.KeyValueParis;

public interface IDAO<T> {

	public long add(T t) throws Exception;

	public void add(List<T> list) throws Exception;

	public int update(T t) throws Exception;

	public int update(KeyValueParis keyValueParis, long id) throws Exception;

	public int update(KeyValueParis keyValueParis, DBQuery query) throws Exception;

	public T getOne(long id) throws Exception;

	public T getOne(DBQuery query) throws Exception;

	public T getOne(DBQuery query, String orderBy) throws Exception;
	
	public List<T> getList(DBQuery query, int page, int size, String orderBy) throws Exception;

	public int countByQuery(DBQuery query) throws Exception;

	public int deleteById(long id) throws Exception;

	public int delete(DBQuery query) throws Exception;
}
