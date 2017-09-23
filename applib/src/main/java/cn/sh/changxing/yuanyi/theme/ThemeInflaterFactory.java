package cn.sh.changxing.yuanyi.theme;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.util.HashSet;
import java.util.Set;

import cn.sh.changxing.yuanyi.theme.attr.Attr;
import cn.sh.changxing.yuanyi.theme.attr.AttrFactory;

/**
 * Created by yuanyi on 17-9-17.
 */

public class ThemeInflaterFactory implements LayoutInflater.Factory {
    private ViewAttrManager mViewAttrManager;
    private LayoutInflater mInflater;

    public ThemeInflaterFactory(LayoutInflater inflater) {
        mViewAttrManager = new SimpleViewAttrManager();
        mInflater = inflater;
    }

    public ThemeInflaterFactory(LayoutInflater inflater, ViewAttrManager viewAttrManager) {
        if (viewAttrManager == null) {
            throw new IllegalArgumentException("viewAttrManager cannot be null!");
        }
        mViewAttrManager = viewAttrManager;
        mInflater = inflater;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        boolean enable = attrs.getAttributeBooleanValue(ThemeConfig.NAMESPACE, ThemeConfig.ATTR_SKIN_ENABLE, false);
        if (enable) {
            View view = createView(name, context, attrs);
            if (view != null) {
                parseAttr(view, attrs);
                return view;
            }
        }
        return null;
    }

    private View createView(String name, Context context, AttributeSet attrs) {
        LayoutInflater inflater = mInflater == null? LayoutInflater.from(context): mInflater;
        return ViewProducer.createViewFromTag(inflater, context, name, attrs);
    }

    private void parseAttr(View view, AttributeSet attrSet) {
        Resources resource = ThemeManager.getInstance().getOriginalResource();
        Set<Attr> attrs = new HashSet<>();
        String attrName;
        String attrValue;
        for (int i = 0; i < attrSet.getAttributeCount(); i++) {
            attrName = attrSet.getAttributeName(i);
            attrValue = attrSet.getAttributeValue(i);
            if (AttrFactory.isSupportAttr(attrName) && attrValue.startsWith("@")) {
                int resid = Integer.parseInt(attrValue.substring(1));
                if (resid > 0) {
                    Attr attr = AttrFactory.createAttr(resource, attrName, resid);
                    if (attr != null) {
                        attrs.add(attr);
                    }
                }
            }
        }
        if (attrs.size() > 0) {
            ViewAttrItem item = new ViewAttrItem(view, attrs);
            if (!ThemeManager.getInstance().isDefaultTheme()) {
                item.apply();
            }
            mViewAttrManager.addViewAttrItem(item);
        }
    }

    public ViewAttrManager getViewAttrManager() {
        return mViewAttrManager;
    }
}
