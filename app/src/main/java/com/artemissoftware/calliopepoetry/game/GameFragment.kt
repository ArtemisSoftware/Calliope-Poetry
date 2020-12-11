package com.artemissoftware.calliopepoetry.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.artemissoftware.calliopepoetry.R
import com.artemissoftware.calliopepoetry.databinding.GameFragmentBinding

class GameFragment : Fragment(R.layout.game_fragment) {


    private var _binding : GameFragmentBinding? = null
    private val binding get() = _binding!!


    private lateinit var viewModel: GameViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = GameFragmentBinding.bind(view)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.apply {
            gameViewModel = viewModel
            lifecycleOwner  = viewLifecycleOwner
        }


        // Observer for the Game finished event

        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer<Boolean> {
            if (it) gameFinished()
        })

    }


    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        Toast.makeText(activity, "Calliope has no more poetry for you", Toast.LENGTH_SHORT).show()

        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(viewModel.score.value?:0)

        findNavController().navigate(action)
        viewModel.onGameFinishComplete()
    }

}