package com.suudupa.lacoupe.viewModel

import androidx.lifecycle.ViewModel
import com.suudupa.lacoupe.model.MatchModel
import com.suudupa.lacoupe.repository.RealmRepo

class AddNewGameViewModel : ViewModel() {

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
                val jerseyNumber = player.user!!.jerseyNumber
                if (player.score == 4) realmRepo.updateUserScore(jerseyNumber, true) else realmRepo.updateUserScore(jerseyNumber, false)
            }
            realmRepo.insertOrUpdate(match)
            true
        }
    }
}