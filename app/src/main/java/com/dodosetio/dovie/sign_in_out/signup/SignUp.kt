package com.dodosetio.dovie.sign_in_out.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dodosetio.dovie.R
import com.dodosetio.dovie.sign_in_out.signin.User
import com.dodosetio.dovie.util.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    lateinit var iUsername:String
    lateinit var iPassword:String
    lateinit var iName:String
    lateinit var iEmail:String

    lateinit var mFirebaseInstance:FirebaseDatabase
    lateinit var mDatabaseReference:DatabaseReference
    lateinit var mDatabase: DatabaseReference
    lateinit var preference:Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        preference = Preferences(this)

        btn_sign_up.setOnClickListener{

            iUsername = input_username.text.toString()
            iPassword = input_password.text.toString()
            iName = input_name.text.toString()
            iEmail = input_email.text.toString()

            mFirebaseInstance = FirebaseDatabase.getInstance()
            mDatabase = FirebaseDatabase.getInstance().getReference()
            mDatabaseReference = mFirebaseInstance.getReference("User")

            if(iUsername.equals("")){
                input_username.error = "Username is needed"
                input_username.requestFocus()
            }else if(iPassword.equals("")){
                input_password.error ="Password is needed"
                input_username.requestFocus()
            }else if(iName.equals("")){
                input_name.error ="Name is needed"
                input_username.requestFocus()
            }else if(iEmail.equals("")){
                input_email.error ="Email is needed"
                input_username.requestFocus()
            }else{
                saveUser(iUsername, iPassword, iName, iEmail)

            }

        }

        back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun saveUser(iUsername: String, iPassword: String, iName: String, iEmail: String) {
        var user = User()
        user.username=iUsername
        user.password = iPassword
        user.name = iName
        user.email = iEmail


        if(iUsername != null){
            checkUsername(iUsername,user)
            //below is own code
            preference.setValue("name", user.name.toString())
            preference.setValue("username", user.username.toString())
            preference.setValue("email", user.email.toString())
            preference.setValue("password",user.password.toString())
            preference.setValue("balance","0")
            //stop here
        }

    }

    private fun checkUsername(iUsername: String, data: User) {
        var exist = false
        mDatabaseReference.child(iUsername).addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@SignUp,p0.message,Toast.LENGTH_LONG).show()
            }
            override fun onDataChange(p0: DataSnapshot) {
               var user = p0.getValue(User::class.java)
                if(user == null){
                    startActivity(Intent(this@SignUp, SignUpPhoto::class.java).putExtra("name",data.name))
//                    mDatabaseReference.child(iUsername).setValue(data)
                    exist = true

                }else if(exist == false){
                    Toast.makeText(this@SignUp, "Username is already in use", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}