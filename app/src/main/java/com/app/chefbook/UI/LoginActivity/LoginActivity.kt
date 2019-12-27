package com.app.chefbook.UI.LoginActivity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import cn.pedant.SweetAlert.SweetAlertDialog
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

        val loadingDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE).setTitleText("Yükleniyor...")

        btnLogin.setOnClickListener {

            layout_Email.error = ""
            layout_Password.error = ""

            var registerState = true

            if(logEmail.length() > 50) {
                layout_Email.error = "En fazla 50 karakter olmalı"
                registerState = false
            }
            if(logPassword.length() < 8 || logPassword.length() > 16) {
                layout_Password.error = "En az 8, en fazla 16 karakter olmalı"
                registerState = false
            }

            if(registerState) {

                loadingDialog.show()

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

        viewModel.isAuth.observe(this, Observer {

            loadingDialog.cancel()

            when (it) {
                true -> {
                    SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Kayıt Başarılı!")
                        .show()
                }
                false -> {
                    SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Kayıt Başarısız!")
                        .setContentText(viewModel.errorResponse.toString())
                        .show()
                }
            }

        })

    }
}
