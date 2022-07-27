package com.shubham.dependencyinjectiondemo

import dagger.Module
import dagger.Provides

@Module
class NCBatteryModule {

    @Provides
    fun providesNCBattery(nickelCadmiumBattery: NickelCadmiumBattery) : Battery {
//        return NickelCadmiumBattery() //We can do like it but it is unnecessary. because as we have already
        // annotated the constructor of the NickelCadmiumBattery with @Inject annotation, dagger can construct the dependency.
        // So we can take that dependency here as a function parameter & return it.
        return nickelCadmiumBattery
        // Dagger recognizes this returned dependency by considering the return type of the provider function.
        // So dagger will recognise this dependency as a battery.
    }

    // Now we need to add this module to the SmartPhoneComponent.kt s modules list

    // See the end of the 100th lesson again to learn the shorter way, not done that now cause of hurry on assigned task.

}