package cn.sh.changxing.yuanyi.theme.resource;

import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.support.annotation.AnimRes;
import android.support.annotation.AnyRes;
import android.support.annotation.ArrayRes;
import android.support.annotation.BoolRes;
import android.support.annotation.ColorInt;
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

/**
 * Created by yuanyi on 17-9-16.
 */

public interface ResourceParser {

    @NonNull
    CharSequence getText(@StringRes int id) throws NotFoundException;

    @NonNull
    CharSequence getQuantityText(@PluralsRes int id, int quantity)
            throws NotFoundException;

    @NonNull
    String getString(@StringRes int id) throws NotFoundException;

    @NonNull
    String getString(@StringRes int id, Object... formatArgs) throws NotFoundException;

    @NonNull
    String getQuantityString(@PluralsRes int id, int quantity, Object... formatArgs)
            throws NotFoundException;

    CharSequence getText(@StringRes int id, CharSequence def);

    @NonNull
    CharSequence[] getTextArray(@ArrayRes int id) throws NotFoundException;

    @NonNull
    String[] getStringArray(@ArrayRes int id) throws NotFoundException;

    @NonNull
    int[] getIntArray(@ArrayRes int id) throws NotFoundException;

    @NonNull
    TypedArray obtainTypedArray(@ArrayRes int id) throws NotFoundException;

    float getDimension(@DimenRes int id) throws NotFoundException;

    int getDimensionPixelOffset(@DimenRes int id) throws NotFoundException;

    int getDimensionPixelSize(@DimenRes int id) throws NotFoundException;

    float getFraction(@FractionRes int id, int base, int pbase);

    @Deprecated
    Drawable getDrawable(@DrawableRes int id) throws NotFoundException;

    Drawable getDrawable(@DrawableRes int id, @Nullable Theme theme)
            throws NotFoundException;

    @Deprecated
    Drawable getDrawableForDensity(@DrawableRes int id, int density) throws NotFoundException;

    Drawable getDrawableForDensity(@DrawableRes int id, int density, @Nullable Theme theme);

    Movie getMovie(@RawRes int id) throws NotFoundException;

    @ColorInt
    @Deprecated
    int getColor(@ColorRes int id) throws NotFoundException;

    @ColorInt
    int getColor(@ColorRes int id, @Nullable Theme theme) throws NotFoundException;

    @Nullable
    @Deprecated
    ColorStateList getColorStateList(@ColorRes int id) throws NotFoundException;

    @Nullable
    ColorStateList getColorStateList(@ColorRes int id, @Nullable Theme theme) throws NotFoundException;

    boolean getBoolean(@BoolRes int id) throws NotFoundException;

    int getInteger(@IntegerRes int id) throws NotFoundException;

    XmlResourceParser getLayout(@LayoutRes int id) throws NotFoundException;

    XmlResourceParser getAnimation(@AnimRes int id) throws NotFoundException;

    XmlResourceParser getXml(@XmlRes int id) throws NotFoundException;

    InputStream openRawResource(@RawRes int id) throws NotFoundException;

    InputStream openRawResource(@RawRes int id, TypedValue value) throws NotFoundException;

    AssetFileDescriptor openRawResourceFd(@RawRes int id) throws NotFoundException;

    void getValue(@AnyRes int id, TypedValue outValue, boolean resolveRefs) throws NotFoundException;

    void getValueForDensity(@AnyRes int id, int density, TypedValue outValue,
                            boolean resolveRefs) throws NotFoundException;

    int getIdentifier(String name, String defType, String defPackage);

    String getResourceName(@AnyRes int resid) throws NotFoundException;

    String getResourcePackageName(@AnyRes int resid) throws NotFoundException;

    String getResourceTypeName(@AnyRes int resid) throws NotFoundException;

    String getResourceEntryName(@AnyRes int resid) throws NotFoundException;

    /**
     * 获取在当前主题下对应的资源id
     *
     * @param resid 默认主题下的资源id
     * @return 对应主题下的资源id
     */
    int getEffectiveId(int resid);
}
