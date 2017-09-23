package cn.sh.changxing.yuanyi.theme;

import android.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.sh.changxing.yuanyi.theme.attr.Attr;
import cn.sh.changxing.yuanyi.theme.attr.AttrFactory;

/**
 * Created by yuanyi on 17-9-17.
 */

public class SimpleViewAttrManager implements ViewAttrManager {
    private Map<View, ViewAttrItem> mViewAttrsItems;

    public SimpleViewAttrManager() {
        mViewAttrsItems = new HashMap<>();
    }

    @Override
    public void addViewAttrItem(ViewAttrItem item) {
        ViewAttrItem viewAttrItem = mViewAttrsItems.get(item.getView());
        if (viewAttrItem == null) {
            mViewAttrsItems.put(item.getView(), item);
        } else {
            viewAttrItem.addAttrs(item.getAttrs());
        }
    }

    @Override
    public void removeViewAttrItem(ViewAttrItem item) {
        ViewAttrItem remove = mViewAttrsItems.remove(item.getView());
        if (remove != null) {
            remove.destroy();
        }
    }

    @Override
    public void addViewAttr(View view, String attrName, int resid) {
        if (view != null) {
            Attr attr = AttrFactory.createAttr(ThemeManager.getInstance().getOriginalResource(), attrName, resid);
            if (attr != null) {
                ViewAttrItem viewAttrItem = mViewAttrsItems.get(view);
                if (viewAttrItem != null) {
                    viewAttrItem.addAttr(attr);
                } else {
                    ViewAttrItem item = new ViewAttrItem(view);
                    item.addAttr(attr);
                    mViewAttrsItems.put(view, item);
                }
            }
        }
    }

    @Override
    public void removeViewAttrItem(View view) {
        ViewAttrItem remove = mViewAttrsItems.remove(view);
        if (remove != null) {
            remove.destroy();
        }
    }

    @Override
    public void removeViewAttrItems(View... views) {
        if (views != null) {
            for (View view : views) {
                removeViewAttrItem(view);
            }
        }
    }

    @Override
    public void clear() {
        Set<Map.Entry<View, ViewAttrItem>> items = mViewAttrsItems.entrySet();
        for (Map.Entry<View, ViewAttrItem> item : items) {
            item.getValue().destroy();
        }
        mViewAttrsItems.clear();
    }

    @Override
    public void applyThemeChange() {
        Set<Map.Entry<View, ViewAttrItem>> items = mViewAttrsItems.entrySet();
        for (Map.Entry<View, ViewAttrItem> item : items) {
            item.getValue().apply();
        }
    }
}
