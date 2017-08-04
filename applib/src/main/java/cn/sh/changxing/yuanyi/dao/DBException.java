package cn.sh.changxing.yuanyi.dao;

/**
 * Created by YuanZezhong on 2017/8/4.
 *
 * 数据库相关操作的异常类
 */
public class DBException extends RuntimeException {
    public static final String CODE_INSERT = "INSERT";
    public static final String CODE_DELETE = "DELETE";
    public static final String CODE_UPDATE = "UPDATE";
    public static final String CODE_SELECT = "SELECT";
    public static final String CODE_OTHERS = "OTHERS";

    private String mCode;

    public DBException(String code, String message) {
        super(message);
        mCode = code;
    }

    public DBException(String code, String message, Throwable t) {
        super(message, t);
        mCode = code;
    }

    public DBException(String code, Throwable t) {
        super(t);
        mCode = code;
    }

    public String getCode() {
        return mCode;
    }
}
