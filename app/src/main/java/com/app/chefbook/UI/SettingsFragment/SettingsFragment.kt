package com.app.chefbook.UI.SettingsFragment


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.chefbook.R
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.coroutines.Dispatchers.Main

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnPasswordChange.setOnClickListener {
            val passwordChangeDialog = Dialog(context!!)
            passwordChangeDialog.setContentView(R.layout.dialog_password_change)
            passwordChangeDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            passwordChangeDialog.setCanceledOnTouchOutside(false)
            passwordChangeDialog.setTitle("Şifre Değiştir")
            passwordChangeDialog.show()
        }
    }

}
