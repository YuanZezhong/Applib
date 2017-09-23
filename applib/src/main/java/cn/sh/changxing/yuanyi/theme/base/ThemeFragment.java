package cn.sh.changxing.yuanyi.theme.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.sh.changxing.yuanyi.theme.ViewAttrManager;


/**
 * Created by YuanZezhong on 2017/9/19.
 * <p>
 * 1.此Fragment必须和ThemeFragmentActivity一起使用
 * <p>
 * 2.此Fragment中对应主题更换的View应交给宿主Activity的ViewAttrManager管理
 * <p>
 * 3.此Fragment中手动填充布局应使用来自getActivityLayoutInflater()的LayoutInflater;
 * <p>
 * 4.此Fragment并不监听主题更换事件, 主题更换时此Fragment中对应主题更换的View皆由宿主Activity更新.
 *   如果需要监听主题更换事件处理特殊的事情, 需要手动注册监听器OnThemeChangedListener.
 */
public abstract class ThemeFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof ThemeFragmentActivity)) {
            throw new RuntimeException("ThemeFragment must be used with ThemeFramentActivity!");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        View view = getView();
        if (view != null) {
            onRemoveAllViewAttrs(view);
        }
    }

    /**
     * 获取宿主Activity的LayoutInflater
     *
     * @return 宿主Activity的LayoutInflater
     */
    public final LayoutInflater getActivityLayoutInflater() {
        return getActivity().getLayoutInflater();
    }

    /**
     * 获取宿主Activity的ViewAttrManager
     */
    public final ViewAttrManager getActivityViewAttrManager() {
        FragmentActivity activity = getActivity();
        if (activity instanceof ThemeFragmentActivity) {
            return ((ThemeFragmentActivity) activity).getViewAttrManager();
        }
        return null;
    }

    /**
     * 清理此Fragment中所有对应主题更换的View
     * <p>
     * 默认实现是从根View开始递归清理
     */
    protected void onRemoveAllViewAttrs(View view) {
        getActivityViewAttrManager().removeViewAttrItem(view);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                onRemoveAllViewAttrs(viewGroup.getChildAt(i));
            }
        }
    }
}
