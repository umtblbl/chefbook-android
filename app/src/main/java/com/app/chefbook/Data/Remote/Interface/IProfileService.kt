package com.app.chefbook.Data.Remote.Interface

import com.app.chefbook.Data.Remote.ServiceCallBack
import com.app.chefbook.Model.ServiceModel.ResponseModel.Profile

interface IProfileService {
    fun getProfile(accessToken :String, callBack: ServiceCallBack<Profile>)
}