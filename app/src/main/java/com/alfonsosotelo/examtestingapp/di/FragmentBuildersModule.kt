package com.alfonsosotelo.examtestingapp.di

import com.alfonsosotelo.examtestingapp.ui.login.LoginFragment
import com.alfonsosotelo.examtestingapp.ui.register.RegisterFragment
import com.alfonsosotelo.examtestingapp.ui.users.UserListFragment
import com.alfonsosotelo.examtestingapp.ui.users.details.UserDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector
    abstract fun contributeUserListFragment(): UserListFragment

    @ContributesAndroidInjector
    abstract fun contributeUserDetailsFragment(): UserDetailsFragment
}