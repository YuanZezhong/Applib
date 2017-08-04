package cn.sh.changxing.yuanyi.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by YuanZezhong on 2017/8/1.
 * <p>
 * 数据库操作功能的基本实现类
 */
public class DBOperationImpl implements IDBOperation {
    private SimpleDateFormat mSDF = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
    private SQLiteOpenHelper mDBHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    /**
     * 获取一个数据库基本操作类
     *
     * @param context context
     * @param creator 用于创建DBHelper
     * @return 数据库基本操作类
     */
    public static DBOperationImpl newInstance(Context context, IDBCreator creator) {
        if (context == null || creator == null) {
            throw new IllegalArgumentException("context or creator is null");
        }
        try {
            DBHelper dbHelper = new DBHelper(context.getApplicationContext(), creator);
            return new DBOperationImpl(context, dbHelper);
        } catch (Exception e) {
            throw new DBException(DBException.CODE_OTHERS, "create DBHelper Exception!", e);
        }
    }

    /**
     * 获取一个数据库基本操作类
     *
     * @param context  context
     * @param dbHelper SQLiteOpenHelper
     * @return 数据库基本操作类
     */
    public static DBOperationImpl newInstance(Context context, SQLiteOpenHelper dbHelper) {
        if (context == null || dbHelper == null) {
            throw new IllegalArgumentException("context or dbHelper is null");
        }
        return new DBOperationImpl(context, dbHelper);
    }

    DBOperationImpl(Context context, SQLiteOpenHelper dbHelper) {
        mContext = context.getApplicationContext();
        mDBHelper = dbHelper;
    }

    @Override
    public String getSqlString(int sqlStringId) {
        try {
            return mContext.getResources().getString(sqlStringId);
        } catch (Exception e) {
            throw new DBException(DBException.CODE_OTHERS, "SQL String cannot be found by specified sqlStringId: " + sqlStringId);
        }
    }

    @Override
    public boolean insert(int sqlStringId, Object[] params) {
        checkDatabaseOpen();
        return baseCRUDOperation(sqlStringId, params, mDatabase, DBException.CODE_INSERT, false);
    }

    @Override
    public boolean delete(int sqlStringId, Object[] params) {
        checkDatabaseOpen();
        return baseCRUDOperation(sqlStringId, params, mDatabase, DBException.CODE_DELETE, false);
    }

    @Override
    public boolean update(int sqlStringId, Object[] params) {
        checkDatabaseOpen();
        return baseCRUDOperation(sqlStringId, params, mDatabase, DBException.CODE_UPDATE, false);
    }

