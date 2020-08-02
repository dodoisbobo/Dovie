package com.dodosetio.dovie.sign_in_out.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dodosetio.dovie.home.HomeActivity
import com.dodosetio.dovie.R
import com.dodosetio.dovie.sign_in_out.signup.SignUp
import com.dodosetio.dovie.util.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignIn : AppCompatActivity() {

    lateinit var iUsername:String
    lateinit var iPassword:String

    lateinit var mDatabase:DatabaseReference
    lateinit var preference:Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preference = Preferences(this)
        preference .setValue("status","0")

        if(preference.getValue("status").equals("1")){
            finishAffinity()

            startActivity(Intent(this@SignIn, HomeActivity::class.java))
        }
        btn_sign_in.setOnClickListener{
            iUsername = InpUsername.text.toString()
            iPassword = InpPwd.text.toString()
            if(iUsername.equals("")){
                InpUsername.error = "Username is needed"
                InpUsername.requestFocus()
            }else if (iPassword.equals("")){
                InpPwd.error = "Password is needed"
                InpPwd.requestFocus()
            }else{
                pushLogin(iUsername,iPassword)
            }
        }
        btn_sign_up.setOnClickListener{
            startActivity(Intent(this@SignIn, SignUp::class.java))
        }
    }
    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object:ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignIn,databaseError.message, Toast.LENGTH_LONG).show()
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)

                if(user == null){
                    Toast.makeText(this@SignIn,"No Username found", Toast.LENGTH_LONG).show()
                }else{
                    if(user.password.equals(iPassword)){
                        preference.setValue("name", user.name.toString())
                        preference.setValue("username", user.username.toString())
                        preference.setValue("url", user.url.toString())
                        preference.setValue("email", user.email.toString())
                        preference.setValue("balance", user.balance.toString())
                        preference.setValue("status", "1")
                        startActivity(Intent(this@SignIn, HomeActivity::class.java))
                    }else{
                        Toast.makeText(this@SignIn,"Password does not match", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }
}