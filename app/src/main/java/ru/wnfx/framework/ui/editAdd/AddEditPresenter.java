package ru.wnfx.framework.ui.editAdd;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Objects;

import javax.inject.Inject;

import ru.wnfx.framework.data.model.Lesson;
import ru.wnfx.framework.ui.base.BasePresenter;
import ru.wnfx.framework.ui.look.LookContract;

public class AddEditPresenter extends BasePresenter<LookContract.View> implements AddEditContract.Presenter {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Inject
    public AddEditPresenter() {
    }

    @Override
    public void attachView(AddEditContract.View view) {
        super.attachView(view);
    }

    public void getLesson(String day, String id){
        db.collection(day).whereEqualTo("lesson_id", id).get().addOnCompleteListener(task -> {
            Lesson lesson = null;
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    lesson = new Lesson(Objects.requireNonNull(document.getData().get("lesson_name")).toString(),
                            Objects.requireNonNull(document.getData().get("lesson_time")).toString(),
                            Objects.requireNonNull(document.getData().get("lesson_desct")).toString(),
                            Objects.requireNonNull(document.getData().get("lesson_teacher")).toString(),
                            Objects.requireNonNull(document.getData().get("lesson_aud")).toString(),
                            Objects.requireNonNull(document.getData().get("lesson_id")).toString());
                }
            }
            getView().getedLesson(lesson);
        });
    }
}
