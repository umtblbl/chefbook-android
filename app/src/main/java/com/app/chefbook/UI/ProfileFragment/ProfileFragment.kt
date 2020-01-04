package com.app.chefbook.UI.ProfileFragment

import android.Manifest.*
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.app.chefbook.DI.DataManager.componentFragment
import com.app.chefbook.Data.DataManager
import com.app.chefbook.R
import com.app.chefbook.UI.Adapters.ProfilePostAdapter
import com.app.chefbook.UI.Adapters.RecyclerViewOnClickListener
import com.app.chefbook.Utilities.getInflateLayout
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.awaitAll
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ProfileFragment : Fragment(), RecyclerViewOnClickListener {

    @Inject
    lateinit var dataManager: DataManager
    private lateinit var viewModel: ProfileViewModel
    private var toolbarProfile: View? = null
    private var imgToolbarSettings: ImageView? = null
    private var txtUserNameToolbar: TextView? = null
    var toolbar: Toolbar? = null
    lateinit var profilePostAdapter: ProfilePostAdapter
    var userName: String = ""
    private lateinit var loadingDialog: SweetAlertDialog
    private var pictureResultValue: Boolean? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        componentFragment.inject(this)
        viewModel = ViewModelProviders.of(this, ProfileViewModelFactory(dataManager))
            .get(ProfileViewModel::class.java)

        initializeToolbar()

        viewModel.getProfile()

        viewModel.profile.observe(this, Observer {

            userName = it.userName
            txtSharingCount.text = it.postCount.toString()
            txtFollowersCount.text = it.followerCount.toString()
            txtFullName.text = it.fullName
            txtBiography.text = it.description
            txtUserNameToolbar?.text = it.userName.toString()

            Picasso.with(context).load(it.profilePicture).into(imgProfilePicture)
            Picasso.with(context).load(it.cover).into(imgProfileCover)

            profilePostAdapter = ProfilePostAdapter(it, this)
            recViewPost.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recViewPost.adapter = profilePostAdapter
            profilePostAdapter.notifyDataSetChanged()
        })

        viewModel.uploadProfilePictureState.observe(this, Observer {
            loadingDialog.cancel()
            when(it) {
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
            when(it) {
                "200" -> {
                    viewModel.getProfile()
                    SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Kapak Fotoğrafı güncellendi!")
                        .show()
                }
            }
        })

        return view
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
                .start(context!!, this)

        }

        imgChangeCoverPicture.setOnClickListener {

            pictureResultValue = false
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(context!!, this)


            //val intent = Intent(this.context, CameraActivity::class.java)
            //startActivity(intent)

/*
            val builder = StrictMode.VmPolicy.Builder()
            StrictMode.setVmPolicy(builder.build())

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (ContextCompat.checkSelfPermission(
                        context!!,
                        permission.CAMERA
                    ) == PackageManager.PERMISSION_DENIED
                    || ContextCompat.checkSelfPermission(
                        context!!,
                        permission.WRITE_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_DENIED
                ) {

                    val permisson = arrayOf(permission.CAMERA, permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permisson, PERMISSION_CODE)

                } else {
                    openCamera()
                }

            }
*/
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

                if(pictureResultValue!!)
                    viewModel.uploadProfilePicture(pictureResult)
                else
                    viewModel.uploadCoverPicture(pictureResult)

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Log.d("Camera", result.error.toString())
            }
        }
/*
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE_CODE -> {
                    try {

                        val pictureUri = MediaStore.Images.Media.getBitmap(context?.contentResolver, Uri.parse(currentPhotoPath))

                        //startCropImageIntent()

                    } catch (ex: IOException) {
                        ex.printStackTrace()
                    }
                }
                2 -> {
                    val picture = data?.extras?.getParcelable<Bitmap>("data")
                    imgProfileCover.setImageBitmap(picture)
                }
            }
        }
        */

    }


    private fun initializeToolbar() {

        toolbar = requireActivity().findViewById(R.id.toolbar_profile_content)
        toolbar?.menu?.clear()
        toolbar?.title = null

        toolbarProfile = context?.let { getInflateLayout(it, R.layout.toolbar_profile) }

        imgToolbarSettings = toolbarProfile?.findViewById(R.id.imgSettings)
        txtUserNameToolbar = toolbarProfile?.findViewById(R.id.txtUserId)

        toolbar?.addView(toolbarProfile)
    }

    override fun onClick(item: String) {
        Toast.makeText(context, "Id = $item", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        toolbar?.removeView(toolbarProfile)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getProfile()
    }
}


/*
    private fun openCamera() {

        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(context?.packageManager) != null) {
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                Log.i("CAMERA", "IOException $ex")
            }

            if (photoFile != null) {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile))
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE_CODE)
            }
        }
    }
    private fun startCropImageIntent() {
        try {
            val cropIntent = Intent("com.android.camera.action.CROP")

            cropIntent.setDataAndType(Uri.parse(currentPhotoPath), "image/*")
            // set crop properties
            cropIntent.putExtra("crop", "true")
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 2)
            cropIntent.putExtra("aspectY", 1)
            // indicate output X and Y
            cropIntent.putExtra("outputX", 256)
            cropIntent.putExtra("outputY", 256)
            // retrieve data on return
            cropIntent.putExtra("return-data", true)
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, 2)
        }
        catch (ex:IOException) {
            Toast.makeText(context, "Crop not support", Toast.LENGTH_SHORT).show()
        }
    }
    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss".format(Date()))
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(imageFileName, ".jpg", storageDir)
        currentPhotoPath = "file:" + image.absolutePath
        return image
    }
*/