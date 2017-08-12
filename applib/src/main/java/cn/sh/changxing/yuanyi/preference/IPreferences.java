package cn.sh.changxing.yuanyi.preference;

import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * Created by YuanZezhong on 2017/8/10.
 * <p>
 * 定义Preferences的功能
 */
public interface IPreferences {
    /**
     * 添加一条记录
     *
     * @param key   记录的键
     * @param value 记录的值, 如果为null，不作处理
     * @return this
     */
    public <T> IPreferences put(String key, T value);

    /**
     * 添加map中的所有的记录
     *
     * @param value map
     * @return this
     */
    public IPreferences putAll(Map<String, ?> value);

    /**
     * 添加一条记录，值是Set<String>
     *
     * @param key   记录的键
     * @param value 记录的值
     * @return this
     */
    public IPreferences putStringSet(String key, Set<String> value);

    /**
     * 添加一条记录，值是String
     *
     * @param key   记录的键
     * @param value 记录的值
     * @return this
     */
    public IPreferences putString(String key, String value);

    /**
     * 添加一条记录，值是boolean
     *
     * @param key   记录的键
     * @param value 记录的值
     * @return this
     */
    public IPreferences putBoolean(String key, boolean value);

    /**
     * 添加一条记录，值是int
     *
     * @param key   记录的键
     * @param value 记录的值
     * @return this
     */
    public IPreferences putInt(String key, int value);

    /**
     * 添加一条记录，值是long
     *
     * @param key   记录的键
     * @param value 记录的值
     * @return this
     */
    public IPreferences putLong(String key, long value);

    /**
     * 添加一条记录，值是float
     *
     * @param key   记录的键
     * @param value 记录的值
     * @return this
     */
    public IPreferences putFloat(String key, float value);

    /**
     * 获取一条记录的值
     *
     * @param key      记录的键
     * @param defValue 默认值，如果是null，需要特殊处理
     * @return 记录的值
     */
    public <T> T get(String key, T defValue);

    /**
     * 获取一条记录的值
     *
     * @param key      记录的键
     * @param c        记录值的类型
     * @param defValue 默认值
     * @return 记录的值
     */
    public <T> T get(String key, Class<T> c, T defValue);

    /**
     * 获取所有的记录
     *
     * @return 所有的记录
     */
    public Map<String, ?> getAll();

    /**
     * 获取一条记录，值为Set<String>
     *
     * @param key      记录的键
     * @param defValue 默认值
     * @return 记录的值
     */
    public Set<String> getStringSet(String key, Set<String> defValue);

    /**
     * 获取一条记录，值为String
     *
     * @param key      记录的键
     * @param defValue 默认值
     * @return 记录的值
     */
    public String getString(String key, String defValue);

    /**
     * 获取一条记录，值为boolean
     *
     * @param key      记录的键
     * @param defValue 默认值
     * @return 记录的值
     */
    public boolean getBoolean(String key, boolean defValue);

    /**
     * 获取一条记录，值为int
     *
     * @param key      记录的键
     * @param defValue 默认值
     * @return 记录的值
     */
    public int getInt(String key, int defValue);

    /**
     * 获取一条记录，值为long
     *
     * @param key      记录的键
     * @param defValue 默认值
     * @return 记录的值
     */
    public long getLong(String key, long defValue);

    /**
     * 获取一条记录，值为float
     *
     * @param key      记录的键
     * @param defValue 默认值
     * @return 记录的值
     */
    public float getFloat(String key, float defValue);

    /**
     * 删除指定的记录
     *
     * @param keys 记录的键
     * @return this
     */
    public IPreferences remove(String... keys);

    /**
     * 是否包含指定记录
     *
     * @param key 记录的键
     * @return true:包含, false:不包含
     */
    public boolean contains(String key);

    /**
     * 清除所有的记录
     *
     * @return this
     */
    public IPreferences clear();

    /**
     * 获取内部的SharedPreferences
     */
    public SharedPreferences getSharedPreferences();
}
