package ru.wnfx.framework.ui.main.profile;

import ru.wnfx.framework.ui.base.BaseView;

public class ProfileContract {

    public interface View extends BaseView {
    }

    interface Presenter extends ru.wnfx.framework.ui.base.Presenter<ProfileContract.View> {
    }
}
