package com.andre.checkpoint04.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface ArtefatoInfoDAO {
    @Query("SELECT * FROM $ARTEFATO_INFO_TABLE_NAME ORDER BY nome ASC")
    fun getAll(): List<ArtefatoInfo>

    @Insert
    fun insert(vararg artefatoInfo: ArtefatoInfo)

    @Delete
    fun delete(artefatoInfo: ArtefatoInfo)

    @Update
    fun update(artefatoInfo: ArtefatoInfo)
}