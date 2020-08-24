package com.suudupa.lacoupe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suudupa.lacoupe.databinding.FragmentScoresGamePlayerItemRowBinding
import com.suudupa.lacoupe.model.MatchModel
import com.suudupa.lacoupe.model.PlayerModel
import io.realm.RealmList

class MatchPlayersAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mPlayers: RealmList<PlayerModel>
    private lateinit var mContext: Context

    constructor(match: MatchModel) : this() {
        mPlayers = match.players
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchPlayersViewHolder {
        mContext = parent.context
        return MatchPlayersViewHolder(
                FragmentScoresGamePlayerItemRowBinding.inflate(LayoutInflater.from(mContext), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val player = mPlayers[position]
        val mHolder = holder as MatchPlayersViewHolder
        if (player != null) {
            mHolder.bind(player)
        }
    }

    override fun getItemCount(): Int {
        return mPlayers.size
    }

    inner class MatchPlayersViewHolder(val binding: FragmentScoresGamePlayerItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(player: PlayerModel) {
            binding.data = player
            binding.executePendingBindings()
        }
    }
}