package com.artemissoftware.calliopepoetry.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.artemissoftware.calliopepoetry.R
import com.artemissoftware.calliopepoetry.databinding.GameFragmentBinding

class GameFragment : Fragment(R.layout.game_fragment) {


    private var _binding : GameFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = GameFragmentBinding.bind(view)
    }
}