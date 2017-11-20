package ch.unstable.hackforcoffee.di

import ch.unstable.hackforcoffee.ServerApp
import dagger.Component

@Component(modules = arrayOf(ServerModule::class))
interface HackForCoffeeServer {
    fun application(): ServerApp
}
