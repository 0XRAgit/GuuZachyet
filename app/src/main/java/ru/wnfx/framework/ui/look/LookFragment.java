package ru.wnfx.framework.ui.look;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import ru.wnfx.framework.App;
import ru.wnfx.framework.R;
import ru.wnfx.framework.data.model.Lesson;
import ru.wnfx.framework.databinding.FragmentLookBinding;
import ru.wnfx.framework.databinding.FragmentMainBinding;
import ru.wnfx.framework.ui.base.BaseFragment;
import ru.wnfx.framework.ui.main.main.MainContract;
import ru.wnfx.framework.ui.main.main.MainPresenter;

public class LookFragment extends BaseFragment implements LookContract.View{

    FragmentLookBinding binding;

    @Inject
    LookPresenter presenter;

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
        binding.imageButton.setOnClickListener(v->Navigation.findNavController(requireView()).popBackStack());
        binding.delete.setOnClickListener(v->presenter.deleteLesson(currentDay, id));
        binding.edit.setOnClickListener(v->{
            Bundle bundle = new Bundle();
            bundle.putString("day",currentDay);
            bundle.putString("id",id);
            bundle.putString("type", "edit");
        });
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
        binding.title.setText(lesson.getName());
        binding.timeText.setText(lesson.getTime());
        binding.teacherText.setText(lesson.getTeacher());
        binding.audiText.setText(lesson.getAud());
        binding.desctText.setText(lesson.getDesct());
    }

    @Override
    public void deleteComplete() {
        Navigation.findNavController(requireView()).popBackStack();
    }
}
