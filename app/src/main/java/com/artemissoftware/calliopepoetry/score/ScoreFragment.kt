package com.artemissoftware.calliopepoetry.score

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.artemissoftware.calliopepoetry.R
import com.artemissoftware.calliopepoetry.databinding.ScoreFragmentBinding
import com.artemissoftware.calliopepoetry.game.GameViewModel

class ScoreFragment : Fragment(R.layout.score_fragment){

    private var _binding : ScoreFragmentBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ScoreFragmentArgs>()

    private lateinit var viewModel: ScoreViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = ScoreFragmentBinding.bind(view)

        viewModel = ViewModelProvider(this).get(ScoreViewModel::class.java)

        binding.apply {

            scoreViewModel = viewModel
            lifecycleOwner  = viewLifecycleOwner
            score = args.score
        }

        // Navigates back to game when button is pressed

        viewModel.eventPlayAgain.observe(viewLifecycleOwner, Observer { playAgain ->
            if (playAgain) {
                findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
                viewModel.onPlayAgainComplete()
            }
        })

    }

}