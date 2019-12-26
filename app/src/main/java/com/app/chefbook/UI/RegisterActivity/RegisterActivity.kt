package com.app.chefbook.UI.RegisterActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.chefbook.DI.DataManager.componentActivity
import com.app.chefbook.Data.IDataManager
import com.app.chefbook.Model.ServiceModel.RequestModel.RegisterUser
import com.app.chefbook.R
import com.app.chefbook.UI.LoginActivity.LoginActivity
import com.app.chefbook.Utilities.isValidEmail
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {

    @Inject
    lateinit var dataManager: IDataManager
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        componentActivity.inject(this)
        viewModel = ViewModelProviders.of(this, RegisterViewModelFactory(dataManager)).get(RegisterViewModel::class.java)

        val animation = AnimationUtils.loadAnimation(this, R.anim.uptodown)
        rlayout.animation = animation

        viewModel.isAuth?.observe(this, Observer {

            when (it) {
                true -> {
                    //Intent to MainActivity
                }
                false -> {
                    //Error Dialog
                }
            }

        })

        txtLogin.setOnClickListener {startActivity(Intent(this, LoginActivity::class.java)) }

        btnRegister.setOnClickListener {

            layout_UserName.error = ""
            layout_Mail.error = ""
            layout_Password.error = ""
            layout_PasswordAgain.error = ""

            var registerState = true

            if(regUserName.length() < 8 || regUserName.length() > 16) {
                layout_UserName.error = "En az 8, en fazla 16 karakter olmalı"
                registerState = false
            }
            if(regMail.length() > 50 || !isValidEmail(regMail.text)) {
                layout_Mail.error = "En fazla 30 karakterden oluşan geçerli bir mail adresi girin"
                registerState = false
            }
            if(regPassword.length() < 8 || regPassword.length() > 16) {
                layout_Password.error = "En az 8, en fazla 16 karakter olmalı"
                registerState = false
            }
            if(regPasswordAgain.text.toString() != regPassword.text.toString()) {
                layout_PasswordAgain.error = "Şifre tekrarı uyuşmalıdır"
                registerState = false
            }

            if(registerState) {
                val registerUser = RegisterUser(
                    userName = regUserName.text.toString(),
                    mail = regMail.text.toString(),
                    password = regPassword.text.toString()
                )
                viewModel.registerUser(registerUser)

            }
        }


    }
}
