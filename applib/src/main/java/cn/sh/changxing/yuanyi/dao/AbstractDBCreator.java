package cn.sh.changxing.yuanyi.dao;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by YuanZezhong on 2017/8/1.
 */
public abstract class AbstractDBCreator implements IDBCreator {
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onConfigure(SQLiteDatabase db) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {

    }

    @Override
    public SQLiteDatabase.CursorFactory getCursorFactory() {
        return null;
    }
}
