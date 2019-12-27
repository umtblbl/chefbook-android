package com.app.chefbook.UI.LoginActivity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.app.chefbook.DI.DataManager.componentActivity
import com.app.chefbook.Data.IDataManager
import com.app.chefbook.Model.ServiceModel.RequestModel.LoginUser
import com.app.chefbook.R
import com.app.chefbook.UI.RegisterActivity.RegisterActivity
import com.app.chefbook.UI.RegisterActivity.RegisterViewModel
import com.app.chefbook.UI.RegisterActivity.RegisterViewModelFactory
import com.app.chefbook.Utilities.isValidEmail
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var dataManager: IDataManager
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        componentActivity.inject(this)
        viewModel = ViewModelProviders.of(this, LoginViewModelFactory(dataManager)).get(LoginViewModel::class.java)


        btnLogin.setOnClickListener {

            layout_Email.error = ""
            layout_Password.error = ""

            var registerState = true

            if(logEmail.length() > 50 || !isValidEmail(logEmail.text)) {
                layout_Email.error = "En fazla 30 karakterden oluşan geçerli bir mail adresi girin"
                registerState = false
            }
            if(logPassword.length() < 8 || logPassword.length() > 16) {
                layout_Password.error = "En az 8, en fazla 16 karakter olmalı"
                registerState = false
            }

            if(registerState) {
                val loginUser = LoginUser(
                    email = logEmail.text.toString(),
                    password = logPassword.text.toString()
                )
                viewModel.loginUser(loginUser)
            }
        }

        btnRegister.setOnClickListener {
            val intent = Intent (this, RegisterActivity::class.java)
            val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this,  android.util.Pair<View, String> (txtLogin, "Login"))
            startActivity(intent, activityOptions.toBundle())
        }

    }
}
