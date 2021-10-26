package ru.wnfx.framework.ui.look;

import java.util.ArrayList;

import ru.wnfx.framework.data.model.Lesson;
import ru.wnfx.framework.ui.base.BaseView;
import ru.wnfx.framework.ui.main.main.MainContract;

public class LookContract {
    public interface View extends BaseView {
        void getedLesson(Lesson lesson);
        void deleteComplete();
    }

    interface Presenter extends ru.wnfx.framework.ui.base.Presenter<LookContract.View> {
    }
}
