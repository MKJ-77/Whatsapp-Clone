package com.mkj.whatsapp.data.local.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mkj.whatsapp.data.local.entity.ChatParticipantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ParticipantDao {

    // -------------------------------------------------
    // INSERT
    // -------------------------------------------------

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParticipant(participant: ChatParticipantEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParticipants(participants: List<ChatParticipantEntity>)


    // -------------------------------------------------
    // GET PARTICIPANTS OF A CHAT (Group Members)
    // -------------------------------------------------

    @Query(
        """
        SELECT * FROM chat_participants
        WHERE chatId = :chatId
    """
    )
    fun observeParticipants(chatId: String): Flow<List<ChatParticipantEntity>>


    @Query(
        """
        SELECT * FROM chat_participants
        WHERE chatId = :chatId
    """
    )
    suspend fun getParticipants(chatId: String): List<ChatParticipantEntity>


    // -------------------------------------------------
    // CHECK IF USER IS IN CHAT
    // -------------------------------------------------

    @Query(
        """
        SELECT EXISTS(
            SELECT 1 FROM chat_participants
            WHERE chatId = :chatId
            AND userId = :userId
        )
    """
    )
    suspend fun isUserInChat(
        chatId: String,
        userId: String
    ): Boolean


    // -------------------------------------------------
    // GET USER ROLE IN CHAT
    // -------------------------------------------------

    @Query(
        """
        SELECT role FROM chat_participants
        WHERE chatId = :chatId
        AND userId = :userId
    """
    )
    suspend fun getUserRole(
        chatId: String,
        userId: String
    ): String?


    // -------------------------------------------------
    // REMOVE USER FROM CHAT
    // -------------------------------------------------

    @Query(
        """
        DELETE FROM chat_participants
        WHERE chatId = :chatId
        AND userId = :userId
    """
    )
    suspend fun removeParticipant(
        chatId: String,
        userId: String
    )


    // -------------------------------------------------
    // DELETE ALL PARTICIPANTS OF A CHAT
    // -------------------------------------------------

    @Query(
        """
        DELETE FROM chat_participants
        WHERE chatId = :chatId
    """
    )
    suspend fun clearParticipants(chatId: String)
}
