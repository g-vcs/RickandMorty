package com.guilherme.rickandmortyapi.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.guilherme.rickandmortyapi.network.Character
import com.guilherme.rickandmortyapi.network.CharacterResponse
import com.guilherme.rickandmortyapi.network.RickandMortyRepository

class PagingSource : PagingSource<Int, CharacterResponse>() {

    private val repository = RickandMortyRepository()

    override fun getRefreshKey(state: PagingState<Int, CharacterResponse>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterResponse> {
        try {
            val nextPageNumber = params.key ?: 1 // Initial page number is 1
            val response = repository.getData(nextPageNumber)

            return LoadResult.Page(
                data = response.data,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (response.hasMore) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}