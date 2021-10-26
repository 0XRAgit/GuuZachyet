package ru.wnfx.framework.ui.main.main;

import java.util.ArrayList;

import ru.wnfx.framework.data.model.Lesson;
import ru.wnfx.framework.ui.base.BaseView;
import ru.wnfx.framework.ui.main.profile.ProfileContract;

public class MainContract {
    public interface View extends BaseView {
        void readyDay(ArrayList<Lesson> lessons);
        void chooseDay(Lesson lessons);
    }

    interface Presenter extends ru.wnfx.framework.ui.base.Presenter<MainContract.View> {
    }
}
