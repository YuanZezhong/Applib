package cn.sh.changxing.yuanyi.theme.attr;

import android.view.View;
import android.widget.TextView;

import cn.sh.changxing.yuanyi.theme.ThemeManager;
import cn.sh.changxing.yuanyi.theme.resource.ResourceParser;

/**
 * Created by yuanyi on 17-9-17.
 */

public class TextColorAttr extends Attr {
    @Override
    public void apply(View v) {
        if (isColorType()) {
            if (v instanceof TextView) {
                TextView textView = (TextView) v;
                ResourceParser resource = ThemeManager.getInstance().getCurrentResource();
                int color = resource.getColor(valueResId);
                textView.setTextColor(color);
            }
        }
    }
}
