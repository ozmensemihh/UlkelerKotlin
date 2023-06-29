package com.semihozmen.lkelerkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.semihozmen.lkelerkotlin.R
import com.semihozmen.lkelerkotlin.databinding.RecRowBinding
import com.semihozmen.lkelerkotlin.model.CountryModel
import com.semihozmen.lkelerkotlin.util.downloadFromUrl
import com.semihozmen.lkelerkotlin.util.placeHolderProgressBar
import com.semihozmen.lkelerkotlin.view.FeedFragmentDirections

class CountryAdapter(val countryList:ArrayList<CountryModel>) : RecyclerView.Adapter<CountryAdapter.CountryHolder>(),CountryClickListener {

    class CountryHolder(val binding:RecRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val binding = DataBindingUtil.inflate<RecRowBinding>(LayoutInflater.from(parent.context), R.layout.rec_row,parent,false)
        return CountryHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {

        holder.binding.country = countryList.get(position) // country xml içinde tanımlanan değişken
        holder.binding.listener = this

/*
        holder.binding.txtCardCountryname.text = countryList.get(position).name
        holder.binding.txtCardCountryinfo.text = countryList.get(position).region
        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToDetailsFragment(countryList.get(position).id)
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.imgCard.downloadFromUrl(countryList.get(position).flag,
            placeHolderProgressBar(holder.itemView.context)
        )

        */

    }

    fun updateCountryList(newCountryList:List<CountryModel>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClick(v: View) {
        val id = v.findViewById<TextView>(R.id.countryId).text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToDetailsFragment(id)
        Navigation.findNavController(v).navigate(action)
    }
}