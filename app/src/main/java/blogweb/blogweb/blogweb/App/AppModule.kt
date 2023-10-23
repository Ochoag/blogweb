package jseets.jseets.jseets

import android.app.Application
import android.content.Context
import blogweb.blogweb.blogweb.Activities.LoginApp
import blogweb.blogweb.blogweb.db.database.AppDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class AppModule(private val mApplication: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @Singleton
    fun provideLoginApp(): LoginApp {
        return LoginApp()
    }

    @Provides
    @Singleton
    fun provideAppDb(context: Context): AppDb {
        return AppDb.getAppDb(context)
    }

}
