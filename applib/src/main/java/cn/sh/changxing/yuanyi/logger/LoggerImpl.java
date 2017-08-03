package cn.sh.changxing.yuanyi.logger;

import android.util.Log;

/**
 * Created by YuanZezhong on 2017/7/26.
 */
public class LoggerImpl extends AbstractNamedLogger {
    private boolean mIsForceLog = true;
    private boolean mIsEnabled = true;

    public LoggerImpl(String name) {
        super(name);
    }

    @Override
    public boolean isTraceEnabled() {
        return isLoggable(Log.VERBOSE);
    }

    @Override
    public void trace(String msg) {
        log(Log.VERBOSE, msg, null);
    }

    @Override
    public void trace(String format, Object... args) {
        formatAndLog(Log.VERBOSE, format, args);
    }

    @Override
    public void trace(String msg, Throwable t) {
        log(Log.VERBOSE, msg, t);
    }

    @Override
    public void traceIn(String msg) {
        log(Log.VERBOSE, "-----------------" + msg + "--BEGIN-----------------", null);
    }

    @Override
    public void traceOut(String msg) {
        log(Log.VERBOSE, "-----------------" + msg + "--END-----------------", null);
    }

    @Override
    public void beginMethod() {
        String method = getCallerInfo(1, ", ", true, true, false, false);
        traceIn(method);
    }

    @Override
    public void endMethod() {
        String method = getCallerInfo(1, ", ", true, true, false, false);
        traceOut(method);
    }

    @Override
    public boolean isDebugEnabled() {
        return isLoggable(Log.DEBUG);
    }

    @Override
    public void d(String msg) {
        log(Log.DEBUG, msg, null);
    }

    @Override
    public void d(String format, Object... args) {
        formatAndLog(Log.DEBUG, format, args);
    }

    @Override
    public void d(String msg, Throwable t) {
        log(Log.DEBUG, msg, t);
    }

    @Override
    public boolean isInfoEnabled() {
        return isLoggable(Log.INFO);
    }

    @Override
    public void i(String msg) {
        log(Log.INFO, msg, null);
    }

    @Override
    public void i(String format, Object... args) {
        formatAndLog(Log.INFO, format, args);
    }

    @Override
    public void i(String msg, Throwable t) {
        log(Log.INFO, msg, t);
    }

    @Override
    public boolean isWarnEnabled() {
        return isLoggable(Log.WARN);
    }

    @Override
    public void w(String msg) {
        log(Log.WARN, msg, null);
    }

    @Override
    public void w(String format, Object... args) {
        formatAndLog(Log.WARN, format, args);
    }

    @Override
    public void w(String msg, Throwable t) {
        log(Log.WARN, msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return isLoggable(Log.ERROR);
    }

    @Override
    public void e(String msg) {
        log(Log.ERROR, msg, null);
    }

    @Override
    public void e(String format, Object... args) {
        formatAndLog(Log.ERROR, format, args);
    }

    @Override
    public void e(String msg, Throwable t) {
        log(Log.ERROR, msg, t);
    }

    @Override
    public void printStackTrace() {
        if (isEnabled() && (isForceLog() || isLoggable(Log.VERBOSE))) {
            StackTraceElement[] stes = Thread.currentThread().getStackTrace();
            StringBuilder result = new StringBuilder();
            for (int i = 3; i < stes.length; i++) {
                StackTraceElement ste = stes[i];
                if (ste.isNativeMethod()) {
                    continue;
                }
                result.append(ste.toString()).append("\n");
            }
            logInternal(Log.VERBOSE, result.toString(), null);
        }
    }

    @Override
    public boolean isEnabled() {
        return mIsEnabled;
    }

    @Override
    public void setEnabled(boolean isEnabled) {
        mIsEnabled = isEnabled;
    }

    @Override
    public boolean isForceLog() {
        return mIsForceLog;
    }

    @Override
    public void setForceLog(boolean isForceLog) {
        mIsForceLog = isForceLog;
    }

    protected boolean isLoggable(int priority) {
        return Log.isLoggable(mName, priority);
    }

    protected void formatAndLog(int priority, String format, Object... argArray) {
        if (isEnabled() && (isForceLog() || isLoggable(priority))) {
            FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
            logInternal(priority, ft.getMessage(), ft.getThrowable());
        }
    }

    protected void log(int priority, String msg, Throwable t) {
        if (isEnabled() && (isForceLog() || isLoggable(priority))) {
            logInternal(priority, msg, t);
        }
    }

    private void logInternal(int priority, String msg, Throwable t) {
        if (t != null) {
            msg += '\n' + Log.getStackTraceString(t);
        }
        Log.println(priority, mName, msg);
    }

    /**
     * 获取调用此方法的方法调用信息
     *
     * @param depth      调用此方法的方法级别是 0
     * @param separator  信息之间的分隔符
     * @param fileName   是否需要方法所在的文件名
     * @param lineNumber 是否需要方法所在的行号
     * @param className  是否需要方法所在的类名
     * @param threadName 是否需要方法所在的线程名
     * @return 多项数据的组合字符串
     */
    public String getCallerInfo(int depth, String separator, boolean fileName, boolean lineNumber, boolean className, boolean threadName) {
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        if (stes != null && stes.length > 3 + depth) {
            StackTraceElement ste = stes[3 + depth];
            StringBuilder result = new StringBuilder();
            result.append("[");
            if (fileName) {
                result.append(ste.getFileName()).append(separator);
            }
            result.append(ste.getMethodName()).append("()");
            if (lineNumber) {
                result.append(separator).append(ste.getLineNumber());
            }
            if (className) {
                result.append(separator).append(ste.getClassName());
            }
            if (threadName) {
                result.append(separator).append(Thread.currentThread().getName());
            }
            result.append("]");
            return result.toString();
        }
        return "";
    }
}
