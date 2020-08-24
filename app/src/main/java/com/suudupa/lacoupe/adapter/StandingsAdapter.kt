package com.suudupa.lacoupe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suudupa.lacoupe.databinding.FragmentStandingsPlayerItemRowBinding
import com.suudupa.lacoupe.model.UserModel
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class StandingsAdapter(players: OrderedRealmCollection<UserModel>?) :
        RealmRecyclerViewAdapter<UserModel, StandingsAdapter.StandingsViewHolder>(players, true, true) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingsViewHolder {
        return StandingsViewHolder(
                FragmentStandingsPlayerItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: StandingsViewHolder, position: Int) {
        val player = data!![position]
        holder.bind(player)
        holder.binding.playerRankingTv.text = (position + 1).toString()
    }

    inner class StandingsViewHolder(val binding: FragmentStandingsPlayerItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(player: UserModel) {
            binding.data = player
            binding.executePendingBindings()
        }
    }
}