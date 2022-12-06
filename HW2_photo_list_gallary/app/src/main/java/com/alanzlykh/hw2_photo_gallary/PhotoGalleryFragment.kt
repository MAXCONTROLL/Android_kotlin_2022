package com.alanzlykh.hw2_photo_gallary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.alanzlykh.hw2_photo_gallary.databinding.FragmentPhotoGalleryBinding
import kotlinx.coroutines.launch

class PhotoGalleryFragment : Fragment() {
    private var _binding: FragmentPhotoGalleryBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }
    private val pagingAdapter = PhotoListAdapter(PhotoComparator)
    private val photoListViewModel: PhotoGalleryViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoGalleryBinding.inflate(inflater, container, false)
        binding.rvPhotoList.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter(pagingAdapter, binding)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                photoListViewModel.photoItems.collect {
                    pagingAdapter.submitData(it)
                    binding.rvPhotoList.adapter = pagingAdapter.withLoadStateHeaderAndFooter(
                        footer = PhotoLoadStateAdapter{ pagingAdapter.retry() },
                        header = PhotoLoadStateAdapter{ pagingAdapter.retry() })
                }
            }
        }
    }

    private fun initAdapter(
        adapter: PhotoListAdapter,
        binding: FragmentPhotoGalleryBinding
    ) {
        binding.btnRetry.setOnClickListener { adapter.retry() }
        adapter.addLoadStateListener { state: CombinedLoadStates ->
            binding.apply {
                rvPhotoList.isVisible = state.refresh != LoadState.Loading
                pbLarge.isVisible = state.refresh == LoadState.Loading
                btnRetry.isVisible = state.refresh is LoadState.Error
                tvErrorMessage.isVisible = state.refresh is LoadState.Error
                if (state.refresh is LoadState.Error){
                    tvErrorMessage.setText(getErrorMessage(state.refresh as LoadState.Error))
                }
            }
        }
    }
    private fun getErrorMessage(refresh: LoadState.Error): Int {
        return when (refresh.error.message) {
            Consts.ioeExceptionFlag -> R.string.bad_internet_error_massage
            Consts.httpExceptionFlag -> R.string.bad_http_code_error_massage
            else -> R.string.default_error_message
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}