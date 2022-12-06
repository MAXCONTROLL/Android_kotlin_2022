package com.alanzlykh.hw2_photo_gallary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alanzlykh.hw2_photo_gallary.api.FlickrApiObj
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class PhotoGalleryViewModel: ViewModel() {
    private val photoRepository = PhotoRepository(FlickrApiObj.api)
    val photoItems: StateFlow<PagingData<GalleryItem>> =
        Pager(PagingConfig(pageSize = 10)) {
            PhotoPagingSource(photoRepository, maxLoadSize = 100, startPage = 0)
        }.flow
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}