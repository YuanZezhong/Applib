package cn.sh.changxing.yuanyi.dao;

import android.database.Cursor;

import java.util.List;

/**
 * Created by YuanZezhong on 2017/7/31.
 *
 * 定义基本的数据库操作
 */
public interface IDBOperation {
    public String getSqlString(int sqlStringId);

    public boolean insert(int sqlStringId, Object[] params);

    public boolean delete(int sqlStringId, Object[] params);

    public boolean update(int sqlStringId, Object[] params);

    public Object queryToObject(Class<?> objectClass, int sqlStringId, String[] params);

    public <T> List<T> queryToList(Class<T> objectClass, int sqlStringId, String[] params);

    public Cursor queryToCursor(int sqlStringId, String[] params);
}
