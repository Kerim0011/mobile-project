package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.dao.CarDao
import com.example.myapplication.dao.CustomerDao
import com.example.myapplication.dao.RentalDao
import com.example.myapplication.repository.CarRepository
import com.example.myapplication.repository.CustomerRepository
import com.example.myapplication.repository.RentalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideCarDao(db: AppDatabase): CarDao = db.carDao()

    @Provides
    fun provideCustomerDao(db: AppDatabase): CustomerDao = db.customerDao()

    @Provides
    fun provideRentalDao(db: AppDatabase): RentalDao = db.rentalDao()

    @Provides
    @Singleton
    fun provideCarRepository(
        carDao: CarDao,
        rentalDao: RentalDao
    ): CarRepository = CarRepository(carDao, rentalDao)

    @Provides
    fun provideCustomerRepository(dao: CustomerDao): CustomerRepository = CustomerRepository(dao)

    @Provides
    fun provideRentalRepository(dao: RentalDao): RentalRepository = RentalRepository(dao)
}
