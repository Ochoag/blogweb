package blogweb.blogweb.blogweb.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import blogweb.blogweb.blogweb.db.entity.Webblog;


@Dao
public interface WebblogDAO {
    @Insert
    long insert(Webblog webblog);

    @Query("SELECT * FROM webblog WHERE iduser=:iduser")
    List<Webblog> findBlogs(int iduser);
}
