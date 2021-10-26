package ru.wnfx.framework.ui.test;

import ru.wnfx.framework.ui.base.BaseView;

public class TestContract {

    public interface View extends BaseView {
        void testItemResult(Object test);
    }

    interface Presenter extends ru.wnfx.framework.ui.base.Presenter<View> {
        void getTestItems();
    }
}
