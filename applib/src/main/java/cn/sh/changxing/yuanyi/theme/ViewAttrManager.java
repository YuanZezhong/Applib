package cn.sh.changxing.yuanyi.theme;

import android.view.View;

/**
 * Created by yuanyi on 17-9-17.
 */

public interface ViewAttrManager {
    void addViewAttrItem(ViewAttrItem item);

    void removeViewAttrItem(ViewAttrItem item);

    void addViewAttr(View view, String attrName, int resid);

    void removeViewAttrItem(View view);

    void removeViewAttrItems(View... views);

    void clear();

    void applyThemeChange();
}
