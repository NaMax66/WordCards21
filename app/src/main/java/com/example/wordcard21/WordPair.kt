package com.example.wordcard21

import androidx.room.*

@Entity
data class WordPair(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "native_word") val nativeWord: String?,
    @ColumnInfo(name = "target_word") val targetWord: String?
)

@Dao
interface WordPairDao {
    @Query("SELECT * FROM wordpair")
    fun getAll(): List<WordPair>

    @Query("SELECT * FROM wordpair WHERE uid IN (:wordpairIds)")
    fun loadAllByIds(wordpairIds: IntArray): List<WordPair>

    @Query("SELECT * FROM wordpair WHERE native_word LIKE :native AND " +
            "target_word LIKE :target LIMIT 1")
    fun findByName(native: String, target: String): WordPair

    @Insert
    fun insertAll(vararg wordpair: WordPair)

    @Delete
    fun delete(wordpair: WordPair)
}

@Database(version = 1, entities = [WordPair::class] )
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordpairDao(): WordPairDao
}