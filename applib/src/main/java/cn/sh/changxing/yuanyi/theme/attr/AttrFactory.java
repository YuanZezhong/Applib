package cn.sh.changxing.yuanyi.theme.attr;

import android.content.res.Resources;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuanyi on 17-9-17.
 */

public class AttrFactory {
    private AttrFactory() {

    }

    private static Map<String, Class<? extends Attr>> sSupportAttr = new HashMap<>();

    static {
        sSupportAttr.put("textColor", TextColorAttr.class);
        sSupportAttr.put("background", BackgroundAttr.class);
        sSupportAttr.put("src", SrcAttr.class);
    }

    /**
     * 创建一个属性
     */
    public static Attr createAttr(String attrName, int resid, String resName, String resType) {
        Class<? extends Attr> attrClass = sSupportAttr.get(attrName);
        if (attrClass != null) {
            try {
                Attr attr = attrClass.newInstance();
                attr.setName(attrName);
                attr.setValueResId(resid);
                attr.setValueResName(resName);
                attr.setValueResType(resType);
                return attr;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Attr createAttr(Resources resources, String attrName, int resid) {
        if (resources != null && resid > 0) {
            String resName = resources.getResourceEntryName(resid);
            String resType = resources.getResourceTypeName(resid);
            return createAttr(attrName, resid, resName, resType);
        }
        return null;
    }

    public static boolean isSupportAttr(String attrName) {
        return sSupportAttr.containsKey(attrName);
    }

    public static void addSupportAttr(String attrName, Class<? extends Attr> attr) {
        sSupportAttr.put(attrName, attr);
    }
}
