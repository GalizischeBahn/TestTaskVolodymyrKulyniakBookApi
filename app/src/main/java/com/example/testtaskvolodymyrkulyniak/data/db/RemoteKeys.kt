
package com.example.android.codelabs.paging.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey val bookID: Long,
    val prevKey: Int?,
    val nextKey: Int?
)
