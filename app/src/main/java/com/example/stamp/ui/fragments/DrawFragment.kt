package com.example.stamp.ui.fragments

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.stamp.R
import com.example.stamp.databinding.FragmentDrawBinding
import com.example.stamp.viewmodels.DrawViewModel
import com.example.stamp.viewmodels.StampViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrawFragment : Fragment() {
//    private val viewModel: DrawViewModel by lazy {
//        val activity = requireNotNull(this.activity) {
//            "You can only access the viewModel after onActivityCreated()"
//        }
//
//        ViewModelProvider(this,
//            DrawViewModel.Factory(activity.application)).get(DrawViewModel::class.java)
//    }

    /**
     * Memo:ViewModelを遅延初期化。onActivityCreated前に参照することはNG
     */
    private val viewModel: DrawViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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