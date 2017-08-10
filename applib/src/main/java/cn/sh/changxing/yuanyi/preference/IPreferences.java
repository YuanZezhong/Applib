package cn.sh.changxing.yuanyi.preference;

import java.util.Map;

/**
 * Created by YuanZezhong on 2017/8/10.
 *
 * 定义Preference的功能
 */
public interface IPreferences {
    public <T> IPreferences put(String key, T value);

    public <T> IPreferences putMap(Map<String, T> map);

    public <T> T get(String key, T defaultVal);
}
