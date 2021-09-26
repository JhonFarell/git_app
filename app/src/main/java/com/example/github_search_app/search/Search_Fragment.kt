package com.example.github_search_app.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.github_search_app.R
import com.example.github_search_app.databinding.FragmentSearchBinding
import com.example.github_search_app.utils.APP_ACTIVITY
import com.example.github_search_app.utils.IS_BLANK
import com.example.github_search_app.utils.LIST_ALL

class Search_Fragment : Fragment() {

    private lateinit var sViewModel: Search_ViewModel
    private var binding: FragmentSearchBinding? = null
    private val sBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return sBinding.root
    }


    override fun onStart() {
        super.onStart()
        initialization()
        search()
    }

    private fun initialization() {
        sViewModel = ViewModelProvider(this)[Search_ViewModel::class.java]
        sBinding.Back.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_search_Fragment_to_main_Fragment)
        }
    }

    private fun search() {
        sBinding.SearchButton.setOnClickListener {

            var enterRepo = sBinding.EnterRepository.text
            var enterUser = sBinding.EnterUser.text

             when {
                enterRepo.isEmpty() && enterUser.isEmpty() -> {
                    sViewModel.searchOption(LIST_ALL, IS_BLANK)
                }
                enterUser.isEmpty() -> {
                    sViewModel.searchOption(enterRepo.toString(), IS_BLANK)
                }
                enterRepo.isEmpty() -> {
                    sViewModel.searchOption("user:$enterUser", IS_BLANK)
                }
                else -> {
                    sViewModel.searchOption("user:$enterUser", enterRepo.toString())
                    //Log.e("kek", "nope")
                }
            }
            APP_ACTIVITY.navController.navigate(R.id.action_search_Fragment_to_main_Fragment)
        }
        }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}


