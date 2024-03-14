package com.demoapp.demotestapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);


        // Récupérer le TextView
        mAuth = FirebaseAuth.getInstance();
        TextView textViewUsername;
        textViewUsername = findViewById(R.id.textViewUsername);
        Button btnLogout = findViewById(R.id.buttonLogout);

        // Supposons que vous avez récupéré le nom d'utilisateur et stocké dans une variable nommée "username"
        String username = "Nom d'utilisateur récupéré";

        // Afficher le nom d'utilisateur dans le TextView
        textViewUsername.setText(username);

        //Logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                // Une fois déconnecté, vous pouvez rediriger l'utilisateur vers la page de connexion ou faire d'autres actions nécessaires.
                // Par exemple, pour rediriger vers une autre activité :
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    }
