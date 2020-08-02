package com.dodosetio.dovie.sign_in_out.signup

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dodosetio.dovie.home.HomeActivity
import com.dodosetio.dovie.R
import com.dodosetio.dovie.sign_in_out.signin.User
import com.dodosetio.dovie.util.Preferences
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up_photoscreen.*
import kotlinx.android.synthetic.main.activity_sign_up_photoscreen.back
import java.util.*

class SignUpPhoto : AppCompatActivity(),PermissionListener{

    val Request_Image_capture = 1
    var statusAdd:Boolean =false
    lateinit var filePath: Uri

    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var preferences: Preferences
    lateinit var mFirebaseInstance: FirebaseDatabase
    lateinit var mDatabaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photoscreen)

        preferences = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference()
        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        msg_welcome.text = "Welcome,\n" +intent.getStringExtra("name")

        btn_upload.setOnClickListener{
            if(statusAdd){
                statusAdd = false
                btn_continue.visibility = View.VISIBLE
                btn_upload.setImageResource(R.drawable.ic_btn_upload)
                profile_pic.setImageResource(R.drawable.user_pic)
            }else{
//                Dexter.withActivity(this)
//                    .withPermission(Manifest.permission.CAMERA)
//                    .withListener(this)
//                    .check()
                ImagePicker.with(this)
                    .galleryOnly()
                    .start()

            }
        }

        btn_skip.setOnClickListener{
            var user = User()
            user.username=preferences.getValue("username")
            user.password = preferences.getValue("password")
            user.name = preferences.getValue("name")
            user.email = preferences.getValue("email")
            user.balance = preferences.getValue("balance")
            user.url = ""
            mDatabaseReference.child(preferences.getValue("username").toString()).setValue(user)
            preferences.setValue("url","")
            startActivity(Intent(this@SignUpPhoto, HomeActivity::class.java))
        }

        back.setOnClickListener {
            onBackPressed()
        }

        btn_continue.setOnClickListener{
            var user = User()
            user.username=preferences.getValue("username")
            user.password = preferences.getValue("password")
            user.name = preferences.getValue("name")
            user.email = preferences.getValue("email")
            user.balance = preferences.getValue("balance")


            if(filePath !=null){
                var progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading...")
                progressDialog.show()

                var ref = storageReference.child("images/"+ UUID.randomUUID().toString())
                ref.putFile(filePath).addOnSuccessListener{
                    progressDialog.dismiss()
                    Toast.makeText(this,"Uploaded", Toast.LENGTH_LONG).show()

                    ref.downloadUrl.addOnSuccessListener{
                        user.url = it.toString()
                        mDatabaseReference.child(preferences.getValue("username").toString()).setValue(user)

                    }
                    finishAffinity()
                    preferences.setValue("url",filePath.toString())
                    startActivity(Intent(this@SignUpPhoto, HomeActivity::class.java))
                }.addOnFailureListener{
                    progressDialog.dismiss()
                    Toast.makeText(this,"Failed", Toast.LENGTH_LONG).show()
                }.addOnProgressListener {
                    taskSnapshot ->  var progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                    progressDialog.setMessage("Upload " +progress.toInt() + " %")
                }

            }else{

            }
        }
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{
            takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also{
                startActivityForResult(takePictureIntent,Request_Image_capture)
            }
        }
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {

    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this,"Fail uploading profile picture", Toast.LENGTH_LONG).show()
    }


//    @SuppressLint("MissingSuperCall")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if(requestCode == Request_Image_capture && resultCode == Activity.RESULT_OK ){
//            var bitmap = data?.extras?.get("data") as Bitmap
//
//            statusAdd = true
//
//            filePath = data.getData()!!
//            Glide.with(this)
//                .load(bitmap)
//                .apply(RequestOptions.circleCropTransform())
//                .into(profile_pic)
//
//            btn_continue.visibility = View.VISIBLE
//            btn_upload.setImageResource(R.drawable.ic_btn_delete)
//        }
//    }
//}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode ==  Activity.RESULT_OK ){
            statusAdd = true
            filePath = data?.data!!
            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(profile_pic)

            btn_continue.visibility = View.VISIBLE
            btn_upload.setImageResource(R.drawable.ic_btn_delete)
        }else if(resultCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(this,ImagePicker.getError(data), Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Task Canceled", Toast.LENGTH_LONG).show()
        }
    }
}