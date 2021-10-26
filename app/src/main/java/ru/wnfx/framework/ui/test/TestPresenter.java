package ru.wnfx.framework.ui.test;

import javax.inject.Inject;

import ru.wnfx.framework.data.repository.AuthRepository;
import ru.wnfx.framework.data.repository.TestRepository;
import ru.wnfx.framework.ui.base.BasePresenter;

public class TestPresenter extends BasePresenter<TestContract.View> implements TestContract.Presenter {

    public TestRepository testRepository;
    public AuthRepository authRepository;

    @Inject
    public TestPresenter(TestRepository testRepository, AuthRepository authRepository) {
        this.testRepository = testRepository;
        this.authRepository = authRepository;
    }

    @Override
    public void attachView(TestContract.View view) {
        super.attachView(view);
    }

    @Override
    public void getTestItems() {
        checkViewAttached();

        authRepository.getTest()
                .subscribe(testItems -> {
                    getView().testItemResult(testItems);
                }, throwable -> {
                    getView().showError(throwable.getMessage());
                });
    }
}
