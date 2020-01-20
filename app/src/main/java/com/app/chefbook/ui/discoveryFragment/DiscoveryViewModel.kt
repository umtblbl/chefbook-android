package com.app.chefbook.ui.discoveryFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.data.remote.manager.userManager.IUserManager
import com.app.chefbook.data.remote.manager.userManager.UserManager
import com.app.chefbook.model.serviceModel.responseModel.SearchResult

class DiscoveryViewModel(var userManager: IUserManager):ViewModel() {

    var searchResult = MutableLiveData<List<SearchResult>>()

    fun getSearchResult(q: String) {
        userManager.getSearchResult(q, object : ServiceCallBack<List<SearchResult>> {
            override fun onResponse(response: List<SearchResult>) {
                searchResult.postValue(response)
            }

            override fun onError(message: String) {

            }
        })
    }
}