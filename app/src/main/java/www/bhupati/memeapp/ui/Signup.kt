package www.bhupati.memeapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*
import www.bhupati.memeapp.R


class Signup : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth = FirebaseAuth.getInstance()



        sign_button.setOnClickListener{
//            val intent1 = Intent(this, Login::class.java)
//            startActivity(intent1)
           check1()
        }
        wants_login.setOnClickListener{
            val intent2 = Intent(this, Login::class.java)
            startActivity(intent2)
            Toast.makeText(baseContext, "wants to login",
                Toast.LENGTH_SHORT).show()
        }


}

     private fun check1(){
        if(signup_email.text.toString().isEmpty()){
            signup_email.error = "Please enter email"
            signup_email.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(signup_email.text.toString()).matches()){
            signup_email.error = "Please enter email"
            signup_email.requestFocus()
            return
        }
        if(sign_password.text.toString().isEmpty()){
            sign_password.error = "Please enter valid password"
            sign_password.requestFocus()
            return
        }
         auth.createUserWithEmailAndPassword(signup_email.text.toString(), sign_password.text.toString())
             .addOnCompleteListener(this) { task ->
                 if(task.isSuccessful) {
                     startActivity(Intent(this,Login :: class.java))
                     finish()
                 }
                 else {
                     Toast.makeText(baseContext, "Signup Failed",
                         Toast.LENGTH_SHORT).show()
                 }
             }
     }

}
