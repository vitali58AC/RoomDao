package com.example.roomdao.data.db.models.shopping_list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.roomdao.data.db.models.TimeConverter
import com.example.roomdao.data.db.models.status.StatusConverter
import com.example.roomdao.data.db.models.status.StatusShoppingList
import java.time.Instant

//Можно повесить конвертер на всю базу данных(абстрактный класс Database)
//если нужно использовать конвертер в разных таблицах
@TypeConverters(StatusConverter::class, TimeConverter::class)
@Entity(tableName = ShoppingListContract.TABLE_NAME)
data class ShoppingList(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ShoppingListContract.Columns.SHOPPING_LIST_ID)
    val id: Long,
    @ColumnInfo(name = ShoppingListContract.Columns.TITLE)
    val title: String,
    @ColumnInfo(name = ShoppingListContract.Columns.DESCRIPTION)
    val description: String,
    @ColumnInfo(name = ShoppingListContract.Columns.CREATE_AT)
    val createAt: Instant,
    @ColumnInfo(name = ShoppingListContract.Columns.BOUGHT_AT)
    val bought: Instant,
    @ColumnInfo(name = ShoppingListContract.Columns.PRICE)
    val price: Double,
    @ColumnInfo(name = ShoppingListContract.Columns.STATUS)
    val status: StatusShoppingList
)
