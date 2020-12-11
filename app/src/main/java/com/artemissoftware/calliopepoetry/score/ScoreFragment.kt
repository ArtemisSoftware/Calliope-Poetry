package com.artemissoftware.calliopepoetry.score

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.artemissoftware.calliopepoetry.R
import com.artemissoftware.calliopepoetry.databinding.ScoreFragmentBinding
import com.artemissoftware.calliopepoetry.game.GameViewModel

class ScoreFragment : Fragment(R.layout.score_fragment){

    private var _binding : ScoreFragmentBinding? = null
    private val binding get() = _binding!!

    //private lateinit var viewModel: GameViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = ScoreFragmentBinding.bind(view)

        //viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

    }

}