package com.example.github_search_app.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.github_search_app.R
import com.example.github_search_app.databinding.FragmentMainBinding
import com.example.github_search_app.utils.*


class Main_Fragment : Fragment() {

    private lateinit var mViewModel: Main_ViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter : Main_RecyclerView_Adapter
    private var binding: FragmentMainBinding? = null
    private val mBinding get() = binding!!
    private lateinit var mObserver: MutableLiveData<Int>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        mViewModel = ViewModelProvider(this)[Main_ViewModel::class.java]
        mViewModel.initSearch()
        initialization()
        switchPage()
        //Log.e("kek", "$QUERY $QUERY2 $PAGE")
        mBinding.srcButton.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_main_Fragment_to_search_Fragment)
            onDestroy()
        }
    }

    private fun initialization() {
        mViewModel = ViewModelProvider(this)[Main_ViewModel::class.java]
        mRecyclerView = mBinding.recyclerView
        linearLayoutManager = LinearLayoutManager(activity)
        mRecyclerView.layoutManager = linearLayoutManager
        mAdapter = Main_RecyclerView_Adapter()
        mRecyclerView.adapter = mAdapter
        mViewModel.getRecyclerListObserver().observe(this, {
            if(it != null) {
                mAdapter.setUpdatedData(it.items)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        mViewModel.makeApiCall()
        mViewModel.recyclerListLiveData.removeObserver{this}
    }


    private fun switchPage () {
        mViewModel = ViewModelProvider(this)[Main_ViewModel::class.java]


        mObserver = mViewModel.page
        mObserver.observe (
            this, Observer {
                mBinding.pageNumber.text = it.toString() })


        mBinding.nextPage.setOnClickListener{
            mViewModel.nextPage()
            mViewModel.makeApiCall()}

        mBinding.prevPage.setOnClickListener{
            mViewModel.prevPage()
            mViewModel.makeApiCall()}
    }


    override fun onDestroy() {
        super.onDestroy()
        binding=null
        mViewModel.recyclerListLiveData.removeObserver{mAdapter.setUpdatedData(it.items)}
        mObserver.removeObserver{this}
        mViewModel.resetPage()
    }

    override fun onResume() {
        super.onResume()
        mViewModel = ViewModelProvider(this)[Main_ViewModel::class.java]
        mViewModel.makeApiCall()
    }

}


