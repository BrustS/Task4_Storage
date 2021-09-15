package by.brust.animalslist.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "animal_table")
data class Animal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name="name")
    val name: String,
    @ColumnInfo(name="age")
    val age : Int,
    @ColumnInfo(name="breed")
    val breed: String
) : Parcelable
