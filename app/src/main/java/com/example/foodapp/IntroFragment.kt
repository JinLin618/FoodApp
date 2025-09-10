package com.example.foodapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class IntroFragment : Fragment(R.layout.activity_intro_screen) { //reference this fragment to activity_intro_screen layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnStart: Button = view.findViewById(R.id.startButton)
        btnStart.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.lightBlue)); //change "Let's Makan" button color
        btnStart.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setReorderingAllowed(true) //optimise the animation of the fragment transition
                .replace(R.id.fragmentContainer, MainListFragment()) //replace the fragment with MainListFragment()
                .addToBackStack(null) //save the state, so can always return to previous fragment/page
                .commit()
        }
    }
}


