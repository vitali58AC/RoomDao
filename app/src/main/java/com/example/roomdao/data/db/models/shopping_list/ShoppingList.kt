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
    val boughtAt: Instant?,
    @ColumnInfo(name = ShoppingListContract.Columns.PRICE)
    val price: Double?,
    @ColumnInfo(name = ShoppingListContract.Columns.STATUS)
    val status: StatusShoppingList
)

object ShoppingListExample {
    val list = listOf(
        ShoppingList(
            id = 0,
            title = "Shopping list #1",
            description = "This is first shopping list",
            createAt = Instant.now(),
            boughtAt = null,
            price = null,
            status = StatusShoppingList.CREATED
        ),
        ShoppingList(
            id = 0,
            title = "Shopping list #2",
            description = "This is second shopping list",
            createAt = Instant.now(),
            boughtAt = null,
            price = null,
            status = StatusShoppingList.ACTIVE
        ),
        ShoppingList(
            id = 0,
            title = "Shopping list #3",
            description = "This is third shopping list",
            createAt = Instant.now(),
            boughtAt = null,
            price = null,
            status = StatusShoppingList.CREATED
        ),
        ShoppingList(
            id = 0,
            title = "Shopping list #4",
            description = "This is fourth shopping list",
            createAt = Instant.now(),
            boughtAt = null,
            price = null,
            status = StatusShoppingList.ACTIVE
        ),
        ShoppingList(
            id = 0,
            title = "Shopping list #5",
            description = "This is fifth shopping list",
            createAt = Instant.now(),
            boughtAt = null,
            price = null,
            status = StatusShoppingList.BOUGHT
        ),
        ShoppingList(
            id = 0,
            title = "Shopping list #6",
            description = "This is sixth shopping list",
            createAt = Instant.now(),
            boughtAt = null,
            price = null,
            status = StatusShoppingList.CLOSED
        ),
        ShoppingList(
            id = 0,
            title = "Shopping list #7",
            description = "This is seventh shopping list",
            createAt = Instant.now(),
            boughtAt = null,
            price = null,
            status = StatusShoppingList.CLOSED
        ),
        ShoppingList(
            id = 0,
            title = "Shopping list #8",
            description = "This is eighth shopping list",
            createAt = Instant.now(),
            boughtAt = null,
            price = null,
            status = StatusShoppingList.CLOSED
        ),
        ShoppingList(
            id = 0,
            title = "Shopping list #9",
            description = "This is ninth shopping list",
            createAt = Instant.now(),
            boughtAt = null,
            price = null,
            status = StatusShoppingList.CLOSED
        ),
        ShoppingList(
            id = 0,
            title = "Shopping list #10",
            description = "This is tenth shopping list",
            createAt = Instant.now(),
            boughtAt = null,
            price = null,
            status = StatusShoppingList.CLOSED
        ),
        ShoppingList(
            id = 0,
            title = "Shopping list #11",
            description = "This is eleventh shopping list",
            createAt = Instant.now(),
            boughtAt = null,
            price = null,
            status = StatusShoppingList.CLOSED
        )
    )
}