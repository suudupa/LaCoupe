package com.suudupa.lacoupe.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suudupa.lacoupe.model.UserModel
import com.suudupa.lacoupe.repository.RealmRepo
import io.realm.RealmChangeListener
import io.realm.RealmResults
import io.realm.Sort

class StandingsViewModel : ViewModel() {

    private val realmRepo = RealmRepo()

    private val _players = MutableLiveData<RealmResults<UserModel>>()
    val players: LiveData<RealmResults<UserModel>> get() = _players

    init {
        getPlayers()
    }


    private fun getPlayers() {
        _players.value = realmRepo.realm.where(UserModel::class.java).sort("wins", Sort.DESCENDING).findAllAsync()
    }

    val playersChangeListener: RealmChangeListener<RealmResults<UserModel>> = RealmChangeListener {
        _players.value = it
    }

    override fun onCleared() {
        super.onCleared()
        realmRepo.close()
    }
}