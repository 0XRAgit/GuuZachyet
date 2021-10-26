package ru.wnfx.framework.di.components;

import javax.inject.Singleton;

import dagger.Component;
import ru.wnfx.framework.di.modules.NetworkModule;
import ru.wnfx.framework.di.modules.RepositoryModule;
import ru.wnfx.framework.ui.editAdd.AddEditFragment;
import ru.wnfx.framework.ui.look.LookFragment;
import ru.wnfx.framework.ui.main.main.MainFragment;
import ru.wnfx.framework.ui.main.profile.ProfileFragment;
import ru.wnfx.framework.ui.reg.auth.AuthFragment;
import ru.wnfx.framework.ui.splash.SplashFragment;
import ru.wnfx.framework.ui.test.TestFragment;

@Singleton
@Component(modules = {RepositoryModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(TestFragment testFragment);

    void inject(AuthFragment authFragment);
    void inject(ProfileFragment profileFragment);
    void inject(SplashFragment splashFragment);
    void inject(MainFragment mainFragment);
    void inject(LookFragment lookFragment);
    void inject(AddEditFragment addEditFragment);
}
