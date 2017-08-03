package cn.sh.changxing.yuanyi.logger;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by YuanZezhong on 2017/7/26.
 */
public class LoggerFactory {
    public static final String DEFAULT_TAG = "DefaultLogger";
    private static final int TAG_MAX_LENGTH = 23;

    private static final ConcurrentMap<String, ILogger> sLoggerMap = new ConcurrentHashMap<String, ILogger>();
    private static ILogger sDefaultLogger;

    public static ILogger getLogger(String name) {
        return getCustomLogger(LoggerImpl.class, name);
    }

    public static ILogger getLogger(Class<?> tagClass) {
        return tagClass == null ? getDefault() : getLogger(tagClass.getName());
    }

    public static ILogger getDefault() {
        if (sDefaultLogger == null) {
            sDefaultLogger = getLogger(DEFAULT_TAG);
        }
        return sDefaultLogger;
    }

    public static <T extends AbstractNamedLogger> ILogger getCustomLogger(Class<T> loggerClass, Class<?> tagClass) {
        return getCustomLogger(loggerClass, tagClass.getName());
    }

    public static <T extends AbstractNamedLogger> ILogger getCustomLogger(Class<T> loggerClass, String name) {
        if (loggerClass == null) {
            throw new IllegalArgumentException("loggerClass cannot be null");
        }
        String tag = loggerNameToTag(name);
        ILogger logger = sLoggerMap.get(tag);
        if (logger == null) {
            try {
                Constructor<T> constructor = loggerClass.getConstructor(String.class);
                constructor.newInstance(tag);
                ILogger newInstance = constructor.newInstance(tag);
                ILogger oldInstance = sLoggerMap.putIfAbsent(tag, newInstance);
                logger = oldInstance == null ? newInstance : oldInstance;
            } catch (Exception e) {
                throw new RuntimeException("instantiation Logger error ", e);
            }
        }
        return logger;
    }

    private static String loggerNameToTag(String loggerName) {
        if (loggerName == null) {
            return DEFAULT_TAG;
        }

        int length = loggerName.length();
        if (length <= TAG_MAX_LENGTH) {
            return loggerName;
        }

        int tagLength = 0;
        int lastTokenIndex = 0;
        int lastPeriodIndex;
        StringBuilder tagName = new StringBuilder(TAG_MAX_LENGTH + 3);
        while ((lastPeriodIndex = loggerName.indexOf('.', lastTokenIndex)) != -1) {
            tagName.append(loggerName.charAt(lastTokenIndex));
            int tokenLength = lastPeriodIndex - lastTokenIndex;
            if (tokenLength > 1) {
                tagName.append('*');
            }
            tagName.append('.');
            lastTokenIndex = lastPeriodIndex + 1;

            tagLength = tagName.length();
            if (tagLength > TAG_MAX_LENGTH) {
                return getSimpleName(loggerName);
            }
        }

        int tokenLength = length - lastTokenIndex;
        if (tagLength == 0 || (tagLength + tokenLength) > TAG_MAX_LENGTH) {
            return getSimpleName(loggerName);
        }

        tagName.append(loggerName, lastTokenIndex, length);
        return tagName.toString();
    }

    private static String getSimpleName(String loggerName) {
        int length = loggerName.length();
        int lastPeriodIndex = loggerName.lastIndexOf('.');
        return lastPeriodIndex != -1 && length - (lastPeriodIndex + 1) <= TAG_MAX_LENGTH ? loggerName.substring(lastPeriodIndex + 1) : '*' + loggerName
                .substring(length - TAG_MAX_LENGTH + 1);
    }
}