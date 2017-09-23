package cn.sh.changxing.yuanyi.theme.resource;

import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.support.annotation.AnimRes;
import android.support.annotation.AnyRes;
import android.support.annotation.ArrayRes;
import android.support.annotation.BoolRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FractionRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.annotation.RawRes;
import android.support.annotation.StringRes;
import android.support.annotation.XmlRes;
import android.util.TypedValue;

import java.io.InputStream;

import cn.sh.changxing.yuanyi.theme.ThemeInfo;
import cn.sh.changxing.yuanyi.theme.ThemeManager;


/**
 * Created by yuanyi on 17-9-16.
 */

public class SingleResourceParser implements ChangeableResourceParser {
    private Resources mEffectiveResources;
    private ThemeInfo mTheme;

    public SingleResourceParser(Resources resources, ThemeInfo themeInfo) {
        mEffectiveResources = resources;
        mTheme = themeInfo;
    }

    @NonNull
    @Override
    public CharSequence getText(@StringRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.getText(getEffectiveId(id));
    }

    @NonNull
    @Override
    public CharSequence getQuantityText(@PluralsRes int id, int quantity) throws Resources.NotFoundException {
        return mEffectiveResources.getQuantityText(getEffectiveId(id), quantity);
    }

    @Override
    public String getString(int resid) {
        return mEffectiveResources.getString(getEffectiveId(resid));
    }

    @NonNull
    @Override
    public String getString(@StringRes int id, Object... formatArgs) throws Resources.NotFoundException {
        return mEffectiveResources.getString(getEffectiveId(id), formatArgs);
    }

    @NonNull
    @Override
    public String getQuantityString(@PluralsRes int id, int quantity, Object... formatArgs) throws Resources.NotFoundException {
        return mEffectiveResources.getQuantityString(getEffectiveId(id), quantity, formatArgs);
    }

    @Override
    public CharSequence getText(@StringRes int id, CharSequence def) {
        return mEffectiveResources.getText(getEffectiveId(id), def);
    }

    @NonNull
    @Override
    public CharSequence[] getTextArray(@ArrayRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.getTextArray(getEffectiveId(id));
    }

    @NonNull
    @Override
    public String[] getStringArray(@ArrayRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.getStringArray(getEffectiveId(id));
    }

    @NonNull
    @Override
    public int[] getIntArray(@ArrayRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.getIntArray(getEffectiveId(id));
    }

    @NonNull
    @Override
    public TypedArray obtainTypedArray(@ArrayRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.obtainTypedArray(getEffectiveId(id));
    }

    @Override
    public float getDimension(@DimenRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.getDimension(getEffectiveId(id));
    }

    @Override
    public int getDimensionPixelOffset(@DimenRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.getDimensionPixelOffset(getEffectiveId(id));
    }

    @Override
    public int getDimensionPixelSize(@DimenRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.getDimensionPixelSize(getEffectiveId(id));
    }

    @Override
    public float getFraction(@FractionRes int id, int base, int pbase) {
        return mEffectiveResources.getFraction(getEffectiveId(id), base, pbase);
    }

    @Override
    public Drawable getDrawable(int resid) {
        return mEffectiveResources.getDrawable(getEffectiveId(resid));
    }

    @Override
    public Drawable getDrawable(@DrawableRes int id, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        return mEffectiveResources.getDrawable(getEffectiveId(id), theme);
    }

    @Override
    public Drawable getDrawableForDensity(@DrawableRes int id, int density) throws Resources.NotFoundException {
        return mEffectiveResources.getDrawableForDensity(getEffectiveId(id), density);
    }

    @Override
    public Drawable getDrawableForDensity(@DrawableRes int id, int density, @Nullable Resources.Theme theme) {
        return mEffectiveResources.getDrawableForDensity(getEffectiveId(id), density, theme);
    }

    @Override
    public Movie getMovie(@RawRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.getMovie(getEffectiveId(id));
    }

