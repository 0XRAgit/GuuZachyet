package ru.wnfx.framework.ui.main.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.wnfx.framework.App;
import ru.wnfx.framework.R;
import ru.wnfx.framework.data.model.Lesson;
import ru.wnfx.framework.databinding.FragmentMainBinding;
import ru.wnfx.framework.databinding.FragmentProfileBinding;
import ru.wnfx.framework.ui.base.BaseFragment;
import ru.wnfx.framework.ui.main.profile.ProfileContract;
import ru.wnfx.framework.ui.main.profile.ProfilePresenter;

public class MainFragment extends BaseFragment implements MainContract.View, TabLayout.OnTabSelectedListener {

    FragmentMainBinding binding;

    @Inject
    MainPresenter presenter;
    String currentDay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);

        setViewClickListeners();

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("ПН"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("ВТ"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("СР"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("ЧТ"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("ПТ"));

        binding.tabLayout.setOnTabSelectedListener(this);
        currentDay = "monday";
        presenter.getCollections(currentDay);

        return binding.getRoot();
    }

    private void setViewClickListeners() {
        binding.add.setOnClickListener(v->{
            Bundle bundle = new Bundle();
            bundle.putString("day",currentDay);
            bundle.putString("type", "add");
            Navigation.findNavController(requireView()).navigate(R.id.addEdit, bundle);
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
    public void readyDay(ArrayList<Lesson> lessons) {
        MainAdapter mainAdapter=new MainAdapter(lessons, this);
        LinearLayoutManager llm = new LinearLayoutManager(requireContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        binding.mainRv.setLayoutManager(llm);
        binding.mainRv.setAdapter(mainAdapter);
    }

    @Override
    public void chooseDay(Lesson lessons) {
        Bundle bundle = new Bundle();
        bundle.putString("id", lessons.getId());
        bundle.putString("day", currentDay);
        Navigation.findNavController(requireView()).navigate(R.id.lookFragment, bundle);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()){
            case 0:
                currentDay = "monday";
                break;
            case 1:
                currentDay = "tuesday";
                break;
            case 2:
                currentDay = "wednesday";
                break;
            case 3:
                currentDay = "thursday";
                break;
            case 4:
                currentDay = "friday";
                break;
        }
        presenter.getCollections(currentDay);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
