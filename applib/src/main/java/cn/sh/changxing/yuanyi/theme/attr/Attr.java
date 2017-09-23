package cn.sh.changxing.yuanyi.theme.attr;

import android.view.View;

/**
 * Created by yuanyi on 17-9-17.
 * <p>
 * 描述一个属性的信息
 */

public abstract class Attr {
    public static final String RES_TYPE_NAME_COLOR = "color";
    public static final String RES_TYPE_NAME_DRAWABLE = "drawable";
    public static final String RES_TYPE_NAME_MIPMAP = "mipmap";
    public static final String RES_TYPE_NAME_STRING = "string";

    /**
     * 属性名, 如: textColor, background
     */
    protected String name;

    /**
     * 属性引用的资源值, 如: 7f050007
     */
    protected int valueResId;

    /**
     * 属性引用的资源名, 如: app_name, colorAccent
     */
    protected String valueResName;

    /**
     * 属性引用的资源类型, 如: color, drawable, string
     */
    protected String valueResType;

    public abstract void apply(View v);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValueResId() {
        return valueResId;
    }

    public void setValueResId(int valueResId) {
        this.valueResId = valueResId;
    }

    public String getValueResName() {
        return valueResName;
    }

    public void setValueResName(String valueResName) {
        this.valueResName = valueResName;
    }

    public String getValueResType() {
        return valueResType;
    }

    public void setValueResType(String valueResType) {
        this.valueResType = valueResType;
    }

    /**
     * 资源类型是否伟drawable类型
     * @return
     */
    protected boolean isDrawableType() {
        return RES_TYPE_NAME_DRAWABLE.equals(valueResType);
    }

    protected boolean isMipmapType() {
        return RES_TYPE_NAME_MIPMAP.equals(valueResType);
    }

    /**
     * 资源类型是否为color类型
     * @return
     */
    protected boolean isColorType() {
        return RES_TYPE_NAME_COLOR.equals(valueResType);
    }

    /**
     * 资源类型是否为string类型
     * @return
     */
    protected boolean isStringType() {
        return RES_TYPE_NAME_STRING.equals(valueResType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attr attr = (Attr) o;

        return name != null ? name.equals(attr.name) : attr.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Attr{" +
                "name='" + name + '\'' +
                ", valueResId=" + valueResId +
                ", valueResName='" + valueResName + '\'' +
                ", valueResType='" + valueResType + '\'' +
                '}';
    }
}
