package cn.sh.changxing.yuanyi.preference;

import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * Created by YuanZezhong on 2017/8/10.
 */
public abstract class BasePreferences implements IPreferences {
    protected SharedPreferences mPreferences;

    public BasePreferences(SharedPreferences preferences) {
        mPreferences = preferences;
    }

    @Override
    public IPreferences putStringSet(String key, Set<String> value) {
        mPreferences.edit().putStringSet(key, value).apply();
        return this;
    }

    @Override
    public IPreferences putString(String key, String value) {
        mPreferences.edit().putString(key, value).apply();
        return this;
    }

    @Override
    public IPreferences putBoolean(String key, boolean value) {
        mPreferences.edit().putBoolean(key, value).apply();
        return this;
    }

    @Override
    public IPreferences putInt(String key, int value) {
        mPreferences.edit().putInt(key, value).apply();
        return this;
    }

    @Override
    public IPreferences putLong(String key, long value) {
        mPreferences.edit().putLong(key, value).apply();
        return this;
    }

    @Override
    public IPreferences putFloat(String key, float value) {
        mPreferences.edit().putFloat(key, value).apply();
        return this;
    }

    @Override
    public Map<String, ?> getAll() {
        return mPreferences.getAll();
    }

    @Override
    public Set<String> getStringSet(String key, Set<String> defValue) {
        return mPreferences.getStringSet(key, defValue);
    }

    @Override
    public String getString(String key, String defValue) {
        return mPreferences.getString(key, defValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return mPreferences.getBoolean(key, defValue);
    }

    @Override
    public int getInt(String key, int defValue) {
        return mPreferences.getInt(key, defValue);
    }

    @Override
    public long getLong(String key, long defValue) {
        return mPreferences.getLong(key, defValue);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return mPreferences.getFloat(key, defValue);
    }

    @Override
    public IPreferences remove(String... keys) {
        if (keys != null && keys.length > 0) {
            SharedPreferences.Editor editor = mPreferences.edit();
            for (String key : keys) {
                editor.remove(key);
            }
            editor.apply();
        }
        return this;
    }

    @Override
    public boolean contains(String key) {
        return mPreferences.contains(key);
    }

    @Override
    public IPreferences clear() {
        mPreferences.edit().clear().apply();
        return this;
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return mPreferences;
    }
}
