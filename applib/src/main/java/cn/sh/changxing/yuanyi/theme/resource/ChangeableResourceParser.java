package cn.sh.changxing.yuanyi.theme.resource;

import android.content.res.Resources;

import cn.sh.changxing.yuanyi.theme.ThemeInfo;


/**
 * Created by YuanZezhong on 2017/9/20.
 */
public abstract interface ChangeableResourceParser extends ResourceParser {
    /**
     * 改变主题信息, 此方法用于单皮肤包模式
     *
     * @param info 新的主题信息
     */
    void changeThemeInfo(ThemeInfo info);

    /**
     * 改变主题信息, 此方法用于多皮肤包模式
     *
     * @param themeResource 新的主题资源管理器
     * @param themeInfo     新的主题信息
     */
    void changeThemeInfo(Resources themeResource, ThemeInfo themeInfo);
}
