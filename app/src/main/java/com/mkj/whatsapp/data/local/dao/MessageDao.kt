package com.mkj.whatsapp.data.local.dao


import androidx.room.*
import com.mkj.whatsapp.data.local.entity.MessageEntity
import com.mkj.whatsapp.data.local.entity.MessageStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    // -------------------------------------------------
    // INSERT
    // -------------------------------------------------

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: MessageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<MessageEntity>)




    // -------------------------------------------------
    // PAGINATION SUPPORT (Future)
    // -------------------------------------------------

    @Query("""
        SELECT * FROM messages
        WHERE chatId = :chatId
        ORDER BY timestamp DESC
        LIMIT :limit OFFSET :offset
    """)
    suspend fun getMessagesPaged(
        chatId: String,
        limit: Int,
        offset: Int
    ): List<MessageEntity>


    // -------------------------------------------------
    // UPDATE STATUS
    // -------------------------------------------------

    @Query("""
        UPDATE messages
        SET status = :status
        WHERE messageId = :messageId
    """)
    suspend fun updateMessageStatus(
        messageId: String,
        status: String
    )


    // -------------------------------------------------
    // MARK CHAT AS SEEN
    // -------------------------------------------------

    @Query("""
        UPDATE messages
        SET status = 'SEEN'
        WHERE chatId = :chatId
        AND senderId != :currentUserId
        AND status != 'SEEN'
    """)
    suspend fun markChatAsSeen(
        chatId: String,
        currentUserId: String
    )


    // -------------------------------------------------
    // DELETE MESSAGE
    // -------------------------------------------------

    @Query("DELETE FROM messages WHERE messageId = :messageId")
    suspend fun deleteMessage(messageId: String)


    // -------------------------------------------------
    // CLEAR CHAT
    // -------------------------------------------------

    @Query("DELETE FROM messages WHERE chatId = :chatId")
    suspend fun clearChat(chatId: String)


    // -------------------------------------------------
    // UNREAD COUNT (Used by ChatDao if needed)
    // -------------------------------------------------

    @Query("""
        SELECT COUNT(*) FROM messages
        WHERE chatId = :chatId
        AND senderId != :currentUserId
        AND status != 'SEEN'
    """)
    suspend fun getUnreadCount(
        chatId: String,
        currentUserId: String
    ): Int

    @Query("""
    UPDATE messages 
    SET status = :status 
    WHERE messageId = :messageId
""")
    suspend fun updateStatus(
        messageId: String,
        status: MessageStatus
    )


    @Query("""
    SELECT * FROM messages 
    WHERE chatId = :chatId
    ORDER BY timestamp ASC
""")
    fun observeMessages(chatId: String): Flow<List<MessageEntity>>

}
