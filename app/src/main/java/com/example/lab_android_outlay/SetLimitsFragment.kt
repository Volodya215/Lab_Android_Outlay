package com.example.lab_android_outlay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_android_outlay.databinding.FragmentRegisterBinding
import com.example.lab_android_outlay.databinding.FragmentSetLimitsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SetLimitsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetLimitsFragment : Fragment() {
    private var _binding: FragmentSetLimitsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSetLimitsBinding.inflate(inflater, container, false)
        binding.categoryRecycler.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false)
        binding.categoryRecycler.adapter = CategoryAdapter(Category.categories)


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}