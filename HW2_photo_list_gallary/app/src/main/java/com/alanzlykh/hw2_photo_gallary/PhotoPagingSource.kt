package com.alanzlykh.hw2_photo_gallary

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PhotoPagingSource(
    private val photoRepository: PhotoRepository,
    private val maxLoadSize: Int,
    private val startPage: Int
): PagingSource<Int, GalleryItem>() {
    override fun getRefreshKey(state: PagingState<Int, GalleryItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItem> {
        val page = params.key ?: startPage
        val pageSize = params.loadSize.coerceAtMost(maxLoadSize)
        try {
            val photos = photoRepository.fetchPhotos(page, pageSize)
            val nextKey = if (photos.size < pageSize) null else page + 1
            val prevKey = if (page == startPage) null else page - 1
            return LoadResult.Page(photos, prevKey, nextKey)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}