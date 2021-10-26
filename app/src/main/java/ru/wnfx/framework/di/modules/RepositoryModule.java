package ru.wnfx.framework.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.wnfx.framework.data.Prefs;
import ru.wnfx.framework.data.network.NetworkApi;
import ru.wnfx.framework.data.repository.AuthRepository;
import ru.wnfx.framework.data.repository.TestRepository;

@Module
public class RepositoryModule {

    private Context context;

    public RepositoryModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    public Prefs providePrefs(){
        return new Prefs(context);
    }

    @Singleton
    @Provides
    public TestRepository provideTestRepository() {
        return new TestRepository(providePrefs());
    }

    @Singleton
    @Provides
    public AuthRepository provideAuthRepository(NetworkApi api) {
        return new AuthRepository(api, providePrefs());
    }
}
