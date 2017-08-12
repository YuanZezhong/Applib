package cn.sh.changxing.yuanyi.preference;

import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * Created by YuanZezhong on 2017/8/10.
 *
 * IPreferences简单实现类
 */
public class PreferencesImpl extends BasePreferences {

    protected PreferencesImpl(SharedPreferences preferences) {
        super(preferences);
    }

    @Override
    public <T> IPreferences put(String key, T value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        put(editor, key, value);
        editor.apply();
        return this;
    }

    @Override
    public IPreferences putAll(Map<String, ?> value) {
        if (value != null && value.size() > 0) {
            SharedPreferences.Editor editor = mPreferences.edit();
            Set<? extends Map.Entry<String, ?>> entries = value.entrySet();
            for (Map.Entry<String, ?> entry : entries) {
                put(editor, entry.getKey(), entry.getValue());
            }
            editor.apply();
        }
        return this;
    }

    private void put(SharedPreferences.Editor editor, String key, Object value) {
        if (value != null) {
            Class<?> c = value.getClass();
            if (Boolean.class.isAssignableFrom(c)) {
                editor.putBoolean(key, (Boolean) value);
            } else if (Integer.class.isAssignableFrom(c)) {
                editor.putInt(key, (Integer) value);
            } else if (Float.class.isAssignableFrom(c)) {
                editor.putFloat(key, (Float) value);
            } else if (Long.class.isAssignableFrom(c)) {
                editor.putLong(key, (Long) value);
            } else if (String.class.isAssignableFrom(c)) {
                editor.putString(key, (String) value);
            } else if (Set.class.isAssignableFrom(c)) {
                editor.putStringSet(key, (Set) value);
            } else if (Double.class.isAssignableFrom(c)) {
                editor.putString(key, value.toString());
            } else {
                // 不支持的类型
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String key, T defValue) {
        if (defValue == null) {
            return (T) get(key, Set.class, null);
        }
        Class<T> c = (Class<T>) defValue.getClass();
        return get(key, c, defValue);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> c, T defValue) {
        T result = null;
        if (Boolean.class.isAssignableFrom(c)) {
            result = (T) Boolean.valueOf(mPreferences.getBoolean(key, (Boolean) defValue));
        } else if (Integer.class.isAssignableFrom(c)) {
            result = (T) Integer.valueOf(mPreferences.getInt(key, (Integer) defValue));
        } else if (Float.class.isAssignableFrom(c)) {
            result = (T) Float.valueOf(mPreferences.getFloat(key, (Float) defValue));
        } else if (Long.class.isAssignableFrom(c)) {
            result = (T) Long.valueOf(mPreferences.getLong(key, (Long) defValue));
        } else if(String.class.isAssignableFrom(c)) {
            result = (T) mPreferences.getString(key, (String) defValue);
        } else if (Set.class.isAssignableFrom(c)) {
            result = (T) mPreferences.getStringSet(key, (Set) defValue);
        } else if (Double.class.isAssignableFrom(c)) {
            result = (T) getDouble(key, (Double) defValue);
        }
        return result;
    }

    private Double getDouble(String key, double defValue) {
        Double result = defValue;
        String ds = mPreferences.getString(key, null);
        if (ds != null && ds.length() > 0) {
            try {
                result = Double.valueOf(ds);
            } catch (NumberFormatException e) {
                result = defValue;
            }
        }
        return result;
    }
}
