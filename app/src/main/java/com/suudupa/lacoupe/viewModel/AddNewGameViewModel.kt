package com.suudupa.lacoupe.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.suudupa.lacoupe.model.MatchModel
import com.suudupa.lacoupe.model.UserModel
import com.suudupa.lacoupe.repository.RealmRepo

class AddNewGameViewModel : ViewModel() {

    private val TAG = "AddNewGameVM"
    private val realmRepo = RealmRepo()

    fun addNewGame(match: MatchModel): Boolean {
        val winner = match.players.filter { it.score == 4 }
        return if (winner.size != 1) {
            false
        } else {
            match.players.forEach {player ->
                if (player.score > 4) {
                    return false
                }
                realmRepo.insertOrUpdate(match)
                val user = UserModel().apply {
                    fullName = player.user!!.fullName
                    jerseyNumber = player.user!!.jerseyNumber
                    gamesPlayed = player.user!!.gamesPlayed + 1
                    if (player.score == 4) {
                        wins = player.user!!.wins + 1
                    }
                    isMain = player.user!!.isMain
                }
                realmRepo.insertOrUpdate(user)
            }
            Log.d(TAG, match.toString())
            true
        }
    }
}