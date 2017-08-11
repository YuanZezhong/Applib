package cn.sh.changxing.yuanyi.preference;

import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by YuanZezhong on 2017/8/11.
 */
public class PreferenceFactory {
    public static IPreferences getPreferences(Context context, String name, int mode) {
        IPreferences preferences = null;
        if (context == null) {
            throw new IllegalArgumentException("context can not be null");
        }
        return new PreferencesImpl(context.getSharedPreferences(name, mode));
    }

    public static IPreferences getPreferences(Context context, String name) {
        return getPreferences(context, name, Context.MODE_PRIVATE);
    }

    public static IPreferences getPreferences(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context can not be null");
        }
        return new PreferencesImpl(PreferenceManager.getDefaultSharedPreferences(context));
    }

    public static IPreferences getPreferences(Activity activity, int mode) {
        if (activity == null) {
            throw new IllegalArgumentException("activity can not be null");
        }
        return new PreferencesImpl(activity.getPreferences(mode));
    }
}
