package com.semihozmen.lkelerkotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.semihozmen.lkelerkotlin.R
import com.semihozmen.lkelerkotlin.databinding.FragmentDetailsBinding
import com.semihozmen.lkelerkotlin.util.downloadFromUrl
import com.semihozmen.lkelerkotlin.util.placeHolderProgressBar
import com.semihozmen.lkelerkotlin.viewmodel.DetailsViewModel


class DetailsFragment : Fragment() {

    private var countryId = 0
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_details,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryId = DetailsFragmentArgs.fromBundle(it).countryId
        }
        detailsViewModel.getDataFromRoom(countryId)

        observeLiveDate()

    }

     private fun observeLiveDate(){
         detailsViewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
             it?.let {country->

                 binding.selectedCountry = country

                 /*
                 binding.txtDetailName.text = country.name
                 binding.txtDetailCapital.text = country.capital
                 binding.txtDetailCurrency.text = country.currency
                 binding.txtDetailLanguage.text = country.language
                 binding.txtDetailRegion.text = country.region
                 context?.let {
                     binding.imdDetails.downloadFromUrl(country.flag, placeHolderProgressBar(it))
                 }

                  */

             }
         })
     }

}