package blogweb.blogweb.blogweb.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import blogweb.blogweb.blogweb.constants.Constants;

@Entity(tableName = Constants.NAME_TABLE_USERS)
public class Users {
    @PrimaryKey(autoGenerate = true)
    public int iduser;
    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "phn")
    public String phn;

    public int getId() {
        return iduser;
    }

    public void setId(int id) {
        this.iduser = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getPhn() {
        return phn;
    }
}
