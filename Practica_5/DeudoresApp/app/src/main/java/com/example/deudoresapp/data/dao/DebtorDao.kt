package com.example.deudoresapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.deudoresapp.data.entities.Debtor

@Dao
interface DebtorDao {

    @Insert
    fun createDebtor(debtor: Debtor)

    @Query("SELECT * FROM table_debtor")
    fun getDebtors() : MutableList<Debtor>

}