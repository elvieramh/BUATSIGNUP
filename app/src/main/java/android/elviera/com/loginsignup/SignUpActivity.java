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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity {

    private EditText nama,username,password,alamat,noidentitas,notelp,email,pertanyaanrahasia,jawabanrahasia;
    private DatePicker tgllahir;
    private RadioGroup genderGroup;
    private Button btnDaftar;

    private ProgressDialog preloader;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference tbUser;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nama=(EditText)findViewById(R.id.nama);
        username=(EditText)findViewById(R.id.nama);
        password=(EditText)findViewById(R.id.nama);
        alamat=(EditText)findViewById(R.id.nama);
        noidentitas=(EditText)findViewById(R.id.nama);
        notelp=(EditText)findViewById(R.id.nama);
        email=(EditText)findViewById(R.id.nama);
        pertanyaanrahasia=(EditText)findViewById(R.id.nama);
        jawabanrahasia=(EditText)findViewById(R.id.nama);
        tgllahir=(DatePicker)findViewById(R.id.datePicker);
        genderGroup=(RadioGroup)findViewById(R.id.radioGroup);
        btnDaftar=(Button)findViewById(R.id.tmblDaftar);

        //Firebase
        firebaseDatabase=FirebaseDatabase.getInstance();
        tbUser=firebaseDatabase.getReference("Users");
        auth= FirebaseAuth.getInstance();

        preloader=new ProgressDialog(this);
    }

    public void register(View v){
        daftarkan();
    }

    private void daftarkan(){
        final String iNama,iUsername,iPassword,iAlamat,iNoId,iNoTelp,iEmail,iQS,iAS,iGender;
        Date iTglahir;
        iNama=nama.getText().toString();
        iUsername=username.getText().toString();
        iPassword=password.getText().toString();
        iAlamat=alamat.getText().toString();
        iNoId=noidentitas.getText().toString();
        iNoTelp=notelp.getText().toString();
        iEmail=email.getText().toString();
        iQS=pertanyaanrahasia.getText().toString();
        iAS=jawabanrahasia.getText().toString();
        //Gender
        if(genderGroup.getCheckedRadioButtonId()==R.id.rPria){
            iGender="Laki Laki";
        }else{
            iGender="Perempuan";
        }
        //Tanggal Lahir
        iTglahir=getDateFromDatePicker(tgllahir);

        final UsersModel mUser = new UsersModel(iNama,iUsername,iPassword,iAlamat,iNoId,iNoTelp,iEmail,iQS,iAS,iTglahir,iGender);

        preloader.setMessage("Please Wait,...");
        preloader.show();
        preloader.setCancelable(false);
        auth.createUserWithEmailAndPassword(iEmail,iPassword)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser mAuthUser = auth.getCurrentUser();
//                            mUser.setKey(mAuthUser.getUid());
                            String userId = tbUser.push().getKey();
                            tbUser.child(userId).setValue(mUser);
                            Toast.makeText(SignUpActivity.this, "Berhasil Daftar", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(SignUpActivity.this, ">>"+task.getResult().toString(), Toast.LENGTH_SHORT).show();
                            Log.d("INPUT::SHOW", iEmail+" p:"+iPassword);
                        Log.d("COMPLTLIST::AUTH","Result: "+task.getResult().toString());
                        }else{
                            Toast.makeText(SignUpActivity.this, "Gagal Daftar", Toast.LENGTH_SHORT).show();

                            Toast.makeText(SignUpActivity.this, ""+task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                            Log.d("COMPLTLIST::AUTH",""+task.getException().getMessage().toString());
                        }
                        preloader.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("ONFAILURELISTENER",""+e.getMessage().toString());
                    }
                })
        ;
    }

    public Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }


    /*
        * Method yang digunakan untuk Aksi Register
        *   Mendaftarkan user baru ke FirebaseDatabase
        * */
//    public void daft(){
//        String user = txtUsername.getText().toString();
//        String pin = txtPassword.getText().toString();
//        Log.d("CREDENTIAL::REGISTER","u:"+user+", p:"+pin);
//        if(TextUtils.isEmpty(user)){txtUsername.setError("Required");}
//        if(TextUtils.isEmpty(user)){txtPassword.setError("Required");}
//
//        //Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();
//
//        loading.setMessage("Wait a sec ...");
//        loading.show();
//        auth.createUserWithEmailAndPassword(user, pin).addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    loading.dismiss();
//                    Toast.makeText(SignInActivity.this, "Berhasil Register", Toast.LENGTH_SHORT).show();
//                    finish();
//                    startActivity(new Intent(SignInActivity.this,SignInActivity.class));
//                }else{
//                    loading.dismiss();
//                    Toast.makeText(SignInActivity.this, "Gagal Register", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
}
