package com.demoapp.demotestapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        TextInputEditText editEmail = findViewById(R.id.emailLogin);
        TextInputEditText editPassword = findViewById(R.id.passwordLogin);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        TextView signUp=(TextView) findViewById(R.id.signUp);
        TextView forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password ;
                email =String.valueOf(editEmail.getText());
                password=String.valueOf(editPassword.getText());
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this,"Enter email",Toast.LENGTH_SHORT).show();

                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"Enter password",Toast.LENGTH_SHORT).show();
                }
                //Connection de l'application a firebase
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this ,"Login Succesfully" ,Toast.LENGTH_SHORT).show();
                                    Intent intent =new Intent(LoginActivity.this,HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Exception exception =task.getException();
                                    if (exception != null) {
                                        String errorMessage = exception.getMessage();
                                        if (errorMessage != null) {
                                            Log.e("Firebase error" ,errorMessage);
                                            Toast.makeText(LoginActivity.this ,errorMessage ,Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                }
                            }
                        });

            }
        });
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
////        });
//        TextInputEditText editTextEmail , editTextPassword ;
//
//        Button signIn ;
//
//        FirebaseAuth fireBase =FirebaseAuth.getInstance();
//        editTextEmail = findViewById(R.id.emailLogin);
//        editTextPassword = findViewById(R.id.passwordLogin);
//
//        signUp =findViewById(R.id.signUp);
//        signIn =findViewById(R.id.buttonLogin);
//        // Quand l'utilisateur clique le boutton sign up dans la page login
//
//        signUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(LoginActivity.this , MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//
//        // Quand l'utilisateur essaye de se connecter avec son compte
//        signIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    String email ,password ;
//                    email = String.valueOf(editTextEmail.getText());
//                    password = String.valueOf(editTextPassword.getText());
//
//                    if(TextUtils.isEmpty(email)) {
//                    Toast.makeText(LoginActivity.this,"Enter email",Toast.LENGTH_SHORT).show();
//                    return;
//                    }
//                    if(TextUtils.isEmpty(password)) {
//                    Toast.makeText(LoginActivity.this,"Enter password",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                    //Connection de l'application a firebase
//                fireBase.signInWithEmailAndPassword(email,password)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if(task.isSuccessful()) {
//                                    Toast.makeText(LoginActivity.this ,"Login Succesfully" ,Toast.LENGTH_SHORT).show();
//                                    Intent intent =new Intent(LoginActivity.this,MainActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                                else{
//                                    Toast.makeText(LoginActivity.this ,"Authentication failed" ,Toast.LENGTH_SHORT).show();
//
//                                }
//                            }
//                        });
//            }
//        });


    }
}