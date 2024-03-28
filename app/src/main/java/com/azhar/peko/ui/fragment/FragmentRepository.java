package com.azhar.peko.ui.fragment;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.peko.R;
import com.azhar.peko.adapter.RepositoryAdapter;
import com.azhar.peko.model.search.ModelSearchData;
import com.azhar.peko.viewmodel.UserViewModel;

public class FragmentRepository extends Fragment {
    ModelSearchData modelSearchData;
    UserViewModel repositoryViewModel;
    RepositoryAdapter repositoryAdapter;
    RecyclerView rvListRepository;
    ConstraintLayout layoutEmpty;
    ProgressDialog progressDialog;
    String strUsername;
    ImageView btnFilter;

    public FragmentRepository() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repository, container, false);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            progressDialog = new ProgressDialog(getContext());
        }
        progressDialog.setTitle("Repository...");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Searching for data");

        rvListRepository = view.findViewById(R.id.rvListRepository);
        layoutEmpty = view.findViewById(R.id.layoutEmpty);
        btnFilter = view.findViewById(R.id.btnFilter);


        assert this.getArguments() != null;
        modelSearchData = this.getArguments().getParcelable("modelSearchData");
        if (modelSearchData != null) {
            strUsername = modelSearchData.getLogin();
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            repositoryAdapter = new RepositoryAdapter(getContext());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            rvListRepository.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        rvListRepository.setAdapter(repositoryAdapter);
        rvListRepository.setHasFixedSize(true);

        //method set viewmodel
        repositoryViewModel = new ViewModelProvider((ViewModelStoreOwner) this, new ViewModelProvider.NewInstanceFactory()).get(UserViewModel.class);
        repositoryViewModel.setUserRepo(strUsername);
        progressDialog.show();

        repositoryViewModel.getUserRepo().observe(getViewLifecycleOwner(), RepositoryDataCap -> {
            if (RepositoryDataCap.size() != 0) {
                layoutEmpty.setVisibility(View.GONE);
                repositoryAdapter.setRepoList(RepositoryDataCap);
            } else {
                layoutEmpty.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Toast.makeText(getContext(), "No Repository Found!", Toast.LENGTH_SHORT).show();
                }
            }
            progressDialog.dismiss();
        });

        // Assuming btnOpenMenu is your button
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a PopupMenu
                PopupMenu popupMenu = new PopupMenu(requireContext(), v);

                // Inflate the menu resource
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.filter_menu, popupMenu.getMenu());

                // Set an item click listener for the menu items
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Handle menu item click here
                        switch (item.getItemId()) {
                            case R.id.filter_last_update:
                                // Handle menu item 1 click
                                return true;
                            case R.id.filter_by_fork:
                                repositoryViewModel.sortByStarsDescending();

                                return true;
                            case R.id.filter_by_star:
                                // Handle menu item 2 click
                                return true;
                            // Add more cases for other menu items as needed
                            default:
                                return false;
                        }
                    }
                });

                // Show the PopupMenu
                popupMenu.show();
            }
        });


        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.filter_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


}
