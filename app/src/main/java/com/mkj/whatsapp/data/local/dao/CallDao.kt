package com.mkj.whatsapp.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mkj.whatsapp.data.local.entity.CallEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CallDao {

    // -------------------------------------------------
    // INSERT
    // -------------------------------------------------

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCall(call: CallEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalls(calls: List<CallEntity>)


    // -------------------------------------------------
    // GET ALL CALLS (Recent Calls Screen)
    // -------------------------------------------------

    @Query(
        """
        SELECT * FROM calls
        ORDER BY timestamp DESC
    """
    )
    fun observeAllCalls(): Flow<List<CallEntity>>


    @Query(
        """
        SELECT * FROM calls
        ORDER BY timestamp DESC
    """
    )
    suspend fun getAllCalls(): List<CallEntity>


    // -------------------------------------------------
    // GET CALLS FOR A SPECIFIC CHAT
    // -------------------------------------------------

    @Query(
        """
        SELECT * FROM calls
        WHERE chatId = :chatId
        ORDER BY timestamp DESC
    """
    )
    fun observeCallsByChat(chatId: String): Flow<List<CallEntity>>


    // -------------------------------------------------
    // GET MISSED CALLS
    // -------------------------------------------------

    @Query(
        """
        SELECT * FROM calls
        WHERE status = 'MISSED'
        ORDER BY timestamp DESC
    """
    )
    fun observeMissedCalls(): Flow<List<CallEntity>>


    // -------------------------------------------------
    // DELETE
    // -------------------------------------------------

    @Query(
        """
        DELETE FROM calls
        WHERE callId = :callId
    """
    )
    suspend fun deleteCall(callId: String)


    @Query(
        """
        DELETE FROM calls
    """
    )
    suspend fun clearAllCalls()
}
