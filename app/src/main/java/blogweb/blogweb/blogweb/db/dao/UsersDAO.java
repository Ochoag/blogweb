package blogweb.blogweb.blogweb.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import blogweb.blogweb.blogweb.Objects.IDUser;
import blogweb.blogweb.blogweb.db.entity.Users;
import blogweb.blogweb.blogweb.db.entity.Webblog;


@Dao
public interface UsersDAO {
    @Insert
    long insert(Users users);

    @Query("SELECT * FROM users WHERE email=:email AND phn=:phn")
    List<Users> findUser(String email, String phn);
}
