package ru.wnfx.framework.ui.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends BaseView> implements Presenter<V> {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private V mvpView;

    @Override
    public void attachView(V view) {
        this.mvpView = view;
    }

    @Override
    public void detachView() {
        mvpView = null;
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    @Override
    public boolean isViewAttached() {
        return mvpView != null;
    }

    @Override
    public V getView() {
        return mvpView;
    }

    protected void checkViewAttached() {
        if(!isViewAttached()) {
            throw new ViewNotAttachedException();
        }
    }

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    private static class ViewNotAttachedException extends RuntimeException {

        ViewNotAttachedException() {
            super(
                    "Please call Presenter.attachView(BaseView) before"
                            + " requesting data to the Presenter");
        }
    }
}
