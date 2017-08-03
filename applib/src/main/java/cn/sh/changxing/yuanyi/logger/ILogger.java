package cn.sh.changxing.yuanyi.logger;

/**
 * Created by YuanZezhong on 2017/7/26.
 * <p>
 * 定义Logger基本的功能
 */
public interface ILogger {
    /**
     * 获取Logger实例的TAG名称
     *
     * @return tag名称
     */
    public String getName();

    /**
     * trace级别的LOG是否开启
     *
     * @return true开启, false未开启
     */
    public boolean isTraceEnabled();

    /**
     * 输出trace级别的LOG
     *
     * @param msg 输出LOG的内容
     */
    public ILogger trace(String msg);

    /**
     * 输出trace级别的LOG
     *
     * @param format 格式化字符串
     * @param args   格式化参数
     */
    public ILogger trace(String format, Object... args);

    /**
     * 输出trace级别的LOG
     *
     * @param msg 输出LOG的内容
     * @param t   附加的异常信息
     */
    public ILogger trace(String msg, Throwable t);

    /**
     * 输出trace级别的LOG，并增加标识开始的前后缀
     *
     * @param msg 输出的LOG内容
     */
    public ILogger traceIn(String msg);

    /**
     * 输出trace级别的LOG，并增加标识结束的前后缀
     *
     * @param msg 输出的LOG内容
     */
    public ILogger traceOut(String msg);

    /**
     * 输出trace级别的方法调用LOG
     */
    public ILogger beginMethod();

    /**
     * 输出trace级别的方法调用LOG
     */
    public ILogger endMethod();

    /**
     * debug级别的LOG是否开启
     *
     * @return true开启, false未开启
     */
    public boolean isDebugEnabled();

    /**
     * 输出debug级别的LOG
     *
     * @param msg 输出的LOG内容
     */
    public ILogger d(String msg);

    /**
     * 输出debug级别的LOG
     *
     * @param format 格式化字符串
     * @param args   格式化参数
     */
    public ILogger d(String format, Object... args);

    /**
     * 输出debug级别的LOG
     *
     * @param msg 输出LOG的内容
     * @param t   附加的异常信息
     */
    public ILogger d(String msg, Throwable t);

    /**
     * info级别的LOG是否开启
     *
     * @return true开启, false未开启
     */
    public boolean isInfoEnabled();

    /**
     * 输出info级别的LOG
     *
     * @param msg 输出LOG的内容
     */
    public ILogger i(String msg);

    /**
     * 输出info级别的LOG
     *
     * @param format 格式化字符串
     * @param args   格式化参数
     */
    public ILogger i(String format, Object... args);

    /**
     * 输出info级别的LOG
     *
     * @param msg 输出LOG的内容
     * @param t   附加的异常信息
     */
    public ILogger i(String msg, Throwable t);

    /**
     * warn级别的LOG是否开启
     *
     * @return true开启, false未开启
     */
    public boolean isWarnEnabled();

    /**
     * 输出warn级别的LOG
     *
     * @param msg 输出LOG的内容
     */
    public ILogger w(String msg);

    /**
     * 输出warn级别的LOG
     *
     * @param format 格式化字符串
     * @param args   格式化参数
     */
    public ILogger w(String format, Object... args);

    /**
     * 输出warn级别的LOG
     *
     * @param msg 输出LOG的内容
     * @param t   附加的异常信息
     */
    public ILogger w(String msg, Throwable t);

    /**
     * error级别的LOG是否开启
     *
     * @return true开启, false未开启
     */
    public boolean isErrorEnabled();

    /**
     * 输出error级别的LOG
     *
     * @param msg 输出LOG的内容
     */
    public ILogger e(String msg);

    /**
     * 输出error级别的LOG
     *
     * @param format 格式化字符串
     * @param args   格式化参数
     */
    public ILogger e(String format, Object... args);

    /**
     * 输出error级别的LOG
     *
     * @param msg 输出LOG的内容
     * @param t   附加的异常信息
     */
    public ILogger e(String msg, Throwable t);

    /**
     * trace级别输出方法调用的堆栈信息
     */
    public ILogger printStackTrace();

    /**
     * log功能是否开启
     *
     * @return true开启, false未开启
     */
    public boolean isEnabled();

    /**
     * 打开或关闭log功能
     *
     * @param isEnabled true打开, false关闭
     */
    public ILogger setEnabled(boolean isEnabled);

    /**
     * 是否忽略设备的默认LOG级别限制，强制输出LOG
     *
     * @return true:强制输出, false:不强制
     */
    public boolean isForceLog();

    /**
     * 打开或关闭强制输出LOG的功能
     *
     * @param isForceLog true打开, false关闭
     */
    public ILogger setForceLog(boolean isForceLog);
}
