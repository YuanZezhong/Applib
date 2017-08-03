package cn.sh.changxing.yuanyi;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import cn.sh.changxing.yuanyi.logger.ILogger;
import cn.sh.changxing.yuanyi.logger.LoggerFactory;
import cn.sh.changxing.yuanyi.logger.LoggerImpl;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("cn.sh.changxing.bindert", appContext.getPackageName());
    }

    @Test
    public void testDefaultLogger() {
        ILogger logger = LoggerFactory.getDefault();
        loggerMsg(logger);
    }

    @Test
    public void testTagLogger() {
        ILogger logger = LoggerFactory.getLogger("Yuan");
        loggerMsg(logger);
    }

    @Test
    public void testCustomLogger() {
        ILogger logger = LoggerFactory.getCustomLogger(MyLogger.class, "Yuan");
        loggerMsg(logger);
    }

    @Test
    public void testClassTagLogger() {
        ILogger logger = LoggerFactory.getLogger(getClass());
        loggerMsg(logger);
    }

    private void loggerMsg(ILogger logger) {
        logger.setForceLog(false);
        logger.beginMethod();
        logger.trace("trace level msg");
        logger.trace("trace level format {}", logger.getName());
        logger.trace("trace level msg and throwable", new Throwable());
        logger.d("debug level msg");
        logger.d("debug level format {}", logger.getName());
        logger.d("debug level msg and throwable", new Throwable());
        logger.i("info level msg");
        logger.i("info level format {}", logger.getName());
        logger.i("info level msg and throwable", new Throwable());
        logger.w("warn level msg");
        logger.w("warn level format {}", logger.getName());
        logger.w("warn level msg and throwable", new Throwable());
        logger.e("error level msg");
        logger.e("error level format {}", logger.getName());
        logger.e("error level msg and throwable", new Throwable());
        logger.printStackTrace();
        logger.endMethod();
    }

    @Test
    public void getCaller() {
        LoggerImpl logger = (LoggerImpl) LoggerFactory.getDefault();
        logger.beginMethod();
        logger.d("caller info is {}", logger.getCallerInfo(0, ", ", true, true, false, false));
        logger.endMethod();
    }

    public void testCallerInfo() {
        Activity activity = null;
    }

    private static class MyLogger extends LoggerImpl {

        public MyLogger(String name) {
            super(name);
        }

        @Override
        public void beginMethod() {
            String method = getCallerInfo(4, "|", true, true, false, false);
            traceIn(method);
        }

        @Override
        public void endMethod() {
            String method = getCallerInfo(4, "|", true, true, false, false);
            traceOut(method);
        }
    }
}
