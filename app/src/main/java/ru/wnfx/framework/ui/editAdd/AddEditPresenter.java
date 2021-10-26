package ru.wnfx.framework.ui.editAdd;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import ru.wnfx.framework.data.model.Lesson;
import ru.wnfx.framework.ui.base.BasePresenter;
import ru.wnfx.framework.ui.look.LookContract;

public class AddEditPresenter extends BasePresenter<AddEditContract.View> implements AddEditContract.Presenter {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String dbId;

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
                    dbId=document.getId();
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

    public void updateLesson(Lesson lesson,String day){
        DocumentReference ref =  db.collection(day).document(dbId);
        ref.update("lesson_name",lesson.getName());
        ref.update("lesson_dest",lesson.getDesct());
        ref.update("lesson_aud",lesson.getAud());
        ref.update("lesson_teacher",lesson.getTeacher());
        ref.update("lesson_time",lesson.getTime());
        getView().readyUpdate();
    }

    public void addLesson(Lesson lesson, String day){
        Map<String, Object> data = new HashMap<>();
        data.put("lesson_name",lesson.getName());
        data.put("lesson_desct",lesson.getDesct());
        data.put("lesson_aud",lesson.getAud());
        data.put("lesson_teacher",lesson.getTeacher());
        data.put("lesson_time",lesson.getTime());
        data.put("lesson_id",lesson.getId());
        db.collection(day).document(lesson.getId())
                .set(data, SetOptions.merge()).addOnCompleteListener(task -> getView().readyUpdate());
    }
}
