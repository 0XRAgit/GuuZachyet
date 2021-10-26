package ru.wnfx.framework.ui.reg.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.wnfx.framework.App;
import ru.wnfx.framework.databinding.FragmentAuthBinding;
import ru.wnfx.framework.ui.base.BaseFragment;

public class AuthFragment extends BaseFragment implements AuthContract.View {

    FragmentAuthBinding binding;

    @Inject
    AuthPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAuthBinding.inflate(inflater, container, false);

        setViewClickListeners();

        return binding.getRoot();
    }

    private void setViewClickListeners() {
    }

    @Override
    public void inject() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void attachView() {
        presenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        presenter.detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
