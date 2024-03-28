package com.azhar.peko.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.azhar.peko.model.follow.ModelFollow;
import com.azhar.peko.model.repo.RepositoryDataCap;
import com.azhar.peko.model.search.ModelSearch;
import com.azhar.peko.model.search.ModelSearchData;
import com.azhar.peko.model.user.ModelUser;
import com.azhar.peko.networking.ApiClient;
import com.azhar.peko.networking.ApiInterface;

import java.util.ArrayList;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    private MutableLiveData<ArrayList<ModelSearchData>> modelSearchMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelFollow>> modelFollowersMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ModelFollow>> modelFollowingMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<RepositoryDataCap>> modelRepoMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ModelUser> modelUserMutableLiveData = new MutableLiveData<>();
    private ArrayList<RepositoryDataCap> repositoryList = new ArrayList<>();


    //method search user
    public void setSearchUser(String query) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ModelSearch> call = apiService.searchUser(query);
        call.enqueue(new Callback<ModelSearch>() {
            @Override
            public void onResponse(Call<ModelSearch> call, Response<ModelSearch> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    ArrayList<ModelSearchData> items = new ArrayList<>(response.body().getModelSearchData());
                    modelSearchMutableLiveData.setValue(items);
                }
            }

            @Override
            public void onFailure(Call<ModelSearch> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    //method view detail user
    public void setUserDetail(String strUsername) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ModelUser> call = apiService.detailUser(strUsername);
        call.enqueue(new Callback<ModelUser>() {

            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelUserMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    public LiveData<ArrayList<ModelSearchData>> getResultList() {
        return modelSearchMutableLiveData;
    }

    public LiveData<ModelUser> getUserList() {
        return modelUserMutableLiveData;
    }

    public LiveData<ArrayList<ModelFollow>> getFollowersUser() {
        return modelFollowersMutableLiveData;
    }

    //method get followers
    public void setFollowersUser(String strUsername) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<ModelFollow>> call = apiService.followersUser(strUsername);
        call.enqueue(new Callback<ArrayList<ModelFollow>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelFollow>> call, Response<ArrayList<ModelFollow>> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelFollowersMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelFollow>> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    public LiveData<ArrayList<ModelFollow>> getFollowingUser() {
        return modelFollowingMutableLiveData;
    }

    //method get following
    public void setFollowingUser(String strUsername) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<ModelFollow>> call = apiService.followingUser(strUsername);
        call.enqueue(new Callback<ArrayList<ModelFollow>>() {
            @Override
            public void onResponse(Call<ArrayList<ModelFollow>> call, Response<ArrayList<ModelFollow>> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelFollowingMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ModelFollow>> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    public LiveData<ArrayList<RepositoryDataCap>> getUserRepo() {
        return modelRepoMutableLiveData;
    }

    // setting user repo
    public void setUserRepo(String strUsername) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<RepositoryDataCap>> call = apiService.userRepository(strUsername);
        call.enqueue(new Callback<ArrayList<RepositoryDataCap>>() {
            @Override
            public void onResponse(Call<ArrayList<RepositoryDataCap>> call, Response<ArrayList<RepositoryDataCap>> response) {
                if (!response.isSuccessful()) {
                    Log.e("response", response.toString());
                } else if (response.body() != null) {
                    modelRepoMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RepositoryDataCap>> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    public void sortByStarsDescending() {
        // Create a custom comparator to compare repositories by stars count in descending order
        Comparator<RepositoryDataCap> comparator = new Comparator<RepositoryDataCap>() {
            @Override
            public int compare(RepositoryDataCap repo1, RepositoryDataCap repo2) {
                // Compare the stars count of two repositories in descending order
                return Integer.compare(repo2.getForksCount(), repo1.getForksCount());
            }
        };

        // Sort the repository list using the custom comparator
        repositoryList.sort(comparator);

        // Update the LiveData object with the sorted list
        modelRepoMutableLiveData.setValue(repositoryList);
    }


}