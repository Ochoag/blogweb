package blogweb.blogweb.blogweb.db

import android.content.Context
import androidx.room.Room
import blogweb.blogweb.blogweb.db.dao.UsersDAO
import blogweb.blogweb.blogweb.db.dao.WebblogDAO
import blogweb.blogweb.blogweb.db.database.AppDb
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


@Module
class DatabaseModule @Inject constructor()  {
    @Provides
    @Singleton
    fun provideAppDatabase(context: Context?): AppDb {
        return Room.databaseBuilder(context!!, AppDb::class.java, "webblog").build()
    }

    @Provides
    @Singleton
    fun provideUsersDAO(appDatabase: AppDb): UsersDAO {
        return appDatabase.usersDAO()
    }

    @Provides
    @Singleton
    fun provideWebBlogDAO(appDatabase: AppDb): WebblogDAO {
        return appDatabase.webblogDAO()
    }
}
