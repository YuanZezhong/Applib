package cn.sh.changxing.yuanyi.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import cn.sh.changxing.yuanyi.R;
import cn.sh.changxing.yuanyi.logger.LoggerFactory;

/**
 * Created by yuanyi on 17-8-6.
 */

public class SongCreator extends AbstractDBCreator {

    public static final String DB_NAME = "songs";
    public static final int DB_VERSION = 1;

    private Context mContext;

    public SongCreator(Context context) {
        mContext = context;
    }

    @Override
    public String getDatabaseName() {
        return DB_NAME;
    }

    @Override
    public int getDatabaseVersion() {
        return DB_VERSION;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        LoggerFactory.getDefault().beginMethod();
        String createString = mContext.getString(R.string.sql_download_list_create);
        db.execSQL(createString);
        LoggerFactory.getDefault().endMethod();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LoggerFactory.getDefault().beginMethod();

        // ...

        LoggerFactory.getDefault().endMethod();
    }
}
