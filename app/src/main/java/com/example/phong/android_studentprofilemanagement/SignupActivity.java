package com.example.phong.android_studentprofilemanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phong.android_studentprofilemanagement.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends Activity implements View.OnClickListener {

    EditText edtFullName;
    EditText edtEmail;
    EditText edtPassword;
    Button btnSignUp;
    TextView linkLogIn;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseInstance;
    DatabaseReference userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseInstance = FirebaseDatabase.getInstance();
        userDatabase = firebaseInstance.getReference("user");

        edtFullName = (EditText) findViewById(R.id.edt_full_name);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        findViewById(R.id.btn_sign_up).setOnClickListener(this);
        findViewById(R.id.link_log_in).setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        switch (resId) {
            case R.id.btn_sign_up:
                final String fullName = edtFullName.getText().toString();
                final String email = edtEmail.getText().toString().trim();
                final String password = edtPassword.getText().toString();

                if (TextUtils.isEmpty(fullName)) {
                    makeToast("Enter full name!");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    makeToast("Enter email address!");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    makeToast("Enter password!");
                    return;
                }
                if (password.length() < 6) {
                    edtPassword.setError(getString(R.string.minimum_password));
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        makeToast("Create user " + (task.isSuccessful() == true ? "successfully" : "failed"));
                        if (!task.isSuccessful()) {
                            makeToast("Authentication failed");
                        } else {
                            User user = new User();
                            user.setId(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            user.setEmail(email);
                            user.setFullName(fullName);
                            user.setRole(AppConfig.ROLE_STUDENT);
                            userDatabase.child(user.getId()).setValue(user);
                            startActivity(new Intent(SignupActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
                break;
            case R.id.link_log_in:
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                break;
            default:
        }
    }

    private void makeToast(String message) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }
}
