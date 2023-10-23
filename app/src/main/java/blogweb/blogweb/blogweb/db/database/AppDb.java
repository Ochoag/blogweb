package blogweb.blogweb.blogweb.db.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import blogweb.blogweb.blogweb.constants.Constants;
import blogweb.blogweb.blogweb.db.dao.UsersDAO;
import blogweb.blogweb.blogweb.db.dao.WebblogDAO;
import blogweb.blogweb.blogweb.db.entity.Users;
import blogweb.blogweb.blogweb.db.entity.Webblog;


@Database(entities = {Users.class, Webblog.class}, version = 3, exportSchema = false)
public abstract class AppDb extends RoomDatabase {

    public static AppDb INSTANCE;
    public abstract UsersDAO usersDAO();
    public abstract WebblogDAO webblogDAO();

    public static AppDb getAppDb(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDb.class, Constants.NAME_DATABASE)
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .addMigrations(MIGRATION_2_3)
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE webblog (idwebblog INTEGER PRIMARY KEY NOT NULL, titulo TEXT, autor TEXT, fechapublicacion TEXT, contenido TEXT, iduser INTEGER NOT NULL, foreign key (iduser) references users(iduser) ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE users (iduser INTEGER PRIMARY KEY NOT NULL, email TEXT, phn TEXT)");
        }
    };
}
