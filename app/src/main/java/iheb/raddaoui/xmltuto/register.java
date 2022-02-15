package iheb.raddaoui.xmltuto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText email,password;
    Button register;
    FirebaseApp firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebase= FirebaseApp.initializeApp(this);
        mAuth = firebase.FirebaseAuth();



        email = (EditText)findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        register = (Button)findViewById(R.id.register);
        
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

        // ...
        // Initialize Firebase Auth
    }

    private void signup() {
        String em=email.getText().toString();
        String p=password.getText().toString();
        mAuth.createUserWithEmailAndPassword(em, p)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(register.this,DashBoardActivity.class);
                            finish();

                        } else {
                            Toast.makeText(register.this, "Signup Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}