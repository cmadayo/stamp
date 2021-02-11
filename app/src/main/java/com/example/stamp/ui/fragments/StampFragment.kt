package com.example.stamp.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stamp.R
import com.example.stamp.databinding.FragmentStampBinding
import com.example.stamp.databinding.StampItemBinding
import com.example.stamp.domain.DomainStamp
import com.example.stamp.viewmodels.StampViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StampFragment : Fragment() {
    /**
     * Memo:ViewModelを遅延初期化。onActivityCreated前に参照することはNG
     */
    private val viewModel: StampViewModel by viewModels()

    /**
     * RecyclerView Adapter for converting a list of Stamp to cards.
     */
    private var viewModelAdapter: StampAdapter? = null

    /**
     * onCreateView()のあと呼び出される
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.stampList.observe(viewLifecycleOwner, Observer<List<DomainStamp>> { stamps ->
            stamps?.apply {
                viewModelAdapter?.stamps = stamps
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentStampBinding>(
            inflater,
            R.layout.fragment_stamp,
            container, false
        )
        viewModelAdapter = StampAdapter(StampClick {
            // TODO
        })
        binding.stampsView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        binding.viewModel = viewModel

        return binding.root
    }
}

/**
 * スタンプをクリックした際のリスナ
 * 可読性のために、名前をつけて独立したクラスとする
 */
class StampClick(val block: (DomainStamp) -> Unit) {
    /**
     * Called when a stamp is clicked
     *
     * @param stamp the stamp that was clicked
     */
    fun onClick(stamp: DomainStamp) = block(stamp)
}

/**
 * Memo:各アイテムにデータバインディングするためのRecyclerView Adapter
 */
class StampAdapter(val callback: StampClick) : RecyclerView.Adapter<StampHolder>() {

    /**
     * The stamps that our Adapter will show
     */
    var stamps: List<DomainStamp> = emptyList()
        set(value) {
            field = value
            // Observerに変更通知し、すべての要素を無効にする
            notifyDataSetChanged()
        }

    /**
     * 新しいViewHolderが必要となるたびに呼ばれる
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StampHolder {
        val withDataBinding: StampItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            StampHolder.LAYOUT,
            parent,
            false)
        return StampHolder(withDataBinding)
    }

    override fun getItemCount() = stamps.size

    /**
     * 表示しているViewHolderのアイテムを更新
     */
    override fun onBindViewHolder(holder: StampHolder, position: Int) {
        holder.viewDataBinding.also {
            it.stamp = stamps[position]
            it.stampClickCallback = callback
        }
    }
}

/**
 * Stamp itemsのためのViewHolder
 * 処理はデータバインディングによって行われる
 * 新しいitemが必要となるたび、findViewByIdするのはコストが高いため
 * ViewHolderにてLayoutを保持する(viewを再利用する)
 */
class StampHolder(val viewDataBinding: StampItemBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes val LAYOUT = R.layout.stamp_item
    }
}