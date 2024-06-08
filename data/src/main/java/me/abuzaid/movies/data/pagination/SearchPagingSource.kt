package me.abuzaid.movies.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.abuzaid.movies.data.network.models.mappers.toMovieModelList
import me.abuzaid.movies.data.network.models.mappers.toSearchModelList
import me.abuzaid.movies.data.network.services.ApiServices
import me.abuzaid.movies.domain.models.SearchModel
import me.abuzaid.movies.domain.models.wrappers.CallFailure
import me.abuzaid.movies.domain.models.wrappers.ErrorModel

/**
 * Created by "Mohamad Abuzaid" on 08/06/2024.
 * Email: m.abuzaid.ali@gmail.com
 */
class SearchPagingSource(
    private val apiServices: ApiServices,
    private val query: String,
    private val lang: String
) : PagingSource<Int, SearchModel>() {

    override fun getRefreshKey(state: PagingState<Int, SearchModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchModel> {
        return try {
            val page = params.key ?: 1

            val response = apiServices.search(query, lang, page)
            val nextKey = if (response.results.isNullOrEmpty()) null else page.plus(1)

            response.results?.let {
                LoadResult.Page(
                    data = response.results.toSearchModelList(),
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = nextKey,
                )
            } ?: run {
                LoadResult.Error(
                    CallFailure(
                        ErrorModel(
                            code = response.statusCode ?: -1,
                            errorMessage = response.statusMessage ?: ""
                        )
                    )
                )
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}