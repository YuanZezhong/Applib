package cn.sh.changxing.yuanyi.theme.resource;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.lang.reflect.Method;

import cn.sh.changxing.yuanyi.theme.ThemeInfo;


/**
 * Created by yuanyi on 17-9-17.
 */

public class ResourceUtils {
    private ResourceUtils() {

    }

    /**
     * 创建出指定主题包对应资源解析器Resources
     *
     * @param context   context
     * @param themeInfo 主题包信息
     * @return null:创建失败
     */
    public static Resources createResources(Context context, ThemeInfo themeInfo) {
        String themePath = themeInfo.getThemePath();
        if (themePath != null && themePath.length() > 0) {
            String packageName = getThemePackageName(context, themePath);
            if (packageName != null && packageName.length() > 0) {
                AssetManager assetManager = createAssetManager(themePath);
                if (assetManager != null) {
                    themeInfo.setPackageName(packageName);
                    Resources original = context.getResources();
                    return new Resources(assetManager, original.getDisplayMetrics(), original.getConfiguration());
                }
            }
        }
        return null;
    }

    public static Resources cloneResources(Context context) {
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        return context.createConfigurationContext(config).getResources();
    }

    private static String getThemePackageName(Context context, String themePath) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(themePath, PackageManager.GET_ACTIVITIES);
        return info == null ? null : info.packageName;
    }

    private static AssetManager createAssetManager(String themePath) {
        Class<AssetManager> assetManagerClass = AssetManager.class;
        try {
            Method addAssetPath = assetManagerClass.getDeclaredMethod("addAssetPath", String.class);
            addAssetPath.setAccessible(true);
            AssetManager assetManager = assetManagerClass.newInstance();
            Integer ret = (Integer) addAssetPath.invoke(assetManager, themePath);
            if (ret != 0) {
                return assetManager;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
