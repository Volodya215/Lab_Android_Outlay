package com.example.lab_android_outlay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_android_outlay.databinding.FragmentSetLimitsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception


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
    private var firebaseUser: FirebaseUser? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSetLimitsBinding.inflate(inflater, container, false)
        binding.categoryRecycler.layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false)
        binding.categoryRecycler.adapter = CategoryAdapter(Category.categories)

        binding.submit.setOnClickListener {
            val limit: Long? = binding.number.text.toString().toLong()
            val position = CategoryAdapter.index
            if(limit == null || position == -1)
                Toast.makeText(this.context, "You need to select category and enter the limit amount", Toast.LENGTH_LONG).show()
            else
            {
                try {
                    setValueOfLimitsInDatabase(position, limit)
                }
                catch (e: Exception)
                {
                    Toast.makeText(this.context, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        return binding.root
    }

    private fun setValueOfLimitsInDatabase(position: Int, limit: Long)
    {
        firebaseUser = FirebaseAuth.getInstance().currentUser

        val category: String = when(position) {
            0 -> "houseLimit"
            1 -> "carLimit"
            2 -> "travelLimit"
            3 -> "groceryLimit"
            4 -> "sportLimit"
            5 -> "ticketsLimit"
            6 -> "sweetsLimit"
            7 -> "clothesLimit"
            8 -> "beautyLimit"
            9 -> "tvlimit"
            else -> ""
        }

        FirebaseDatabase.getInstance().reference.child("Users")
            .child(firebaseUser!!.uid).child(category).setValue(limit)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this.context, "Data updated", Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}