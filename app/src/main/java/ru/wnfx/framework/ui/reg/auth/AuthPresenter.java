package ru.wnfx.framework.ui.reg.auth;

import javax.inject.Inject;

import ru.wnfx.framework.data.repository.AuthRepository;
import ru.wnfx.framework.ui.base.BasePresenter;

public class AuthPresenter extends BasePresenter<AuthContract.View> implements AuthContract.Presenter {

    public AuthRepository authRepository;

    @Inject
    public AuthPresenter(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public void attachView(AuthContract.View view) {
        super.attachView(view);
    }
}
