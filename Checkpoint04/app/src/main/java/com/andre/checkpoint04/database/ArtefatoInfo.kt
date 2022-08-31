package com.andre.checkpoint04.database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

const val ARTEFATO_INFO_TABLE_NAME = "artefato"
@Entity(tableName = ARTEFATO_INFO_TABLE_NAME)
@Parcelize
data class ArtefatoInfo(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @NonNull @ColumnInfo val nome: String,
    @NonNull @ColumnInfo val nomeSistemaEstelar: String,
    @NonNull @ColumnInfo val nomePlanetaLua: String,
    @NonNull @ColumnInfo val coordenadaX: Double,
    @NonNull @ColumnInfo val coordenadaY: Double,
): Parcelable
