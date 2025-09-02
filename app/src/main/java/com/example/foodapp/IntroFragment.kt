package com.example.foodapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class IntroFragment : Fragment(R.layout.activity_intro_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnStart: Button = view.findViewById(R.id.startButton)
        btnStart.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.lightBlue));
        btnStart.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, MainListFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}


