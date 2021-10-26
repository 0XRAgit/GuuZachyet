package ru.wnfx.framework.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public abstract class BaseFragment extends AppCompatDialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
        attachView();
    }

    protected abstract void inject();

    protected abstract void attachView();

    protected abstract void detachPresenter();

    public void openKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        view.requestFocus();
        Objects.requireNonNull(imm).showSoftInput(view, 0);
    }

    public void closeKeyboard() {
        if(getActivity() != null) {
            View view = getActivity().getCurrentFocus();
            if (view instanceof EditText) {
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                Objects.requireNonNull(imm).hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void onDestroy() {
        detachPresenter();
        super.onDestroy();
    }
}
