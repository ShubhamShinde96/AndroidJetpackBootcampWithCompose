package com.shubham.hiltdemo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// When we are using dagger, we listed all the modules belong to the component at the top of the component
// But now we include the component of the module at the top of the module with InstallIn annotation

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun providesDataSource(): DataSource {

        return DataSource()
    }


}