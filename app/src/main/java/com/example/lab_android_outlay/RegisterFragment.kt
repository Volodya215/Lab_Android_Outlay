package com.example.lab_android_outlay

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lab_android_outlay.data.model.UserData
import com.example.lab_android_outlay.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null

    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserId: String = ""

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

     


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerButton = binding.register
        mAuth = FirebaseAuth.getInstance()

        registerButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val email: String = binding.emailRegistration.text.toString()
        val password: String = binding.password.text.toString()

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this.context, "Enter username", Toast.LENGTH_LONG).show()
        }
        else if(password.length <= 5)
        {
                Toast.makeText(this.context, R.string.invalid_password, Toast.LENGTH_LONG).show()
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    task ->
                    if (task.isSuccessful)
                    {
                        firebaseUserId = mAuth.currentUser!!.uid
                       /// refUsers = FirebaseDatabase.getInstance().reference.child("Users").child(firebaseUserId)

                        var refOnDb = FirebaseDatabase.getInstance();
                        //efOnDb.setPersistenceEnabled(true)

                        refUsers = refOnDb.getReference("Users").child(firebaseUserId)
                        var user  = UserData(firebaseUserId, email)
                        refUsers.setValue(user)
                            .addOnCompleteListener { task  ->
                                if(task.isSuccessful)
                                {
                                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                                }
                            }
                    }
                    else
                    {
                        Toast.makeText(this.context, "Error: " + task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

}