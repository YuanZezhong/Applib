package cn.sh.changxing.yuanyi.utils.regex;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import cn.sh.changxing.yuanyi.logger.LoggerFactory;

@RunWith(AndroidJUnit4.class)
public class RegexUtilsTest {


    @Test
    public void isMobileSimple() throws Exception {
        LoggerFactory.getDefault().beginMethod();
        LoggerFactory.getDefault().d(RegexUtils.isMobileSimple("10000000000")+"isMobileSimple");
        LoggerFactory.getDefault().endMethod();
    }

    @Test
    public void isMobileExact() throws Exception {
        LoggerFactory.getDefault().beginMethod();
        LoggerFactory.getDefault().d(RegexUtils.isMobileExact("15555994291")+"isMobileExact");
        LoggerFactory.getDefault().endMethod();
    }

    @Test
    public void isTel() throws Exception {
        LoggerFactory.getDefault().beginMethod();
        LoggerFactory.getDefault().d(RegexUtils.isTel("021-56903818")+"isTel");
        LoggerFactory.getDefault().endMethod();
    }

    @Test
    public void isIDCard15() throws Exception {

    }

    @Test
    public void isIDCard18() throws Exception {
        LoggerFactory.getDefault().beginMethod();
        LoggerFactory.getDefault().d(RegexUtils.isIDCard18("")+"isIDCard18");
        LoggerFactory.getDefault().endMethod();
    }

    @Test
    public void isEmail() throws Exception {
        LoggerFactory.getDefault().beginMethod();
        LoggerFactory.getDefault().d(RegexUtils.isEmail("1723313411@qq.com")+"isEmail");
        LoggerFactory.getDefault().endMethod();
    }

    @Test
    public void isMatch() throws Exception {

    }
}