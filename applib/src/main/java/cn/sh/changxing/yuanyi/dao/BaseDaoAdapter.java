package cn.sh.changxing.yuanyi.dao;

import android.content.Context;

/**
 * Created by yuanyi on 17-8-5.
 */

public abstract class BaseDaoAdapter {
    protected IDBOperation mDBOperation;

    public BaseDaoAdapter(IDBOperation dbOperation) {
        if (dbOperation == null) {
            throw new DBException(DBException.CODE_OTHERS, "dbOperation cannot be null!");
        }
        mDBOperation = dbOperation;
    }

    public BaseDaoAdapter(Context context, IDBCreator creator) {
        this(DBOperationImpl.newInstance(context, creator));
    }

    public void open(boolean isWritable) {
        mDBOperation.open(isWritable);
    }

    public void close() {
        mDBOperation.close();
    }
}
