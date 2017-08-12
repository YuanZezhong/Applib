package cn.sh.changxing.yuanyi.dao;

import android.content.Context;

import java.util.List;

import cn.sh.changxing.yuanyi.R;
import cn.sh.changxing.yuanyi.dao.bean.SongBean;
import cn.sh.changxing.yuanyi.logger.LoggerFactory;

/**
 * Created by yuanyi on 17-8-6.
 */

public class SongDaoAdapter extends BaseDaoAdapter {
    private static SongDaoAdapter sInstance = null;

    private SongDaoAdapter(IDBOperation dbOperation) {
        super(dbOperation);
    }

    public static SongDaoAdapter getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SongDaoAdapter(DBOperationImpl.newInstance(context, new SongCreator(context)));
        }
        return sInstance;
    }

    public void insertSong(int songId, String songName) {
        LoggerFactory.getDefault().d("{} in", LoggerFactory.getDefault().getCallerName());
        mDBOperation.insert(R.string.sql_download_insert, new Object[]{songId, songName});
        LoggerFactory.getDefault().d("{} out", LoggerFactory.getDefault().getCallerName());
    }

    public void getSongById(int songId) {
        LoggerFactory.getDefault().beginMethod();
        SongBean song = mDBOperation.queryToObject(SongBean.class, R.string.sql_download_get_song_by_id, new String[]{songId + ""});
        LoggerFactory.getDefault().d("selected song is {}", song);
        LoggerFactory.getDefault().d("{} out", LoggerFactory.getDefault().getCallerName());
    }

    public void getAllSong() {
        LoggerFactory.getDefault().beginMethod();
        List<SongBean> songList = mDBOperation.queryToList(SongBean.class, R.string.sql_download_get_song_list, null);
        if (songList != null) {
            for (SongBean songBean : songList) {
                LoggerFactory.getDefault().d("selected song is {}", songBean);
            }
        }
        LoggerFactory.getDefault().endMethod();
    }
}
