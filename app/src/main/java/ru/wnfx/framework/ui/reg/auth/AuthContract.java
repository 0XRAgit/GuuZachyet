package ru.wnfx.framework.ui.reg.auth;

import ru.wnfx.framework.ui.base.BaseView;

public class AuthContract {

    public interface View extends BaseView {
    }

    interface Presenter extends ru.wnfx.framework.ui.base.Presenter<AuthContract.View> {
    }
}
