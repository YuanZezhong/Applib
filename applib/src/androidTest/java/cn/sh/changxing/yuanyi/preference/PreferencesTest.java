package cn.sh.changxing.yuanyi.preference;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.sh.changxing.yuanyi.logger.LoggerFactory;

/**
 * Created by YuanZezhong on 2017/8/10.
 */
@RunWith(AndroidJUnit4.class)
public class PreferencesTest {
    private Context mContext;
    private IPreferences mPrefer;

    @Before
    public void init() {
        mContext = InstrumentationRegistry.getContext();
        mPrefer = PreferenceFactory.getPreferences(mContext, "test");
    }

    @Test
    public void testPutX() {
        HashSet<String> set = new HashSet<>();
        set.add("set_one");
        set.add("set_two");
        set.add("set_three");
        mPrefer.putBoolean("boolean", true)
                .putInt("int", 2341)
                .putLong("long", 2544879513156L)
                .putFloat("float", 23.234F)
                .putString("string", "test_string")
                .putStringSet("set", set);
    }

    @Test
    public void testGetX() {
        boolean b = mPrefer.getBoolean("boolean", false);
        int i = mPrefer.getInt("int", 0);
        long l = mPrefer.getLong("long", 0L);
        float f = mPrefer.getFloat("float", 0.0F);
        String s = mPrefer.getString("string", "");
        Set<String> set = mPrefer.getStringSet("set", null);
        LoggerFactory.getDefault().d("boolean: {}, int: {}, long: {}, float: {}, string: {}, set: {}",
                b, i, l, f, s, set);
    }

    @Test
    public void testPut() {
        HashSet<String> set = new HashSet<>();
        set.add("set_one");
        set.add("set_two");
        set.add("set_three");

        mPrefer.put("boolean", true)
                .putInt("int", 147)
                .put("long", 21474836472L)
                .put("float", 3.14F)
                .put("double", 1323472934.90823479)
                .put("string", "test string")
                .put("set", set);
    }

    @Test
    public void testGet() {
        Boolean b = mPrefer.get("boolean", false);
        Integer i = mPrefer.get("int", 0);
        Long l = mPrefer.get("long", 0L);
        Float f = mPrefer.get("float", 0F);
        String s = mPrefer.get("string", "");
        Set<String> set = mPrefer.get("set", null);
        Double d = mPrefer.get("double", 0.0);
        LoggerFactory.getDefault().d("boolean: {}, int: {}, long: {}, float: {}, double: {},  string: {}, set: {}",
                b, i, l, f, d, s, set);
    }

    @Test
    public void testPutAll() {
        HashSet<String> set = new HashSet<>();
        set.add("set_one");
        set.add("set_two");
        set.add("set_three");

        Map<String, Object> map = new HashMap<>();
        map.put("boolean", true);
        map.put("int", 147);
        map.put("float", 3.14F);
        map.put("double", 242342.92379);
        map.put("long", 2147483647123L);
        map.put("string", "string_key");
        map.put("set", set);
        mPrefer.putAll(map);
    }

    @Test
    public void testGetAll() {
        Map<String, ?> map = mPrefer.getAll();
        Set<? extends Map.Entry<String, ?>> entries = map.entrySet();
        for (Map.Entry<String, ?> entry : entries) {
            LoggerFactory.getDefault().d("key: {}, value: {}, class: {}", entry.getKey(), entry.getValue(), entry.getValue().getClass().getSimpleName());
        }
    }

    @Test
    public void testRemove() {
        mPrefer.remove("double", "boolean");
    }

    @Test
    public void testContaines() {
        boolean contains = mPrefer.contains("float");
        LoggerFactory.getDefault().d("is contains float key: {}", contains);
    }

    @Test
    public void testClear() {
        mPrefer.clear();
    }
}
