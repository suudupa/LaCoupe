package com.suudupa.lacoupe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suudupa.lacoupe.databinding.FragmentScoresGameItemRowBinding
import com.suudupa.lacoupe.model.MatchModel
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class ScoresAdapter(matches: OrderedRealmCollection<MatchModel>?) :
        RealmRecyclerViewAdapter<MatchModel, ScoresAdapter.ScoresViewHolder>(matches, true, true) {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoresViewHolder {
        return ScoresViewHolder(
                FragmentScoresGameItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ScoresViewHolder, position: Int) {
        val match = data!![position]
        holder.bind(match)
        val playersLayoutManager = LinearLayoutManager(holder.binding.playerNamesRv.context, RecyclerView.VERTICAL, false)
        holder.binding.playerNamesRv.apply {
            layoutManager = playersLayoutManager
            adapter = MatchPlayersAdapter(match)
            setRecycledViewPool(viewPool)
        }
    }

    inner class ScoresViewHolder(val binding: FragmentScoresGameItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(match: MatchModel) {
            binding.data = match
            binding.executePendingBindings()
        }
    }
}