package ch.unstable.hackforcoffee.di

import ch.unstable.hackforcoffee.ServerApp
import com.google.gson.Gson
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ServerModule::class))
interface HackForCoffeeServer {

    fun application(): ServerApp
}
