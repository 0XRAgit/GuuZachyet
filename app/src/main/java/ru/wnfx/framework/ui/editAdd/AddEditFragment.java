package ru.wnfx.framework.ui.editAdd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import javax.inject.Inject;

import ru.wnfx.framework.App;
import ru.wnfx.framework.data.model.Lesson;
import ru.wnfx.framework.databinding.FragmentLookBinding;
import ru.wnfx.framework.ui.base.BaseFragment;
import ru.wnfx.framework.ui.look.LookContract;
import ru.wnfx.framework.ui.look.LookPresenter;

public class AddEditFragment extends BaseFragment implements AddEditContract.View{

    FragmentLookBinding binding;

    @Inject
    AddEditPresenter presenter;

    String id, currentDay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLookBinding.inflate(inflater, container, false);

        setViewClickListeners();

        id = getArguments().getString("id");
        currentDay = getArguments().getString("day");
        presenter.getLesson(currentDay,id);

        return binding.getRoot();
    }

    private void setViewClickListeners() {
        binding.imageButton.setOnClickListener(v-> Navigation.findNavController(requireView()).popBackStack());
        
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

    @Override
    public void getedLesson(Lesson lesson) {

    }
}
