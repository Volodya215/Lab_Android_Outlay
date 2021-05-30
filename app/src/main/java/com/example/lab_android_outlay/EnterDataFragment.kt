package com.example.lab_android_outlay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_android_outlay.databinding.FragmentEnterDataBinding
import com.example.lab_android_outlay.databinding.FragmentHistoryBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EnterDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EnterDataFragment : Fragment() {

    private var _binding: FragmentEnterDataBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnterDataBinding.inflate(inflater, container, false)

        binding.categoryRecycler.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false)
        binding.categoryRecycler.adapter = CategoryAdapter(Category.categories)

        /* Find another way to do that */
        (activity as BaseActivity).supportActionBar?.title = getString(R.string.fragment_enter_data)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}