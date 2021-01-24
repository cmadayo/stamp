package com.example.stamp.ui.draw

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.stamp.R
import com.example.stamp.databinding.FragmentDrawBinding
import com.example.stamp.databinding.FragmentTopBinding
import com.example.stamp.ui.view.DrawView
import com.example.stamp.viewmodels.DrawViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [DrawFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DrawFragment : Fragment() {

    private val viewModel: DrawViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        ViewModelProvider(this,
            DrawViewModel.Factory(activity.application)).get(DrawViewModel::class.java)
    }

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

        binding.viewModel = viewModel

        return binding.root
    }
}