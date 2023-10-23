package blogweb.blogweb.blogweb.App;

import androidx.multidex.MultiDexApplication;
import blogweb.blogweb.blogweb.Api.ApiComponent;
import blogweb.blogweb.blogweb.Api.DaggerApiComponent;


public class BaseApplication extends MultiDexApplication {

    private ApiComponent appComponent = DaggerApiComponent.create();

    public ApiComponent getAppComponent() {
        return appComponent;
    }
}
