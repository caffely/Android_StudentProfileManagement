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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends Activity implements View.OnClickListener {

    EditText edtEmail;
    Button btnResetPassword;
    TextView linkSignUp;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        edtEmail = (EditText) findViewById(R.id.edt_email);
        findViewById(R.id.btn_reset_password).setOnClickListener(this);
        findViewById(R.id.link_sign_up).setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        int resId = v.getId();
        switch (resId) {
            case R.id.btn_reset_password:
                String email = edtEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    makeToast("Enter your registered email address");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                          makeToast("We have sent you instructions to reset your password!");
                            startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
                            finish();
                        } else {
                            makeToast("Failed to send reset email");
                        }
                    }
                });
                break;
            case R.id.link_sign_up:
                startActivity(new Intent(ResetPasswordActivity.this, SignupActivity.class));
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
