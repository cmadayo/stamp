package com.example.stamp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.stamp.R
import com.example.stamp.databinding.FragmentTopBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TopFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
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

        return binding.root
    }
}