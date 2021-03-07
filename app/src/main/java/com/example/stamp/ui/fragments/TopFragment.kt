package com.example.stamp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.stamp.R
import com.example.stamp.databinding.FragmentTopBinding

class TopFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTopBinding>(
            inflater,
            R.layout.fragment_top,
            container, false
        )
        // UIイベントリスナーの作成
        binding.drawbutton.setOnClickListener { view: View ->
            // Navigationを使った画面遷移を実施
            view.findNavController().navigate(R.id.action_topFragment_to_drawFragment)
        }
        binding.stampButton.setOnClickListener {view: View ->
            // Navigationを使った画面遷移を実施
            view.findNavController().navigate(R.id.action_topFragment_to_stampFragment)
        }

        return binding.root
    }
}