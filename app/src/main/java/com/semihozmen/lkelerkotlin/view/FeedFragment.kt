package com.semihozmen.lkelerkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.semihozmen.lkelerkotlin.R
import com.semihozmen.lkelerkotlin.adapter.CountryAdapter
import com.semihozmen.lkelerkotlin.databinding.FragmentFeedBinding
import com.semihozmen.lkelerkotlin.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    private lateinit var feedViewModel: FeedViewModel
    private val adapter = CountryAdapter(arrayListOf())
    private lateinit var binding: FragmentFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedViewModel.refreshData()
        binding.rv.layoutManager = LinearLayoutManager(context)
        binding.rv.adapter = adapter

        binding.refreshLayout.setOnRefreshListener {
            binding.rv.visibility  =View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            feedViewModel.refreshData()
            binding.refreshLayout.isRefreshing = false
        }

        observeLiveData()

    }

   private fun observeLiveData(){
        feedViewModel.countries.observe(viewLifecycleOwner, Observer {
            it?.let {list->
                binding.rv.visibility = View.VISIBLE
                adapter.updateCountryList(list)
            }
        })

        feedViewModel.countryError.observe(viewLifecycleOwner, Observer {error->
            error?.let {
                if(it){
                    binding.txtError.visibility = View.VISIBLE // Error mesajı gelirse burada gösterilir.
                    binding.progressBar.visibility = View.GONE
                    binding.rv.visibility = View.GONE
                }else{
                    binding.txtError.visibility = View.GONE
                }
            }
        })

        feedViewModel.countryLoading.observe(viewLifecycleOwner, Observer {loading->
            loading?.let{
                if(it){
                    binding.progressBar.visibility = View.VISIBLE
                    binding.txtError.visibility = View.GONE
                    binding.rv.visibility = View.GONE
                }else{
                    binding.progressBar.visibility = View.GONE
                }
            }
        })

    }

}