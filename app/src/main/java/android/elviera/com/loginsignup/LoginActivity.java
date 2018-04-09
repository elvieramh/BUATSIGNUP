package android.elviera.com.loginsignup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    private ProgressDialog loading;

    private EditText txtUsername, txtPassword;
    private Button btnLogin,btnDaftar,btnForgotpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Instance FirebaseAuth (Object Firebase untuk Login)
        auth=FirebaseAuth.getInstance();
        //Komponen View Dialog Loading
        loading=new ProgressDialog(this);
        //Komponen View Input
        txtUsername = (EditText) findViewById(R.id.usernameEdit);
        txtPassword = (EditText) findViewById(R.id.passwordEdit);
        //Komponen View Button Login
        btnLogin = (Button)findViewById(R.id.tmblMasuk);
        btnDaftar= (Button)findViewById(R.id.tmblDaftar);
        btnForgotpass = (Button)findViewById(R.id.tmblLupaPass);
    }

    public void tmblMasuk(View v){
        login();
    }

    public void tmblDaftar(View v){
        startActivity(new Intent(this,SignUpActivity.class));
    }

    public void tmblLupaPass(View v){
        showToast("Aksi belum tersedia");
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /*
    * Method yang digunakan untuk Aksi Login
    *   Kredensial diambil dari FirebaseAuth
    * */
    public void login(){
        String user = txtUsername.getText().toString();
        String pin = txtPassword.getText().toString();
        Log.d("CREDENTIAL::LOGIN","u:"+user+", p:"+pin);
        if(TextUtils.isEmpty(user) || user.equals("") || user.length()<6){txtUsername.setError("Required & 6 Character"); return;}
        if(TextUtils.isEmpty(pin) || pin.equals("") || user.length()<6){txtPassword.setError("Required & 6 Character"); return;}

        loading.setMessage("Checking User ...");
        loading.show();

        auth.signInWithEmailAndPassword(user, pin)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            loading.dismiss();
                            Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                            //finish();
                            //startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }else{
                            loading.dismiss();
                            Toast.makeText(LoginActivity.this, "Username / Password Salah", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
