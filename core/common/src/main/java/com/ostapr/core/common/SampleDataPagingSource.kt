package com.ostapr.core.common

import androidx.paging.PagingSource
import androidx.paging.PagingState

class SampleDataPagingSource<T: Any>(private val data: List<T>) : PagingSource<Int, T>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val position = params.key ?: 0

        // Calculate the range of items to load
        val start = position * params.loadSize
        val end = start + params.loadSize

        // Extract the sublist based on the range
        val itemsInRange = data.subList(start, end)

        val prevKey = if (start > 0) position - 1 else null
        val nextKey = if (end < data.size) position + 1 else null

        // Return the loaded data as LoadResult
        return LoadResult.Page(
            data = itemsInRange,
            prevKey = prevKey,
            nextKey = nextKey
        )
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        // We don't need a refresh key for sample data since it won't change
        return null
    }
}