    @Override
    public <T> T queryToObject(Class<T> objectClass, int sqlStringId, String[] params) {
        checkDatabaseOpen();
        List<T> list = queryToList(objectClass, sqlStringId, params, mDatabase, true);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public <T> List<T> queryToList(Class<T> objectClass, int sqlStringId, String[] params) {
        checkDatabaseOpen();
        return queryToList(objectClass, sqlStringId, params, mDatabase, false);
    }

    @Override
    public Cursor queryToCursor(int sqlStringId, String[] params) {
        checkDatabaseOpen();
        return mDatabase.rawQuery(getSqlString(sqlStringId), params);
    }

    /**
     * 根据条件SQL查询
     *
     * @param sqlStringId SQL语句的资源ID
     * @param params      参数
     * @param objectClass 返回结果类型
     * @param db          SQLiteDatabase
     * @param isOneRow    返回结构是否单行
     * @return 查询List结果
     */
    private <T> List<T> queryToList(Class<T> objectClass, int sqlStringId, String[] params,
                                    SQLiteDatabase db, boolean isOneRow) {
        Cursor cursor = db.rawQuery(getSqlString(sqlStringId), params);
        if (cursor != null && cursor.getCount() > 0) {
            boolean isSimpleData = isSimpleDataType(objectClass);
            String[] columnNames = null;
            Map<String, Object[]> classMap = null;
            if (!isSimpleData) {
                columnNames = cursor.getColumnNames();
                classMap = getClassMap(objectClass);
            }

            List<T> resultList = new ArrayList<T>();
            while (cursor.moveToNext()) {
                Object obj;
                if (isSimpleData) {
                    // 基本类型
                    obj = getSimpleDataTypeValue(cursor, objectClass);
                } else {
                    try {
                        obj = objectClass.newInstance();
                    } catch (InstantiationException e) {
                        cursor.close();
                        throw new DBException(
                                DBException.CODE_SELECT, e);
                    } catch (IllegalAccessException e) {
                        cursor.close();
                        throw new DBException(DBException.CODE_SELECT, e);
                    }
                    this.setObjectFromCursor(obj, cursor, columnNames, classMap);
                }

                resultList.add((T) obj);
                if (isOneRow) {
                    break;
                }
            }
            cursor.close();
            return resultList;
        }
        return null;
    }

    /**
     * 是否是简单类型（指数值、字符串的类型）
     *
     * @param objectClass 数据类型
     * @return true：是简单类型； false：其他类型
     */
    private boolean isSimpleDataType(Class<?> objectClass) {
        if (objectClass.isAssignableFrom(Integer.class) || objectClass.isAssignableFrom(Long.class)
                || objectClass.isAssignableFrom(Double.class)
                || objectClass.isAssignableFrom(Float.class)
                || objectClass.isAssignableFrom(String.class)) {
            return true;
        }
        return false;
    }

    /**
     * 简单类型的情况下，取得相应类型的值
     *
     * @param cur         Cursor
     * @param objectClass 数据类型
     * @return 返回值
     */
    private Object getSimpleDataTypeValue(Cursor cur, Class<?> objectClass) {
        Object obj = null;
        if (objectClass.isAssignableFrom(Integer.class)) {
            obj = cur.getInt(0);
        } else if (objectClass.isAssignableFrom(Long.class)) {
            obj = cur.getLong(0);
        } else if (objectClass.isAssignableFrom(Double.class)) {
            obj = cur.getDouble(0);
        } else if (objectClass.isAssignableFrom(Float.class)) {
            obj = cur.getFloat(0);
        } else if (objectClass.isAssignableFrom(String.class)) {
            obj = cur.getString(0);
        }
        return obj;
    }

    /**
     * 从Cursor结果，设定对象的属性值
     *
     * @param obj            设定结果对象
     * @param cursor         Cursor
     * @param columnNames    列名
     * @param fieldMethodMap 列名对应调用的反射方法
     */
    private void setObjectFromCursor(Object obj, Cursor cursor,
                                     String[] columnNames, Map<String, Object[]> fieldMethodMap) {
        if (fieldMethodMap == null || fieldMethodMap.size() == 0) {
            return;
        }
        for (String name : columnNames) {
            Object[] fm = fieldMethodMap.get(name);
            if (fm == null) {
                continue;
            }

            Method method = (Method) fm[1];
            Object value = getCursorValue((Field) fm[0], cursor);
            try {
                // field.set(obj, value);
                method.invoke(obj, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private Map<String, Object[]> getClassMap(Class<?> objectClass) {
        Map<String, Object[]> methodMap = new HashMap<String, Object[]>();
        Field[] fields = objectClass.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                String fieldName = field.getName();
                try {
                    Method method = objectClass.getMethod(getSetMethodName(fieldName),
                            field.getType());
                    methodMap.put(fieldName, new Object[]{field, method});
                } catch (NoSuchMethodException e) {
                    continue;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return methodMap;
    }

    /**
     * 取得字段名对应的Set方法名
     *
     * @param fieldName 字段名称
     * @return
     */
    private String getSetMethodName(String fieldName) {
        return "set"
                + fieldName.toUpperCase(Locale.getDefault()).substring(0, 1)
                + fieldName.substring(1);
    }

    /**
     * 根据字段类型取得相应值
     *
     * @param f      Field
     * @param cursor Cursor
     * @return
     */
    private Object getCursorValue(Field f, Cursor cursor) {
        Class<?> c = f.getType();
        Object obj = null;
        int index = cursor.getColumnIndex(f.getName());
        if (c.isAssignableFrom(Integer.class)
                || "int".equals(c.getSimpleName())) {
            obj = cursor.getInt(index);
        } else if (c.isAssignableFrom(Long.class)
                || "long".equals(c.getSimpleName())) {
            obj = cursor.getLong(index);
        } else if (c.isAssignableFrom(Double.class)
                || "double".equals(c.getSimpleName())) {
            obj = cursor.getDouble(index);
        } else if (c.isAssignableFrom(Float.class)
                || "float".equals(c.getSimpleName())) {
            obj = cursor.getFloat(index);
        } else if (c.isAssignableFrom(Date.class)) {
            try {
                String d = cursor.getString(index);
                obj = mSDF.parse(d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            obj = cursor.getString(index);
        }
        return obj;
    }

    /**
     * 执行SQL语句
     *
     * @param sqlString SQL语句
     * @param params    替换SQL语句中占位符的参数
     * @param db        database
     */
    private void baseCRUDOperation(String sqlString, Object[] params, SQLiteDatabase db) {
        if (params != null && params.length > 0) {
            db.execSQL(sqlString, params);
        } else {
            db.execSQL(sqlString);
        }
    }

    /**
     * 执行SQL语句
     *
     * @param sqlStringId     SQL语句的资源id
     * @param params          替换SQL语句中占位符的参数
     * @param db              database
     * @param errorCode       出现异常时的错误码
     * @param needTransaction 是否需要开启事务
     * @return 是否执行成功
     */
    private boolean baseCRUDOperation(int sqlStringId, Object[] params, SQLiteDatabase db, String errorCode, boolean needTransaction) {
        boolean result = true;
        try {
            if (needTransaction) {
                db.beginTransaction();
            }
            baseCRUDOperation(getSqlString(sqlStringId), params, db);
            if (needTransaction) {
                db.setTransactionSuccessful();
            }
        } catch (Exception e) {
            result = false;
            throw new DBException(errorCode, e);
        } finally {
            if (needTransaction) {
                db.endTransaction();
            }
            return result;
        }
    }

    /**
     * 检查数据库是否已经打开
     */
    private void checkDatabaseOpen() {
        if (mDatabase == null || !mDatabase.isOpen()) {
            throw new DBException(DBException.CODE_OTHERS, "database not open");
        }
    }

    @Override
    public IDBOperation open(boolean isWritable) {
        if (mDatabase == null || !mDatabase.isOpen()) {
            try {
                if (isWritable) {
                    mDatabase = mDBHelper.getWritableDatabase();
                } else {
                    mDatabase = mDBHelper.getReadableDatabase();
                }
            } catch (Exception e) {
                throw new DBException(DBException.CODE_OTHERS, "the database cannot be opened");
            }
        }
        return this;
    }

    @Override
    public void close() {
        mDBHelper.close();
        mDatabase = null;
    }

    @Override
    public SQLiteDatabase getDatabase() {
        checkDatabaseOpen();
        return mDatabase;
    }
}
