package blogweb.blogweb.blogweb.Api

import blogweb.blogweb.blogweb.Activities.LoginApp
import blogweb.blogweb.blogweb.Activities.LoginApp2
import blogweb.blogweb.blogweb.Activities.MainActivity
import blogweb.blogweb.blogweb.Fragments.BlankFragment
import blogweb.blogweb.blogweb.Fragments.BlankFragment2
import blogweb.blogweb.blogweb.Fragments.BlankFragment3
import blogweb.blogweb.blogweb.Fragments.BlankFragmentOFFLINE
import blogweb.blogweb.blogweb.Service.Service
import dagger.Component
import jseets.jseets.jseets.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, Service::class])
interface ApiComponent {
    fun inject(fragment: LoginApp?)
    fun inject(fragment: LoginApp2?)
    fun inject(fragment: MainActivity?)
    fun inject(fragment: BlankFragment?)
    fun inject(fragment: BlankFragment2?)
    fun inject(fragment: BlankFragment3?)
    fun inject(fragment: BlankFragmentOFFLINE?)
}

