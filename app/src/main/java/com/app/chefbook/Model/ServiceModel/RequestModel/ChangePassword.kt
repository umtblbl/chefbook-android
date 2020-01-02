package com.app.chefbook.Model.ServiceModel.RequestModel

class ChangePassword (
    private val oldPassword: String,
    private val password: String,
    private val verifiedPassword: String
)