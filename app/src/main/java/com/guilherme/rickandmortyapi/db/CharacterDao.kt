package com.guilherme.rickandmortyapi.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import com.guilherme.rickandmortyapi.model.Character

@Database(entities = [Character::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun getAll(): List<Character>

    @Query("SELECT * FROM character WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Character>

    @Insert
    fun insertCharacter(characters: Character)

    @Delete
    fun delete(character: Character)

}

