package cn.sh.changxing.yuanyi.logger;

/**
 * Created by YuanZezhong on 2017/7/27.
 */
public abstract class AbstractNamedLogger implements ILogger {
    protected String mName;

    public AbstractNamedLogger(String name) {
        mName = name;
    }

    @Override
    public String getName() {
        return mName;
    }
}
