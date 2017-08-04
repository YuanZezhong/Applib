package cn.sh.changxing.yuanyi.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by YuanZezhong on 2017/7/31.
 * <p>
 * 定义数据库基本的操作
 */
public interface IDBOperation {
    /**
     * 根据给定的资源id获取SQL语句
     *
     * @param sqlStringId SQL语句的资源id
     * @return SQL语句
     */
    public String getSqlString(int sqlStringId);

    /**
     * 向数据库中插入一条记录
     *
     * @param sqlStringId SQL语句的资源id
     * @param params      替换SQL语句中占位符的参数
     * @return 执行是否成功
     */
    public boolean insert(int sqlStringId, Object[] params);

    /**
     * 从数据库中删除一条记录
     *
     * @param sqlStringId SQL语句的资源id
     * @param params      替换SQL语句中占位符的参数
     * @return 执行是否成功
     */
    public boolean delete(int sqlStringId, Object[] params);

    /**
     * 更新数据库中记录
     *
     * @param sqlStringId SQL语句的资源id
     * @param params      替换SQL语句中占位符的参数
     * @return 执行是否成功
     */
    public boolean update(int sqlStringId, Object[] params);

    /**
     * 从新数据库中获取记录并转换成对象
     *
     * @param objectClass 转换对象的Class
     * @param sqlStringId SQL语句的资源id
     * @param params      替换SQL语句中占位符的参数
     * @return 查询到的记录转换成的对象
     */
    public <T> T queryToObject(Class<T> objectClass, int sqlStringId, String[] params);

    /**
     * 从新数据库中获取记录并转换成集合
     *
     * @param objectClass 转换集合元素的Class
     * @param sqlStringId SQL语句的资源id
     * @param params      替换SQL语句中占位符的参数
     * @return 查询到的记录转换成的集合
     */
    public <T> List<T> queryToList(Class<T> objectClass, int sqlStringId, String[] params);

    /**
     * 从新数据库中获取记录并转换成Cursor
     *
     * @param sqlStringId SQL语句的资源id
     * @param params      替换SQL语句中占位符的参数
     * @return 查询到的记录转换成的Cursor
     */
    public Cursor queryToCursor(int sqlStringId, String[] params);

    /**
     * 打开数据库
     *
     * @param isWritable 决定内部调用getWritableDatabase()或getReadableDatabase()
     * @return 应该返回this
     */
    public IDBOperation open(boolean isWritable);

    /**
     * 关闭数据库
     */
    public void close();

    /**
     * 获取SQLiteDatabase
     *
     * @return SQLiteDataBase
     */
    public SQLiteDatabase getDatabase();
}
