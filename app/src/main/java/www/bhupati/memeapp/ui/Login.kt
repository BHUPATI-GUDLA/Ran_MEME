package www.bhupati.memeapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import www.bhupati.memeapp.MainActivity
import www.bhupati.memeapp.R

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        dont_haave_account.setOnClickListener{
            val intent = Intent(this,Signup ::class.java)
            startActivity(intent)
        }

        login_button.setOnClickListener{
//            val intent2 = Intent(this, MainActivity ::class.java)
//            startActivity(intent2)
           doLogin()
        }
    }

//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        updateUI(currentUser)
//    }
//
//    private fun updateUI(currentUser: FirebaseUser?) {
//
//    }

    private fun doLogin() {
        if (login_email.text.toString().isEmpty()) {
            login_email.error = "Please enter email"
            login_email.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(login_email.text.toString()).matches()) {
            login_email.error = "Please enter email"
            login_email.requestFocus()
            return
        }
        if (login_password.text.toString().isEmpty()) {
            login_password.error = "Please enter valid password"
            login_password.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(login_email.text.toString(), login_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

        private fun updateUI(currentUser: FirebaseUser?) {
            if(currentUser != null){
                startActivity((Intent(this,MainActivity::class.java)))
                //finish()
            }else{
                Toast.makeText(baseContext,"Login Failed",
                    Toast.LENGTH_SHORT).show()
            }

        }


//
//    private fun updateUI(currentUser: FirebaseUser?) {
//        if(currentUser != null) {
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//
//        }else {
//            Toast.makeText(baseContext, "Login Failed. ",
//                Toast.LENGTH_SHORT).show()
//        }
//}
}