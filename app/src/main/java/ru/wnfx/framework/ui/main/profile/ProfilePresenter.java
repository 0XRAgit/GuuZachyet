package ru.wnfx.framework.ui.main.profile;

import javax.inject.Inject;

import ru.wnfx.framework.ui.base.BasePresenter;

public class ProfilePresenter extends BasePresenter<ProfileContract.View> implements ProfileContract.Presenter {

    @Inject
    public ProfilePresenter() {
    }

    @Override
    public void attachView(ProfileContract.View view) {
        super.attachView(view);
    }
}
