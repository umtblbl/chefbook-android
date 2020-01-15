package com.app.chefbook.ui.profileFragment

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.app.chefbook.DI.DataManager.componentFragment
import com.app.chefbook.Data.DataManager
import com.app.chefbook.R
import com.app.chefbook.ui.adapters.RecyclerViewOnClickListener
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject
import com.app.chefbook.databinding.FragmentProfileBinding
import com.app.chefbook.databinding.ToolbarProfileBinding
import com.app.chefbook.ui.adapters.profilePost.ProfilePostAdapter
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(), RecyclerViewOnClickListener {

    @Inject
    lateinit var dataManager: DataManager
    private lateinit var viewModel: ProfileViewModel
    private var imgToolbarSettings: ImageView? = null
    var toolbar: Toolbar? = null
    var userName: String = ""
    private lateinit var loadingDialog: SweetAlertDialog
    private var pictureResultValue: Boolean? = null
    private val profilePostAdapter: ProfilePostAdapter by lazy { ProfilePostAdapter(this) }
    private lateinit var profileBinding: FragmentProfileBinding
    private lateinit var toolbarBinding: ToolbarProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        profileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        componentFragment.inject(this)
        viewModel = ViewModelProviders.of(this, ProfileViewModelFactory(dataManager)).get(ProfileViewModel::class.java)

        initBindingObserver()
        initToolbar()
        viewModel.getProfile()

        viewModel.uploadProfilePictureState.observe(this, Observer {
            loadingDialog.cancel()
            when (it) {
                "200" -> {
                    viewModel.getProfile()
                    SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Profil Fotoğrafı güncellendi!")
                        .show()
                }
            }
        })

        viewModel.uploadCoverPictureState.observe(this, Observer {
            loadingDialog.cancel()
            when (it) {
                "200" -> {
                    viewModel.getProfile()
                    SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Kapak Fotoğrafı güncellendi!")
                        .show()
                }
            }
        })

        viewModel.profile.observe(this, Observer {
            initPostRecyclerView()
        })

        return profileBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imgToolbarSettings?.setOnClickListener {
            Log.d("TAG", "Settings Tıklandı")
            val action = ProfileFragmentDirections.actionProfileToSettings()
            action.userName = userName
            //val bundle = action.arguments
            //bundle.putString("userName", userName)
            findNavController().navigate(action).let { true }
        }

        imgChangeProfilePicture.setOnClickListener {

            pictureResultValue = true
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setCropShape(CropImageView.CropShape.OVAL)
                .setAspectRatio(10, 10)
                .setFixAspectRatio(true)
                .setBackgroundColor(R.color.colorBlue)
                .start(context!!, this)
        }

        imgChangeCoverPicture.setOnClickListener {

            pictureResultValue = false
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(3, 1)
                .setFixAspectRatio(true)
                .setBackgroundColor(R.color.colorBlue)
                .start(context!!, this)

        }

        loadingDialog = SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE).setTitleText("Yükleniyor...")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            val result = CropImage.getActivityResult(data)

            if (resultCode == RESULT_OK) {
                val resultUri = result.uri

                val file = File(resultUri.path)
                val reqFile = RequestBody.create(MediaType.parse("model/form-data"), file)
                val pictureResult = MultipartBody.Part.createFormData("model", file.name, reqFile)

                loadingDialog.show()

                if (pictureResultValue!!)
                    viewModel.uploadProfilePicture(pictureResult)
                else
                    viewModel.uploadCoverPicture(pictureResult)

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Log.d("Camera", result.error.toString())
            }
        }
    }

    private fun initToolbar() {

        toolbar = requireActivity().findViewById(R.id.toolbar_profile_content)
        toolbar?.menu?.clear()
        toolbar?.title = null

        toolbarBinding = ToolbarProfileBinding.inflate(LayoutInflater.from(context))

        imgToolbarSettings = toolbarBinding.root.findViewById(R.id.imgSettings)

        toolbarBinding.run {
            profileViewModel = viewModel
            lifecycleOwner = this@ProfileFragment
        }

        toolbar?.addView(toolbarBinding.root)
    }

    private fun initBindingObserver() {
        profileBinding.run {
            profileViewModel = viewModel
            lifecycleOwner = this@ProfileFragment
        }
    }

    private fun initPostRecyclerView() {
        profileBinding.recViewPost.run {
            layoutManager = LinearLayoutManager(
                profileBinding.root.context,
                LinearLayoutManager.VERTICAL,
                false
            )
            setHasFixedSize(true)
            adapter = profilePostAdapter
        }
    }

    override fun onClick(item: String) {
        Toast.makeText(context, "Id = $item", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        toolbar?.removeView(toolbarBinding.root)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getProfile()
    }
}
