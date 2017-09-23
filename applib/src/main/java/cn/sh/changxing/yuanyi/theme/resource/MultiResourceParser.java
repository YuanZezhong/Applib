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

public class MultiResourceParser implements ChangeableResourceParser {
    private Resources mOriginalResources;
    private Resources mEffectiveResources;
    private ThemeInfo mTheme;

    public MultiResourceParser(Resources resources, ThemeInfo themeInfo) {
        mOriginalResources = mEffectiveResources = resources;
        mTheme = themeInfo;
    }

    @NonNull
    @Override
    public CharSequence getText(@StringRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getText(effectiveId);
        } else {
            return mOriginalResources.getText(id);
        }
    }

    @NonNull
    @Override
    public CharSequence getQuantityText(@PluralsRes int id, int quantity) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getQuantityText(effectiveId, quantity);
        } else {
            return mOriginalResources.getQuantityText(id, quantity);
        }
    }

    @Override
    public String getString(int resid) {
        int effectiveId = getEffectiveId(resid);
        if (effectiveId > 0) {
            return mEffectiveResources.getString(effectiveId);
        } else {
            return mOriginalResources.getString(resid);
        }
    }

    @NonNull
    @Override
    public String getString(@StringRes int id, Object... formatArgs) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getString(effectiveId, formatArgs);
        } else {
            return mOriginalResources.getString(id, formatArgs);
        }
    }

    @NonNull
    @Override
    public String getQuantityString(@PluralsRes int id, int quantity, Object... formatArgs) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getQuantityString(effectiveId, quantity, formatArgs);
        } else {
            return mOriginalResources.getQuantityString(id, quantity, formatArgs);
        }
    }

    @Override
    public CharSequence getText(@StringRes int id, CharSequence def) {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getText(effectiveId, def);
        } else {
            return mOriginalResources.getText(id, def);
        }
    }

    @NonNull
    @Override
    public CharSequence[] getTextArray(@ArrayRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getTextArray(effectiveId);
        } else {
            return mOriginalResources.getTextArray(id);
        }
    }

    @NonNull
    @Override
    public String[] getStringArray(@ArrayRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getStringArray(effectiveId);
        } else {
            return mOriginalResources.getStringArray(id);
        }
    }

    @NonNull
    @Override
    public int[] getIntArray(@ArrayRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getIntArray(effectiveId);
        } else {
            return mOriginalResources.getIntArray(id);
        }
    }

    @NonNull
    @Override
    public TypedArray obtainTypedArray(@ArrayRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.obtainTypedArray(effectiveId);
        } else {
            return mOriginalResources.obtainTypedArray(id);
        }
    }

    @Override
    public float getDimension(@DimenRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getDimension(effectiveId);
        } else {
            return mOriginalResources.getDimension(id);
        }
    }

    @Override
    public int getDimensionPixelOffset(@DimenRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getDimensionPixelOffset(effectiveId);
        } else {
            return mOriginalResources.getDimensionPixelOffset(id);
        }
    }

    @Override
    public int getDimensionPixelSize(@DimenRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getDimensionPixelSize(effectiveId);
        } else {
            return mOriginalResources.getDimensionPixelSize(id);
        }
    }

    @Override
    public float getFraction(@FractionRes int id, int base, int pbase) {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getFraction(effectiveId, base, pbase);
        } else {
            return mOriginalResources.getFraction(id, base, pbase);
        }
    }

    @Override
    public Drawable getDrawable(int resid) {
        int effectiveId = getEffectiveId(resid);
        if (effectiveId > 0) {
            return mEffectiveResources.getDrawable(effectiveId);
        } else {
            return mOriginalResources.getDrawable(resid);
        }
    }

    @Override
    public Drawable getDrawable(@DrawableRes int id, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getDrawable(effectiveId, theme);
        } else {
            return mOriginalResources.getDrawable(id, theme);
        }
    }

    @Override
    public Drawable getDrawableForDensity(@DrawableRes int id, int density) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getDrawableForDensity(effectiveId, density);
        } else {
            return mOriginalResources.getDrawableForDensity(id, density);
        }
    }

    @Override
    public Drawable getDrawableForDensity(@DrawableRes int id, int density, @Nullable Resources.Theme theme) {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getDrawableForDensity(effectiveId, density, theme);
        } else {
            return mOriginalResources.getDrawableForDensity(id, density, theme);
        }
    }

    @Override
    public Movie getMovie(@RawRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getMovie(effectiveId);
        } else {
            return mOriginalResources.getMovie(id);
        }
    }

    @Override
    public int getColor(int resid) {
        int effectiveId = getEffectiveId(resid);
        if (effectiveId > 0) {
            return mEffectiveResources.getColor(effectiveId);
        } else {
            return mOriginalResources.getColor(resid);
        }
    }

    @Override
    public int getColor(@ColorRes int id, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getColor(effectiveId, theme);
        } else {
            return mOriginalResources.getColor(id, theme);
        }
    }

    @Nullable
    @Override
    public ColorStateList getColorStateList(@ColorRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getColorStateList(effectiveId);
        } else {
            return mOriginalResources.getColorStateList(id);
        }
    }

    @Nullable
    @Override
    public ColorStateList getColorStateList(@ColorRes int id, @Nullable Resources.Theme theme) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getColorStateList(effectiveId, theme);
        } else {
            return mOriginalResources.getColorStateList(id, theme);
        }
    }

    @Override
    public boolean getBoolean(@BoolRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getBoolean(effectiveId);
        } else {
            return mOriginalResources.getBoolean(id);
        }
    }

    @Override
    public int getInteger(@IntegerRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getInteger(effectiveId);
        } else {
            return mOriginalResources.getInteger(id);
        }
    }

    @Override
    public XmlResourceParser getLayout(@LayoutRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getLayout(effectiveId);
        } else {
            return mOriginalResources.getLayout(id);
        }
    }

    @Override
    public XmlResourceParser getAnimation(@AnimRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getAnimation(effectiveId);
        } else {
            return mOriginalResources.getAnimation(id);
        }
    }

    @Override
    public XmlResourceParser getXml(@XmlRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.getXml(effectiveId);
        } else {
            return mOriginalResources.getXml(id);
        }
    }

    @Override
    public InputStream openRawResource(@RawRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.openRawResource(effectiveId);
        } else {
            return mOriginalResources.openRawResource(id);
        }
    }

    @Override
    public InputStream openRawResource(@RawRes int id, TypedValue value) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.openRawResource(effectiveId, value);
        } else {
            return mOriginalResources.openRawResource(id, value);
        }
    }

    @Override
    public AssetFileDescriptor openRawResourceFd(@RawRes int id) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            return mEffectiveResources.openRawResourceFd(effectiveId);
        } else {
            return mOriginalResources.openRawResourceFd(id);
        }
    }

    @Override
    public void getValue(@AnyRes int id, TypedValue outValue, boolean resolveRefs) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            mEffectiveResources.getValue(effectiveId, outValue, resolveRefs);
        } else {
            mOriginalResources.getValue(id, outValue, resolveRefs);
        }
    }

    @Override
    public void getValueForDensity(@AnyRes int id, int density, TypedValue outValue, boolean resolveRefs) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(id);
        if (effectiveId > 0) {
            mEffectiveResources.getValueForDensity(effectiveId, density, outValue, resolveRefs);
        } else {
            mOriginalResources.getValueForDensity(id, density, outValue, resolveRefs);
        }
    }

    @Override
    public int getIdentifier(String name, String defType, String defPackage) {
        return mEffectiveResources.getIdentifier(name, defType, defPackage);
    }

    @Override
    public String getResourceName(@AnyRes int resid) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(resid);
        if (effectiveId > 0) {
            return mEffectiveResources.getResourceName(effectiveId);
        } else {
            return mOriginalResources.getResourceName(resid);
        }
    }

    @Override
    public String getResourcePackageName(@AnyRes int resid) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(resid);
        if (effectiveId > 0) {
            return mEffectiveResources.getResourcePackageName(effectiveId);
        } else {
            return mOriginalResources.getResourcePackageName(resid);
        }
    }

    @Override
    public String getResourceTypeName(@AnyRes int resid) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(resid);
        if (effectiveId > 0) {
            return mEffectiveResources.getResourceTypeName(effectiveId);
        } else {
            return mOriginalResources.getResourceTypeName(resid);
        }
    }

    @Override
    public String getResourceEntryName(@AnyRes int resid) throws Resources.NotFoundException {
        int effectiveId = getEffectiveId(resid);
        if (effectiveId > 0) {
            return mEffectiveResources.getResourceEntryName(effectiveId);
        } else {
            return mOriginalResources.getResourceEntryName(resid);
        }
    }

    @Override
    public void changeThemeInfo(ThemeInfo themeInfo) {
        mEffectiveResources = mOriginalResources;
        mTheme = themeInfo;
    }

    @Override
    public void changeThemeInfo(Resources themeResource, ThemeInfo themeInfo) {
        mEffectiveResources = themeResource;
        mTheme = themeInfo;
    }

    @Override
    public int getEffectiveId(int resid) {
        int result = resid;
        if (!ThemeManager.getInstance().isDefaultTheme(mTheme) && resid > 0) {
            String entry = mOriginalResources.getResourceEntryName(resid);
            String type = mOriginalResources.getResourceTypeName(resid);
            String packageName = mTheme.getPackageName();
            result = mEffectiveResources.getIdentifier(entry, type, packageName);
            // 如果主题资源包中不存在指定资源，则返回无效的资源ID
        }
        return result;
    }
}
