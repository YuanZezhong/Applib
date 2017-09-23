package cn.sh.changxing.yuanyi.theme.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.sh.changxing.yuanyi.theme.OnThemeChangedListener;
import cn.sh.changxing.yuanyi.theme.SimpleViewAttrManager;
import cn.sh.changxing.yuanyi.theme.ThemeInflaterFactory;
import cn.sh.changxing.yuanyi.theme.ThemeInfo;
import cn.sh.changxing.yuanyi.theme.ThemeManager;
import cn.sh.changxing.yuanyi.theme.ViewAttrManager;


/**
 * Created by YuanZezhong on 2017/9/19.
 */
public abstract class ThemeActivity extends Activity implements OnThemeChangedListener {
    protected ThemeInflaterFactory mInflaterFactory;
    protected ViewAttrManager mViewAttrManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ThemeManager.getInstance().addOnThemeChangedListener(this);
        ViewAttrManager viewAttrManager = onCreateViewAttrManager();
        mViewAttrManager = viewAttrManager == null ? new SimpleViewAttrManager() : viewAttrManager;
        mInflaterFactory = new ThemeInflaterFactory(getLayoutInflater(), mViewAttrManager);
        getLayoutInflater().setFactory(mInflaterFactory);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onThemeChanged(ThemeInfo oldTheme, ThemeInfo newTheme) {
        mViewAttrManager.applyThemeChange();
    }

    public ViewAttrManager onCreateViewAttrManager() {
        return null;
    }

    public ThemeInflaterFactory getInflaterFactory() {
        return mInflaterFactory;
    }

    public ViewAttrManager getViewAttrManager() {
        return mViewAttrManager;
    }

    @Override
    protected void onDestroy() {
        ThemeManager.getInstance().removeOnThemeChangedListener(this);
        mViewAttrManager.clear();
        mViewAttrManager = null;
        mInflaterFactory = null;
        super.onDestroy();
    }
}
