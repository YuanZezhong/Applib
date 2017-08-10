package cn.sh.changxing.yuanyi.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * @class：PreViewDialog
 * @des:查看图片
 * @brief： Copyright:Copyright (c) 2016
 * Company:天好电子商务股份有限公司。
 * @author：yanxiaosa
 * @email：yanxiaosa@tele-hot.com
 * @date：Administrator on 2017/4/10 08:08
 */

public class AppUtils {
    private AppUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取APP名称
     * @param context
     * @return
     */
    public static String getAppName(Context context) {
        try {
            PackageManager e = context.getPackageManager();
            PackageInfo packageInfo = e.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    /**
     * 获取versionName
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager e = context.getPackageManager();
            PackageInfo packageInfo = e.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException var3) {
            var3.printStackTrace();
            return null;
        }
    }
}
