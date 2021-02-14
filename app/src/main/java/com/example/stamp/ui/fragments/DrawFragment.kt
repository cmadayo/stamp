package com.example.stamp.ui.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.stamp.R
import com.example.stamp.databinding.FragmentDrawBinding
import com.example.stamp.viewmodels.DrawViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrawFragment : Fragment() {
    /**
     * Memo:ViewModelを遅延初期化。onActivityCreated前に参照することはNG
     */
    private val viewModel: DrawViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDrawBinding>(
            inflater,
            R.layout.fragment_draw,
            container, false
        )

        viewModel.clickedSavedButton.observe(viewLifecycleOwner, Observer {
            val bitmap : Bitmap = binding.drawView.bitmap()
            viewModel.addStamp(bitmap)
        })

        binding.viewModel = viewModel

        return binding.root
    }
}