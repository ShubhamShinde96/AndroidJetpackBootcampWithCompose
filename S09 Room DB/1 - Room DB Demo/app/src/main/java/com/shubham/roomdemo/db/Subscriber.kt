package com.shubham.roomdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Here we can mention the name we want Room to give this table corresponds to this data class
@Entity(tableName = "subscriber_data_table") // If we do not give name here then Room will use classname as a table name.
data class Subscriber(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subscriber_id")
    val id: Int,

    @ColumnInfo(name = "subscriber_name")
    var name: String,

    @ColumnInfo(name = "subscriber_email")
    var email: String

    )

// @ColumnInfo(name = "subscriber_id") is used to change the default column name to your custom name, this is same
// like annotating name with entity table class.