package com.suudupa.lacoupe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.suudupa.lacoupe.R
import com.suudupa.lacoupe.databinding.FragmentAddNewGamePlayerScoreItemRowBinding
import com.suudupa.lacoupe.model.PlayerModel
import com.suudupa.lacoupe.model.UserListModel
import com.suudupa.lacoupe.model.UserModel
import io.realm.RealmList
import java.lang.Exception

class AddNewGamePlayerScoreListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mParticipants: ArrayList<UserModel>
    private lateinit var mContext: Context
    var players = RealmList<PlayerModel>()

    constructor(playerList: UserListModel) : this() {
        playerList.users.sortWith(compareBy { it.jerseyNumber })
        mParticipants = playerList.users
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddNewGamePlayerScoreListViewHolder {
        mContext = parent.context
        return AddNewGamePlayerScoreListViewHolder(
                FragmentAddNewGamePlayerScoreItemRowBinding.inflate(LayoutInflater.from(mContext), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val player = mParticipants[position]
        val mHolder = holder as AddNewGamePlayerScoreListViewHolder
        mHolder.bind(player)
        players.add(PlayerModel(player, 0))
        mHolder.binding.playerScoreEt.doAfterTextChanged {
            players[position]!!.score = try {
                it.toString().toInt()
            } catch (e: Exception) {
                0
            }
        }
    }

    override fun getItemCount(): Int {
        return mParticipants.size
    }

    inner class AddNewGamePlayerScoreListViewHolder(val binding: FragmentAddNewGamePlayerScoreItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(player: UserModel) {
            binding.data = player
            binding.executePendingBindings()
            binding.playerNumberTv.text = String.format(mContext.getString(R.string.player_number), player.jerseyNumber.toString())
        }
    }
}