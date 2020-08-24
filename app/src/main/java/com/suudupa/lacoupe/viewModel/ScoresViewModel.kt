package com.suudupa.lacoupe.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.suudupa.lacoupe.R
import com.suudupa.lacoupe.model.MatchModel
import com.suudupa.lacoupe.model.UserModel
import com.suudupa.lacoupe.repository.RealmRepo
import com.suudupa.lacoupe.utility.Utils.greeting
import io.realm.RealmChangeListener
import io.realm.RealmResults
import io.realm.Sort

class ScoresViewModel (val app: Application): AndroidViewModel(app) {

    private val realmRepo = RealmRepo()

    private val _greeting = MutableLiveData<String>().apply {
        value = try {
            app.getString(R.string.greetingUser, app.getString(greeting()), getCurrentUser())
        } catch (e: Exception) {
            app.getString(R.string.greetingUser, app.getString(greeting()), "User")
        }
    }
    val greeting: LiveData<String> = _greeting

    private val _matches = MutableLiveData<RealmResults<MatchModel>>()
    val matches: LiveData<RealmResults<MatchModel>> get() = _matches

    init {
        getMatches()
    }

    private fun getCurrentUser(): String? {
        return realmRepo.realm.where(UserModel::class.java).equalTo("isMain", true).findFirst()!!.fullName
    }

    private fun getMatches() {
        _matches.value = realmRepo.realm.where(MatchModel::class.java).sort("date", Sort.DESCENDING).findAll()
    }

    val matchesChangeListener: RealmChangeListener<RealmResults<MatchModel>> = RealmChangeListener {
        _matches.value = it
    }

    override fun onCleared() {
        super.onCleared()
        realmRepo.close()
    }
}