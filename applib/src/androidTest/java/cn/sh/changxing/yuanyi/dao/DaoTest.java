package cn.sh.changxing.yuanyi.dao;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by yuanyi on 17-8-5.
 */

@RunWith(AndroidJUnit4.class)
public class DaoTest {

    @Test
    public void testDBCreator() {
        SongDaoAdapter dao = SongDaoAdapter.getInstance(InstrumentationRegistry.getContext());
        dao.open(true);
        dao.insertSong(1, "retry");
        dao.insertSong(2, "two");
        dao.insertSong(3, "three");
        dao.insertSong(4, "four");
        dao.insertSong(5, "five");

        dao.getAllSong();
    }
}
