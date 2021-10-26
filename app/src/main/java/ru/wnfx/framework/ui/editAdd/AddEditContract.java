package ru.wnfx.framework.ui.editAdd;

import ru.wnfx.framework.data.model.Lesson;
import ru.wnfx.framework.ui.base.BaseView;
import ru.wnfx.framework.ui.look.LookContract;

public class AddEditContract {
    public interface View extends BaseView {
        void getedLesson(Lesson lesson);
    }

    interface Presenter extends ru.wnfx.framework.ui.base.Presenter<AddEditContract.View> {
    }
}
