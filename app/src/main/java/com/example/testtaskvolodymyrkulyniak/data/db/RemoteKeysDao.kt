

package com.example.android.codelabs.paging.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.UUID

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE bookId = :bookId")
     fun remoteKeysBookId(bookId: Long): RemoteKeys?

    @Query("DELETE FROM remote_keys")
     fun clearRemoteKeys()
}
