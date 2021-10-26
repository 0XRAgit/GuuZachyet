package ru.wnfx.framework.ui.look;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import ru.wnfx.framework.data.model.Lesson;
import ru.wnfx.framework.ui.base.BasePresenter;
import ru.wnfx.framework.ui.main.main.MainContract;

public class LookPresenter extends BasePresenter<LookContract.View> implements LookContract.Presenter {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Inject
    public LookPresenter() {
    }

    @Override
    public void attachView(LookContract.View view) {
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

    public void deleteLesson(String day, String id){
        db.collection(day).whereEqualTo("lesson_id", id).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    document.getId();
                    db.collection(day).document(document.getId()).delete().addOnCompleteListener(v->getView().deleteComplete());

                }
            }
        });
    }
}
