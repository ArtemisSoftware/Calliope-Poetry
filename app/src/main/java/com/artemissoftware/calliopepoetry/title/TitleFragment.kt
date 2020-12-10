package com.artemissoftware.calliopepoetry.title

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.artemissoftware.calliopepoetry.R
import com.artemissoftware.calliopepoetry.databinding.TitleFragmentBinding

class TitleFragment : Fragment(R.layout.title_fragment) {

    private var _binding : TitleFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = TitleFragmentBinding.bind(view)



        binding.playGameButton.setOnClickListener {
            it.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }

        setHasOptionsMenu(true)

    }
}