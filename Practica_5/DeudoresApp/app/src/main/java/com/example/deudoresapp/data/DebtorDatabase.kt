package com.example.deudoresapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.deudoresapp.data.dao.DebtorDao
import com.example.deudoresapp.data.dao.UserDao
import com.example.deudoresapp.data.entities.Debtor
import com.example.deudoresapp.data.entities.User


@Database(entities = [Debtor::class, User::class],  version = 1)
abstract class DebtorDatabase: RoomDatabase() {
    abstract fun DebtorDao(): DebtorDao
    abstract fun UserDao(): UserDao
}