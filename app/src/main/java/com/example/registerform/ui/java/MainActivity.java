package com.example.registerform.ui.java;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.registerform.R;
import com.example.registerform.databinding.ActivityMainBinding;
import com.example.registerform.domain.User;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    boolean isPasswordHidden = true;

    public static final String USER_EXTRA = "USER_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(this::runValidations);
        binding.ivHidden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPasswordHidden = !isPasswordHidden;
                if (isPasswordHidden) {
                    binding.ivHidden.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                    binding.etPassword.setTransformationMethod(new PasswordTransformationMethod());
                } else {
                    binding.ivHidden.setImageResource(R.drawable.ic_baseline_visibility_24);
                    binding.etPassword.setTransformationMethod(null);
                }
                binding.etPassword.setSelection(binding.etPassword.getText().length());
            }
        });
        binding.cbTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                binding.button.setEnabled(isChecked);
            }
        });
    }


    public void runValidations(View view) {
        boolean isEverythingOk = verifyForEmptyField(binding.etName)
            && verifyForEmptyField(binding.etLastName)
            && verifyForEmptyField(binding.etMail)
            && verifyForEmptyField(binding.etPassword)
            && validateEmailAddressField()
            && validatePasswordLength();
        if (isEverythingOk) {
            Intent intent = new Intent(this, SuccessActivity.class);
            User user = new User(
                getTrimmedText(binding.etName),
                getTrimmedText(binding.etLastName),
                getTrimmedText(binding.etMail),
                getTrimmedText(binding.etPassword),
                "", ""
            );
            intent.putExtra(USER_EXTRA, user);
            startActivity(intent);
            finish();
        }
    }

    private boolean verifyForEmptyField(EditText editText) {
        String fieldContent = getTrimmedText(editText);
        boolean isValid = !fieldContent.isEmpty();
        if (!isValid) {
            editText.setError(getString(R.string.error_empty_field));
        }
        return isValid;
    }

    private String getTrimmedText(EditText editText) {
        return editText.getText().toString().trim();
    }

    private boolean validateEmailAddressField() {
        boolean isValid = Patterns.EMAIL_ADDRESS.matcher(getTrimmedText(binding.etMail)).matches();
        if (!isValid) {
            binding.etMail.setError(getString(R.string.error_invalid_email));
        }
        return isValid;
    }

    private boolean validatePasswordLength() {
        String password = getTrimmedText(binding.etPassword);
        boolean isValid = password.length() >= 10;
        if (!isValid) {
            binding.etPassword.setError(getString(R.string.error_password_lenght));
        }
        return isValid;
    }
}