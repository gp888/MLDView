package com.chain.app_version1.component

import com.chain.app_version1.Main2Activity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [Main2ActivitySubcomponent::class])
abstract class Main2ActivityModule{
    @Binds
    @IntoMap
    @ClassKey(Main2Activity::class)
    internal abstract fun bindMainActivityInjectorFactory(builder: Main2ActivitySubcomponent.Builder): AndroidInjector.Factory<*>
}