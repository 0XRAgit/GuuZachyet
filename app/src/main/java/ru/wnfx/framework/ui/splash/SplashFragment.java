package ru.wnfx.framework.ui.splash;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import ru.wnfx.framework.App;
import ru.wnfx.framework.R;
import ru.wnfx.framework.databinding.FragmentSplashBinding;
import ru.wnfx.framework.databinding.FragmentTestBinding;
import ru.wnfx.framework.ui.base.BaseFragment;
import ru.wnfx.framework.ui.test.TestContract;
import ru.wnfx.framework.ui.test.TestPresenter;

public class SplashFragment extends BaseFragment implements SplashContract.View {

    FragmentSplashBinding binding;

    @Inject
    SplashPresenter splashPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(inflater, container, false);

        new android.os.Handler().postDelayed(() -> {
            Navigation.findNavController(requireView()).navigate(R.id.mainFragment);
        },3000);

        return binding.getRoot();
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
        splashPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
