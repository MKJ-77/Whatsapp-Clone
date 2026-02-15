package com.mkj.whatsapp.data.local.dao

import androidx.room.*
import com.mkj.whatsapp.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    // --------------------------------------------------
    // INSERT / UPDATE
    // --------------------------------------------------

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Update
    suspend fun updateUser(user: UserEntity)

    // --------------------------------------------------
    // READ
    // --------------------------------------------------

    @Query("SELECT * FROM users WHERE userId = :userId LIMIT 1")
    fun observeUser(userId: String): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE userId = :userId LIMIT 1")
    suspend fun getUser(userId: String): UserEntity?

    @Query("SELECT * FROM users ORDER BY name ASC")
    fun observeAllUsers(): Flow<List<UserEntity>>

    // --------------------------------------------------
    // SEARCH
    // --------------------------------------------------

    @Query("""
        SELECT * FROM users 
        WHERE name LIKE '%' || :query || '%' 
        OR phone LIKE '%' || :query || '%'
        ORDER BY name ASC
    """)
    fun searchUsers(query: String): Flow<List<UserEntity>>

    // --------------------------------------------------
    // STATUS UPDATES
    // --------------------------------------------------

    @Query("""
        UPDATE users 
        SET isOnline = :isOnline,
            lastSeen = :lastSeen
        WHERE userId = :userId
    """)
    suspend fun updateOnlineStatus(
        userId: String,
        isOnline: Boolean,
        lastSeen: Long
    )

    @Query("""
        UPDATE users 
        SET profilePicture = :url
        WHERE userId = :userId
    """)
    suspend fun updateProfilePicture(
        userId: String,
        url: String?
    )

    @Query("""
        UPDATE users 
        SET about = :about
        WHERE userId = :userId
    """)
    suspend fun updateAbout(
        userId: String,
        about: String?
    )

    // --------------------------------------------------
    // DELETE
    // --------------------------------------------------

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}