    @Override
    public int getColor(int resid) {
        return mEffectiveResources.getColor(getEffectiveId(resid));
    }

    @Override
    public int getColor(@ColorRes int id, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        return mEffectiveResources.getColor(getEffectiveId(id), theme);
    }

    @Nullable
    @Override
    public ColorStateList getColorStateList(@ColorRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.getColorStateList(getEffectiveId(id));
    }

    @Nullable
    @Override
    public ColorStateList getColorStateList(@ColorRes int id, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        return mEffectiveResources.getColorStateList(getEffectiveId(id), theme);
    }

    @Override
    public boolean getBoolean(@BoolRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.getBoolean(getEffectiveId(id));
    }

    @Override
    public int getInteger(@IntegerRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.getInteger(getEffectiveId(id));
    }

    @Override
    public XmlResourceParser getLayout(@LayoutRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.getLayout(getEffectiveId(id));
    }

    @Override
    public XmlResourceParser getAnimation(@AnimRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.getAnimation(getEffectiveId(id));
    }

    @Override
    public XmlResourceParser getXml(@XmlRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.getXml(getEffectiveId(id));
    }

    @Override
    public InputStream openRawResource(@RawRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.openRawResource(getEffectiveId(id));
    }

    @Override
    public InputStream openRawResource(@RawRes int id, TypedValue value) throws Resources.NotFoundException {
        return mEffectiveResources.openRawResource(getEffectiveId(id), value);
    }

    @Override
    public AssetFileDescriptor openRawResourceFd(@RawRes int id) throws Resources.NotFoundException {
        return mEffectiveResources.openRawResourceFd(getEffectiveId(id));
    }

    @Override
    public void getValue(@AnyRes int id, TypedValue outValue, boolean resolveRefs) throws Resources.NotFoundException {
        mEffectiveResources.getValue(getEffectiveId(id), outValue, resolveRefs);
    }

    @Override
    public void getValueForDensity(@AnyRes int id, int density, TypedValue outValue, boolean resolveRefs) throws Resources.NotFoundException {
        mEffectiveResources.getValueForDensity(getEffectiveId(id), density, outValue, resolveRefs);
    }

    @Override
    public int getIdentifier(String name, String defType, String defPackage) {
        return mEffectiveResources.getIdentifier(name, defType, defPackage);
    }

    @Override
    public String getResourceName(@AnyRes int resid) throws Resources.NotFoundException {
        return mEffectiveResources.getResourceName(getEffectiveId(resid));
    }

    @Override
    public String getResourcePackageName(@AnyRes int resid) throws Resources.NotFoundException {
        return mEffectiveResources.getResourcePackageName(getEffectiveId(resid));
    }

    @Override
    public String getResourceTypeName(@AnyRes int resid) throws Resources.NotFoundException {
        return mEffectiveResources.getResourceTypeName(getEffectiveId(resid));
    }

    @Override
    public String getResourceEntryName(@AnyRes int resid) throws Resources.NotFoundException {
        return mEffectiveResources.getResourceEntryName(getEffectiveId(resid));
    }

    @Override
    public void changeThemeInfo(ThemeInfo themeInfo) {
        mTheme = themeInfo;
    }

    @Override
    public void changeThemeInfo(Resources themeResource, ThemeInfo themeInfo) {
        changeThemeInfo(themeInfo);
    }

    @Override
    public int getEffectiveId(int resid) {
        int result = resid;
        if (!ThemeManager.getInstance().isDefaultTheme(mTheme) && resid > 0) {
            String entry = mEffectiveResources.getResourceEntryName(resid);
            String type = mEffectiveResources.getResourceTypeName(resid);
            String packageName = mTheme.getPackageName();
            result = mEffectiveResources.getIdentifier(entry + mTheme.getResNameSuffix(), type, packageName);
            // 未找到特定主题下的对应的资源, 使用默认主题的资源
            if (result == 0) {
                result = resid;
            }
        }
        return result;
    }
}
