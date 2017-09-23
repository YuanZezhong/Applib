package cn.sh.changxing.yuanyi.theme;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yuanyi on 17-9-17.
 */

public class ViewProducer {
    private static Method onCreateViewMethod;
    private static Field mConstructorArgsField;

    static {
        Class<LayoutInflater> clazz = LayoutInflater.class;
        try {
            onCreateViewMethod = clazz.getDeclaredMethod("onCreateView", View.class, String.class, AttributeSet.class);
            onCreateViewMethod.setAccessible(true);
            mConstructorArgsField = clazz.getDeclaredField("mConstructorArgs");
            mConstructorArgsField.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private ViewProducer() {

    }

    public static View createViewFromTag(LayoutInflater inflater, Context context, String name, AttributeSet attrs) {
        Object[] mConstructorArgs = null;
        View result;
        Object lastContext = null;
        try {
            mConstructorArgs = ((Object[]) mConstructorArgsField.get(inflater));
            lastContext = mConstructorArgs[0];
            mConstructorArgs[0] = context;
            if (-1 == name.indexOf('.')) {
                result = (View) onCreateViewMethod.invoke(inflater, null, name, attrs);
            } else {
                result = inflater.createView(name, null, attrs);
            }
            return result;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (mConstructorArgs != null && lastContext != null) {
                mConstructorArgs[0] = lastContext;
            }
        }
        return null;
    }
}
