package com.example.stamp.ui.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.stamp.R
import com.example.stamp.databinding.FragmentDrawBinding
import com.example.stamp.modules.EventObserver
import com.example.stamp.viewmodels.DrawViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment:スタンプ作成画面
 */
@AndroidEntryPoint
class DrawFragment : Fragment() {
    /**
     * Memo:ViewModelを遅延初期化。onActivityCreated前に参照することはNG
     * by viewModelsで勝手にViewModelInjectされるようになってるみたい
     */
    private val viewModel: DrawViewModel by viewModels()

    /**
     * onCreateView
     */
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

        viewModel.clickedSaveButton.observe(viewLifecycleOwner, EventObserver {
            val bitmap : Bitmap = binding.drawView.bitmap()
            viewModel.addStamp(bitmap)
        })

        binding.viewModel = viewModel

        return binding.root
    }
}