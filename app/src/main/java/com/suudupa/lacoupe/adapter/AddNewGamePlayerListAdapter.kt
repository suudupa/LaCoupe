package com.suudupa.lacoupe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.suudupa.lacoupe.R
import com.suudupa.lacoupe.databinding.FragmentAddNewGamePlayerItemRowBinding
import com.suudupa.lacoupe.model.UserModel
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class AddNewGamePlayerListAdapter(players: OrderedRealmCollection<UserModel>?) :
        RealmRecyclerViewAdapter<UserModel, AddNewGamePlayerListAdapter.AddNewGamePlayerListViewHolder>(players, true, true) {

    private lateinit var mContext: Context
    var participants = arrayListOf<UserModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddNewGamePlayerListViewHolder {
        mContext = parent.context
        return AddNewGamePlayerListViewHolder(
                FragmentAddNewGamePlayerItemRowBinding.inflate(LayoutInflater.from(mContext), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AddNewGamePlayerListAdapter.AddNewGamePlayerListViewHolder, position: Int) {
        val player = data!![position]
        holder.bind(player)
        holder.binding.playerNumberTv.text = String.format(mContext.getString(R.string.player_number), player.jerseyNumber.toString())
        holder.binding.checkbox.setOnClickListener {
            if (holder.binding.checkbox.isChecked) {
                participants.add(player)
            } else {
                participants.remove(player)
            }
        }
    }

    inner class AddNewGamePlayerListViewHolder(val binding: FragmentAddNewGamePlayerItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(player: UserModel) {
            binding.data = player
            binding.executePendingBindings()
        }
    }
}