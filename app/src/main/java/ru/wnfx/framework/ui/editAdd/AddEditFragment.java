package ru.wnfx.framework.ui.editAdd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import java.nio.charset.Charset;
import java.util.Random;

import javax.inject.Inject;

import ru.wnfx.framework.App;
import ru.wnfx.framework.R;
import ru.wnfx.framework.data.model.Lesson;
import ru.wnfx.framework.databinding.FragmentAddEditBinding;
import ru.wnfx.framework.databinding.FragmentLookBinding;
import ru.wnfx.framework.ui.base.BaseFragment;
import ru.wnfx.framework.ui.look.LookContract;
import ru.wnfx.framework.ui.look.LookPresenter;

public class AddEditFragment extends BaseFragment implements AddEditContract.View{

    FragmentAddEditBinding binding;

    @Inject
    AddEditPresenter presenter;

    String id, currentDay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddEditBinding.inflate(inflater, container, false);

        setViewClickListeners();
        currentDay = getArguments().getString("day");
        assert getArguments() != null;
        if(getArguments().getString("type").equals("edit")){
            id = getArguments().getString("id");
            presenter.getLesson(currentDay,id);
        }
        switch (currentDay){
            case "monday":
                binding.title.setText("Понедельник");
                break;
            case "tuesday":
                binding.title.setText("Вторник");
                break;
            case "wednesday":
                binding.title.setText("Среда");
                break;
            case "thursday":
                binding.title.setText("Четверг");
                break;
            case "friday":
                binding.title.setText("Пятница");
                break;
        }
        return binding.getRoot();
    }

    private void setViewClickListeners() {
        binding.imageButton.setOnClickListener(v-> Navigation.findNavController(requireView()).popBackStack());
        binding.done.setOnClickListener(v->{
            assert getArguments() != null;
            if(getArguments().getString("type").equals("edit")){
                editLesson();
                return;
            }
            newLesson();
        });
    }

    private void editLesson(){
        if(binding.lesson.getText().toString().isEmpty() ||
        binding.teacher.getText().toString().isEmpty()||
        binding.audi.getText().toString().isEmpty()||
        binding.timeStart.getText().toString().isEmpty() ||
        binding.timeEnd.getText().toString().isEmpty() ||
        binding.desct.getText().toString().isEmpty())
            Toast.makeText(requireContext(), "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show();
        else{
            Lesson lesson = new Lesson(binding.lesson.getText().toString(),
                    binding.timeStart.getText().toString()+"-"+binding.timeEnd.getText().toString(),
                    binding.desct.getText().toString(), binding.teacher.getText().toString(),
                    binding.audi.getText().toString(),id);
            presenter.updateLesson(lesson, currentDay);
        }
    }

    private String rand(){
        return String.valueOf(((Math.random() * (2487 - 100)) + 100));
    }

    private void newLesson(){
        if(binding.lesson.getText().toString().isEmpty() ||
                binding.teacher.getText().toString().isEmpty()||
                binding.audi.getText().toString().isEmpty()||
                binding.timeStart.getText().toString().isEmpty() ||
                binding.timeEnd.getText().toString().isEmpty() ||
                binding.desct.getText().toString().isEmpty())
            Toast.makeText(requireContext(), "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show();
        else{
            Lesson lesson = new Lesson(binding.lesson.getText().toString(),
                    binding.timeStart.getText().toString()+"-"+binding.timeEnd.getText().toString(),
                    binding.desct.getText().toString(), binding.teacher.getText().toString(),
                    binding.audi.getText().toString(),rand());
            presenter.addLesson(lesson, currentDay);
        }
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
        binding.audi.setText(lesson.getAud());
        binding.lesson.setText(lesson.getName());
        binding.desct.setText(lesson.getDesct());
        binding.teacher.setText(lesson.getTeacher());
        binding.timeStart.setText(lesson.getTime().substring(0, lesson.getTime().indexOf('-')));
        binding.timeEnd.setText(lesson.getTime().substring(lesson.getTime().indexOf('-')+1,lesson.getTime().length()));

    }

    @Override
    public void readyUpdate() {
        Navigation.findNavController(requireView()).navigate(R.id.mainFragment);
    }
}
