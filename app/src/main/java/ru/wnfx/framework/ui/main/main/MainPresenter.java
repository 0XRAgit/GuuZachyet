package ru.wnfx.framework.ui.main.main;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

import ru.wnfx.framework.data.model.Lesson;
import ru.wnfx.framework.ui.base.BasePresenter;
import ru.wnfx.framework.ui.main.profile.ProfileContract;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Lesson> lessons = new ArrayList<>();
    @Inject
    public MainPresenter() {
    }

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
    }

    public void getCollections(String day){
        db.collection(day).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    lessons.add(new Lesson(Objects.requireNonNull(document.getData().get("lesson_name")).toString(),
                            Objects.requireNonNull(document.getData().get("lesson_time")).toString(),
                            Objects.requireNonNull(document.getData().get("lesson_desct")).toString(),
                            Objects.requireNonNull(document.getData().get("lesson_teacher")).toString(),
                            Objects.requireNonNull(document.getData().get("lesson_aud")).toString(),
                            Objects.requireNonNull(document.getData().get("lesson_id")).toString()));
                }
            }
            getView().readyDay(lessons);
            lessons.clear();
        });
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public Lesson getLesson(int id){
        return lessons.get(id);
    }
}
