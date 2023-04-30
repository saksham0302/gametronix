package com.example.gametronix.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gametronix.R

class Profile(val user: String) : Fragment() {

    lateinit var displayUser: TextView
    lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_profile, container, false)

        displayUser = v.findViewById(R.id.displayUser)

        displayUser.text = user

        // Inflate the layout for this fragment
        return v
    }
}