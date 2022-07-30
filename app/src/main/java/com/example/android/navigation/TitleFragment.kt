package com.example.android.navigation

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false
        )

        // Click listener to execute a navigation action
        /*
        binding.playButton.setOnClickListener {
            //V1
            //Navigation.findNavController(it).navigate(R.id.action_titleFragment_to_gameFragment)
            //V2
            it.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }
         */

        //V3
        binding.playButton.setOnClickListener (
            Navigation.createNavigateOnClickListener(TitleFragmentDirections.actionTitleFragmentToGameFragment()) //R.id.action_titleFragment_to_gameFragment)
        )

        // Menu
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return NavigationUI.onNavDestinationSelected(menuItem, view!!.findNavController())
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }
}