package com.guilherme.rickandmortyapi.paging

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.guilherme.rickandmortyapi.model.Character
import com.guilherme.rickandmortyapi.network.RickandMortyRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
class PagingSource(private val name: String ?= "") :
    PagingSource<Int, Character>(), KoinComponent {

    private val repository: RickandMortyRepository by inject()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1
        return try {
            val response = repository.getSearchData(pageNumber, name)
            Log.d("CharacterViewModel", "response paging source is : $response")
            val pagedResponse = response.body()
            val data = pagedResponse?.results

            var nextPageNumber: Int? = null
            if (pagedResponse?.info?.next != null) {
                val uri = Uri.parse(pagedResponse.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
    }
}
