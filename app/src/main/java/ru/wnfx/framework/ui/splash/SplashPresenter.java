package ru.wnfx.framework.ui.splash;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collections;

import javax.inject.Inject;

import ru.wnfx.framework.data.repository.AuthRepository;
import ru.wnfx.framework.data.repository.TestRepository;
import ru.wnfx.framework.ui.base.BasePresenter;
import ru.wnfx.framework.ui.test.TestContract;

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {
    public TestRepository testRepository;

    @Inject
    public SplashPresenter(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public void attachView(SplashContract.View view) {
        super.attachView(view);
    }


}
