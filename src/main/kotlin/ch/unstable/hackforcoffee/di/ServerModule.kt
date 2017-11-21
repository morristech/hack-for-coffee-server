package ch.unstable.hackforcoffee.di

import dagger.Module

@Module(includes = arrayOf(JpaModule::class))
class ServerModule {

}
