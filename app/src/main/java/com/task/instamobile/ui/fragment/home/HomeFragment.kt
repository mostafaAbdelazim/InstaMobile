package com.task.instamobile.ui.fragment.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.firebase.firestore.FirebaseFirestore

import com.task.instamobile.R
import com.task.instamobile.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val db = FirebaseFirestore.getInstance()
    private val categories = db.collection("Categories").document("dZsWGCUvF6DlyJvF3r9H")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        categories.get().addOnSuccessListener {
            text.text = it.data?.entries.toString()
        }.addOnFailureListener {
            text.text = it.localizedMessage
        }
        return binding.root
    }


}
