package ru.wnfx.framework.data.repository;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.wnfx.framework.data.Prefs;
import ru.wnfx.framework.data.network.NetworkApi;

public class AuthRepository {

    private final Prefs prefs;
    private final NetworkApi api;

    public AuthRepository(NetworkApi api, Prefs prefs){
        this.prefs = prefs;
        this.api = api;
    }

    public Single<Object> getTest() {
        return api.getTest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
