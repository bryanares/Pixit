package com.brian.pixit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.brian.pixit.R
import com.brian.pixit.adapters.OnClickListener
import com.brian.pixit.adapters.PhotoListAdapter
import com.brian.pixit.databinding.FragmentPhotoListBinding
import com.brian.pixit.viewmodels.PhotoViewModel

class PhotoListFragment : Fragment() {

    private val viewModel: PhotoViewModel by activityViewModels()
    private lateinit var adapter: PhotoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentPhotoListBinding.inflate(inflater, container, false)

        viewModel.getApiResponse()
        adapter = PhotoListAdapter(OnClickListener {  })
        viewModel.response.observe(this.viewLifecycleOwner, Observer { photo ->
            val list = photo
            adapter.submitList(list)
            binding.pixitRecyclerView.adapter = adapter
        } )

        viewModel.loading.observe(this.viewLifecycleOwner, Observer{  loading ->
            binding.progressBar.isVisible = loading
        })

        val view = binding.root
        return view
    }
}