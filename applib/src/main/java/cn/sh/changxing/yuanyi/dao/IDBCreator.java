package cn.sh.changxing.yuanyi.dao;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by YuanZezhong on 2017/7/31.
 * <p>
 * 创建和更新数据库时
 */
public interface IDBCreator {
    /**
     * 创建的数据库的名称
     *
     * @return 数据库名称
     */
    public String getDatabaseName();

    /**
     * 创建的数据库的版本号
     *
     * @return 数据库版本号
     */
    public int getDatabaseVersion();

    /**
     * 创建的数据库的CursorFactory，一般传null即可
     *
     * @return CursorFactory
     */
    public SQLiteDatabase.CursorFactory getCursorFactory();

    /**
     * 在onCreate(), onUpgrade(), onOpen()之前调用
     * 进行数据库的配置操作
     *
     * @param db database
     */
    public void onConfigure(SQLiteDatabase db);

    /**
     * 数据库创建时调用
     *
     * @param db database
     */
    public void onCreate(SQLiteDatabase db);

    /**
     * 数据库升级时调用
     *
     * @param db         database
     * @param oldVersion 旧版本号
     * @param newVersion 新版本号
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    /**
     * 在onCreate(), onUpgrade()之后调用
     * 数据库打开后调用
     *
     * @param db database
     */
    public void onOpen(SQLiteDatabase db);

    /**
     * 数据库降级时调用
     *
     * @param db         database
     * @param oldVersion 旧版本号
     * @param newVersion 新版本号
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion);
}
