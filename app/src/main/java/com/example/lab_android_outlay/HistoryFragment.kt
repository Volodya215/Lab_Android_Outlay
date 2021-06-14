package com.example.lab_android_outlay

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_android_outlay.data.model.UserData
import com.example.lab_android_outlay.databinding.FragmentHistoryBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null

    var refUsers: DatabaseReference? = null
    var firebaseUser: FirebaseUser? = null
    private lateinit var mAuth: FirebaseAuth

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    internal var user: UserData? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        _binding!!.loading.root.visibility = VISIBLE

        firebaseUser = FirebaseAuth.getInstance().currentUser
        refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUser!!.uid)


        refUsers!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    val user: UserData = snapshot.getValue(UserData::class.java)!!
                    binding.historyRecycler.adapter = HistoryAdapter(HistoryUserData.getUserData(user))
                }
            }
        })

        _binding!!.loading.root.visibility = GONE
        //in callback
        binding.historyRecycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}