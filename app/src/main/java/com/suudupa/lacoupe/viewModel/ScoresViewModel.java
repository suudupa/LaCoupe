package com.suudupa.lacoupe.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.suudupa.lacoupe.App;
import com.suudupa.lacoupe.R;
import com.suudupa.lacoupe.model.UserModel;
import com.suudupa.lacoupe.repository.RealmRepo;
import com.suudupa.lacoupe.utility.Utils;

import io.realm.Realm;

public class ScoresViewModel extends AndroidViewModel {

    private MutableLiveData<String> mGreeting;
    private RealmRepo realmRepo = new RealmRepo();

    public ScoresViewModel(Application app) {
        super(app);
        mGreeting = new MutableLiveData<>();
        mGreeting.setValue(app.getString(R.string.greetingUser, app.getString(Utils.INSTANCE.greeting()), getCurrentUser()));
    }

    public LiveData<String> getText() {
        return mGreeting;
    }

    private String getCurrentUser() {
        return realmRepo.getRealm().where(UserModel.class).equalTo("isMain", true).findFirst().getFullName();
    }
}