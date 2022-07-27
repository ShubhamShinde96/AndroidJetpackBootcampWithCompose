package com.shubham.dependencyinjectiondemo

import android.util.Log
import dagger.Module
import dagger.Provides

// To make it dagger module we need to annotate it with Module annotation
@Module
class MemoryCardModule(val memorySize: Int) {

    // Lesson 102: Now this MemoryCardModule has state

    // Now we're creating a provider function with the return type of MemoryCard.

    // we can name it anything but we usually start with provides as in providesMemoryCard
    @Provides // We need to annotate it with @Provides annotation. otherwise dagger will not recognise it as a provider function.
    fun providesMemoryCard() : MemoryCard {
        Log.i("MYTAG", "Size of the memory card is $memorySize") // Let's rebuild the project now.
        return MemoryCard()
    }

    // We basically use dagger modules to group similar types of provider functions together.
    // This Module class has only one provider function. But a module can have more than one provider functions.
    // The module should not have instance variables, because that can lead to a unpredictable behaviour.
    //  You should create module when you actually need it.

}