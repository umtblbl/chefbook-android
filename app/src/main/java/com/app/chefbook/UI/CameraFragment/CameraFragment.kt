package com.app.chefbook.UI.CameraFragment


import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.app.chefbook.R
import com.app.chefbook.UI.PostInitiatorFragment.PostInitiatorFragment
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.VideoResult
import com.otaliastudios.cameraview.controls.Facing
import com.otaliastudios.cameraview.controls.Flash
import com.otaliastudios.cameraview.controls.Mode
import kotlinx.android.synthetic.main.fragment_camera.*
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream


class CameraFragment : Fragment() {

    val REQUEST_EXTERNAL_STORAGE_CODE: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_camera, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        cameraView.setLifecycleOwner(viewLifecycleOwner)
        verifyPermission()


        btnCamera.setOnClickListener {
            Log.d("TESTTT2", "PICTURE")
            cameraView.mode = Mode.PICTURE
            cameraView.takePicture()
        }

        btnCamera.setOnLongClickListener {

            Log.d("TESTTT2", "LONG")
            val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES),"/video.mp4")
            cameraView.mode = Mode.VIDEO
            cameraView.takeVideo(file)
            chronometer.base = SystemClock.elapsedRealtime()
            lnCounter.visibility = View.VISIBLE
            chronometer.start()

            return@setOnLongClickListener true
        }

        btnCamera.setOnTouchListener { v, event ->

            Log.d("TESTTT", "gestureDedector false")
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                }

                MotionEvent.ACTION_UP -> {
                    Toast.makeText(context, "UP - STOP", Toast.LENGTH_SHORT).show()
                    Log.d("TESTTT2", "UP")
                    cameraView.stopVideo()
                    chronometer.stop()
                    lnCounter.visibility = View.GONE
                    chronometer.base = SystemClock.elapsedRealtime()
                }
            }

            v?.onTouchEvent(event) ?: true
        }

        cameraView.addCameraListener(object : CameraListener() {
            override fun onPictureTaken(result: PictureResult) {
                super.onPictureTaken(result)
                val data = result.data
                val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
                val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"/image.jpeg")
                val os = BufferedOutputStream(FileOutputStream(file))
                activity?.runOnUiThread {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
                    os.close()
                }


            }

            override fun onVideoTaken(result: VideoResult) {
                super.onVideoTaken(result)
                Log.d("VIDEOZEL", "onVideoTaken")
                val data = result.file
            }
        })

        imgLoopCamera.setOnClickListener {
            if (cameraView.facing == Facing.BACK)
                cameraView.facing = Facing.FRONT
            else
                cameraView.facing = Facing.BACK
        }

        imgFlash.setOnClickListener {
            when (cameraView.flash) {

                Flash.ON -> {
                    cameraView.flash = Flash.AUTO
                    imgFlash.setImageResource(R.drawable.ic_flash_auto_white_24dp)
                }
                Flash.AUTO -> {
                    cameraView.flash = Flash.OFF
                    imgFlash.setImageResource(R.drawable.ic_flash_off_white_24dp)
                }
                else -> {
                    cameraView.flash = Flash.ON
                    imgFlash.setImageResource(R.drawable.ic_flash_on_white_24dp)
                }
            }
        }


    }

    private fun verifyPermission() {
        val PERMISSIONS_STORAGE_CODE = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val permission = ActivityCompat.checkSelfPermission(
            activity!!,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity!!,
                PERMISSIONS_STORAGE_CODE,
                REQUEST_EXTERNAL_STORAGE_CODE
            )
        }
    }

    override fun onResume() {
        super.onResume()
        cameraView.open()
    }

    override fun onPause() {
        super.onPause()
        cameraView.close()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraView.destroy()
    }
}



