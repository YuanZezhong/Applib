package cn.sh.changxing.yuanyi.theme.attr;

import android.view.View;

import cn.sh.changxing.yuanyi.theme.ThemeManager;
import cn.sh.changxing.yuanyi.theme.resource.ResourceParser;


/**
 * Created by yuanyi on 17-9-17.
 */

public class BackgroundAttr extends Attr {
    @Override
    public void apply(View v) {
        ResourceParser resource = ThemeManager.getInstance().getCurrentResource();
        if (isColorType()) {
            v.setBackgroundColor(resource.getColor(valueResId));
        } else if (isDrawableType()) {
            v.setBackground(resource.getDrawable(valueResId));
        }
    }
}
