package com.example.lab_android_outlay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lab_android_outlay.databinding.FragmentBaseBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BaseFragment : Fragment() {

    private var _binding: FragmentBaseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBaseBinding.inflate(inflater, container, false)

        binding.showHistoryButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.enterDataButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_enterDataFragment)
        }

        binding.setLimitsButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_setLimitsFragment)
        }

        binding.logOutButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_loginFragment)
        }

        (activity as BaseActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}