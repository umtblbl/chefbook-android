package com.app.chefbook.UI.LoginActivity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.chefbook.R
import com.app.chefbook.UI.RegisterActivity.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnRegister.setOnClickListener {

            val intent = Intent (this, RegisterActivity::class.java)
            val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this,  android.util.Pair<View, String> (txtLogin, "Login"))
            startActivity(intent, activityOptions.toBundle())


            //Pair[] pairs = new Pair[1];
              //  pairs[0] = new Pair<View,String> (tvLogin,"login");
                //ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                //startActivity(a,activityOptions.toBundle());

        }

    }
}
