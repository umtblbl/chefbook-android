package com.app.chefbook.model.serviceModel.requestModel

class ChangePassword (
    private val oldPassword: String,
    private val password: String,
    private val verifiedPassword: String
)