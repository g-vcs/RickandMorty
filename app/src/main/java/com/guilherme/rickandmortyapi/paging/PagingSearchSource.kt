/*
package com.guilherme.rickandmortyapi.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.guilherme.rickandmortyapi.model.Character
import com.guilherme.rickandmortyapi.network.RickandMortyRepository

class PagingSearchSource(private val repository: RickandMortyRepository, private val name: String) :
    PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1
        return try {
            val response = repository.getSearchData(pageNumber, name)

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
*/
