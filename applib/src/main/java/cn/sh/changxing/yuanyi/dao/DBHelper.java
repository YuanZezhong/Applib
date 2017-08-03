package cn.sh.changxing.yuanyi.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by YuanZezhong on 2017/8/1.
 */
public class DBHelper extends SQLiteOpenHelper {
    private IDBCreator mCreator;

    public static DBHelper obtainDBHelper(Context context, IDBCreator creator) {
        if (context == null || creator == null) {
            throw new IllegalArgumentException("context or creator is null");
        }
        return new DBHelper(context, creator);
    }

    DBHelper(Context context, IDBCreator creator) {
        super(context, creator.getDatabaseName(), creator.getCursorFactory(), creator.getDatabaseVersion());
        mCreator = creator;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        mCreator.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        mCreator.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mCreator.onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        mCreator.onOpen(db);
    }
}
