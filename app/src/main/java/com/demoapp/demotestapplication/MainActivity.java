package com.demoapp.demotestapplication;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

//import androidx.activity.EdgeToEdge;
//import androidx.annotation.NonNull;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;

import android.text.TextUtils;
//import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
//import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editTextUsername = (EditText) findViewById(R.id.username);
        EditText editTextEmail = (EditText) findViewById(R.id.email);
        EditText editTextPassword = (EditText) findViewById(R.id.password);

// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
//        FirebaseAuth fireBase = FirebaseAuth.getInstance();

        MaterialButton regbtn = (MaterialButton) findViewById(R.id.buttonRegister);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, email ,password ;
                username = String.valueOf(editTextUsername.getText());

                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                if(TextUtils.isEmpty(username)) {
                    Toast.makeText(MainActivity.this,"Enter username",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this,"Enter email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this,"Enter password",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this ,"Registration Succesfully" ,Toast.LENGTH_SHORT).show();
                                    Intent intent =new Intent(MainActivity.this,HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Exception exception =task.getException();
                                    if (exception != null) {
                                        String errorMessage = exception.getMessage();
                                            if (errorMessage != null) {
                                                Log.e("Firebase error" ,errorMessage);
                                                Toast.makeText(MainActivity.this ,errorMessage ,Toast.LENGTH_SHORT).show();
                                            }
                                    }

                                }
                            }
                        });
            }
        });
    }

    // Méthode appelée lorsqu'on clique sur le bouton
    public void goToLoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}


//        // Quand l'utilisateur essaye de se connecter avec son compte
//        buttonRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username, email ,password ;
//                username = String.valueOf(editTextUsername.getText());
//
//                email = String.valueOf(editTextEmail.getText());
//                password = String.valueOf(editTextPassword.getText());
//                if(TextUtils.isEmpty(username)) {
//                    Toast.makeText(MainActivity.this,"Enter username",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(email)) {
//                    Toast.makeText(MainActivity.this,"Enter email",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(password)) {
//                    Toast.makeText(MainActivity.this,"Enter password",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                //Connection de l'application a firebase
//                fireBase.createUserWithEmailAndPassword(email,password)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if(task.isSuccessful()) {
//                                    Toast.makeText(MainActivity.this ,"Login Succesfully" ,Toast.LENGTH_SHORT).show();
//                                    Intent intent =new Intent(MainActivity.this,LoginActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                                else{
//                                    Toast.makeText(MainActivity.this ,"Authentication failed" ,Toast.LENGTH_SHORT).show();
//
//                                }
//                            }
//                        });
//            }
//        });




