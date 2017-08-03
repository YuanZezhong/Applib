package cn.sh.changxing.yuanyi.dao;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

/**
 * Created by YuanZezhong on 2017/8/1.
 */
public class DBOperationImpl implements IDBOperation {
    private DBHelper mDBHelper;
    private Context mContext;



    @Override
    public String getSqlString(int sqlStringId) {
        return null;
    }

    @Override
    public boolean insert(int sqlStringId, Object[] params) {
        return false;
    }

    @Override
    public boolean delete(int sqlStringId, Object[] params) {
        return false;
    }

    @Override
    public boolean update(int sqlStringId, Object[] params) {
        return false;
    }

    @Override
    public Object queryToObject(Class<?> objectClass, int sqlStringId, String[] params) {
        return null;
    }

    @Override
    public <T> List<T> queryToList(Class<T> objectClass, int sqlStringId, String[] params) {
        return null;
    }

    @Override
    public Cursor queryToCursor(int sqlStringId, String[] params) {
        return null;
    }
}
