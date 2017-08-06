package cn.sh.changxing.yuanyi.dao.bean;

/**
 * Created by yuanyi on 17-8-6.
 */

public class SongBean {
    private int songId;
    private String songName;

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    @Override
    public String toString() {
        return "SongBean{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                '}';
    }
}
