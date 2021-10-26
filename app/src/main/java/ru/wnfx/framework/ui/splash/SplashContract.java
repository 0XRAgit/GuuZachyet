package ru.wnfx.framework.ui.splash;

import ru.wnfx.framework.ui.base.BaseView;
import ru.wnfx.framework.ui.test.TestContract;

public class SplashContract {

    public interface View extends BaseView {
    }

    interface Presenter extends ru.wnfx.framework.ui.base.Presenter<SplashContract.View> {
    }
}
