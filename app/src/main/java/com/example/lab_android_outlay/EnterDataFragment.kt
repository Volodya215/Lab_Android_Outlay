package com.example.lab_android_outlay

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_android_outlay.databinding.FragmentEnterDataBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 * Use the [EnterDataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EnterDataFragment : Fragment() {

    private var _binding: FragmentEnterDataBinding? = null

    private var refUsers: DatabaseReference? = null
    private var firebaseUser: FirebaseUser? = null

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

        binding.submit.setOnClickListener {
            val sum: Long? = binding.number.text.toString().toLong()
            val position = CategoryAdapter.index
            if(sum == null || position == -1)
                Toast.makeText(this.context, "You need to select category and enter the cost amount", Toast.LENGTH_LONG).show()
            else
            {
                try {
                    setValueOfCostsInDatabase(position, sum)
                }
                catch (e: Exception)
                {
                    Toast.makeText(this.context, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        return binding.root
    }

    private fun setValueOfCostsInDatabase(position: Int, sum: Long)
    {
        firebaseUser = FirebaseAuth.getInstance().currentUser

        val category: String = when(position) {
            0 -> "houseSum"
            1 -> "carSum"
            2 -> "travelSum"
            3 -> "grocerySum"
            4 -> "sportSum"
            5 -> "ticketsSum"
            6 -> "sweetsSum"
            7 -> "clothesSum"
            8 -> "beautySum"
            9 -> "tvsum"
            else -> ""
        }

        FirebaseDatabase.getInstance().reference.child("Users")
            .child(firebaseUser!!.uid).child(category).setValue(ServerValue.increment(sum))
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