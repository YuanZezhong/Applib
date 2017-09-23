package cn.sh.changxing.yuanyi.theme;

import android.view.View;

import java.util.HashSet;
import java.util.Set;

import cn.sh.changxing.yuanyi.theme.attr.Attr;


/**
 * Created by yuanyi on 17-9-17.
 * <p>
 * 将View和在主题更换时需要改变的所有属性进行关联
 */
public class ViewAttrItem {
    private View view;

    private Set<Attr> attrs;

    public ViewAttrItem(View view) {
        if (view == null) {
            throw new IllegalArgumentException("view cannot be null!");
        }
        this.view = view;
        this.attrs = new HashSet<>();
    }

    public ViewAttrItem(View view, Set<Attr> attrs) {
        this(view);
        this.attrs.addAll(attrs);
    }

    public View getView() {
        return view;
    }

    public Set<Attr> getAttrs() {
        return attrs;
    }

    public void addAttrs(Set<Attr> attrs) {
        this.attrs.addAll(attrs);
    }

    public void addAttr(Attr attr) {
        attrs.add(attr);
    }

    public void destroy() {
        attrs.clear();
        attrs = null;
        view = null;
    }

    public void apply() {
        if (view != null) {
            for (Attr attr : attrs) {
                attr.apply(view);
            }
        }
    }
}
