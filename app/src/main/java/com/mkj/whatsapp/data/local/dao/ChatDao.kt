package com.mkj.whatsapp.data.local.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mkj.whatsapp.data.local.entity.ChatEntity
import com.mkj.whatsapp.data.local.model.ChatListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    // -------------------------------------------------
    // INSERT / UPDATE
    // -------------------------------------------------

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat(chat: ChatEntity)

    @Update
    suspend fun updateChat(chat: ChatEntity)

    @Delete
    suspend fun deleteChat(chat: ChatEntity)


    // -------------------------------------------------
    // UPDATE LAST MESSAGE
    // -------------------------------------------------

    @Query("UPDATE chats SET lastMessageId = :messageId WHERE chatId = :chatId")
    suspend fun updateLastMessage(
        chatId: String,
        messageId: String
    )



    // -------------------------------------------------
    // CHAT LIST WITH LAST MESSAGE + UNREAD COUNT
    // -------------------------------------------------

    @Query(
        """
        SELECT 
            c.chatId AS chatId,
            c.title AS title,
            c.isGroup AS isGroup,
            m.content AS lastMessage,
            m.timestamp AS lastMessageTime,
            
            (
                SELECT COUNT(*) 
                FROM messages 
                WHERE chatId = c.chatId
                AND senderId != :currentUserId
                AND status != 'SEEN'
            ) AS unreadCount
            
        FROM chats c
        
        LEFT JOIN messages m 
        ON m.messageId = c.lastMessageId
        
        ORDER BY m.timestamp DESC
    """
    )
    fun observeChatList(
        currentUserId: String
    ): Flow<List<ChatListItem>>



    // -------------------------------------------------
    // GET SINGLE CHAT
    // -------------------------------------------------

    @Query("SELECT * FROM chats WHERE chatId = :chatId")
    suspend fun getChatById(chatId: String): ChatEntity?
}
