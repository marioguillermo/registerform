package com.example.registerform;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.registerform.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    boolean isPasswordHidden = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
    }


    public void runValidations(View view) {
        String name = binding.etName.getText().toString().trim();
        String lastName = binding.etLastName.getText().toString().trim();
        String mail = binding.etMail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        if (name.isEmpty()) {
            binding.etName.setError(getString(R.string.error_empty_field));
        }
        if (lastName.isEmpty()) {
            binding.etLastName.setError(getString(R.string.error_empty_field));
        }
        if (mail.isEmpty()) {
            binding.etMail.setError(getString(R.string.error_empty_field));
        }
        if (password.isEmpty()) {
            binding.etPassword.setError(getString(R.string.error_empty_field));
        }
    }

    private void runValidationAlternative() {
        ViewGroup viewGroup = binding.getRoot();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            EditText view = getViewAsEditTextOrNull(viewGroup.getChildAt(i));
            if (view != null) {
                verifyForEmptyField(view);
            }
        }

//        verifyForEmptyField(binding.etName);
//        verifyForEmptyField(binding.etLastName);
//        verifyForEmptyField(binding.etMail);
//        verifyForEmptyField(binding.etPassword);
    }

    private EditText getViewAsEditTextOrNull(View view) {
        if (view instanceof EditText) {
            return (EditText) view;
        } else {
            return null;
        }
    }

    private void verifyForEmptyField(EditText editText) {
        String fieldContent = getTrimmedText(editText);
        if (fieldContent.isEmpty()) {
            editText.setError(getString(R.string.error_empty_field));
        }
    }

    private String getTrimmedText(EditText editText) {
        return editText.getText().toString().trim();
    }

    private void validateEmailAddressField() {
        if (!Patterns.EMAIL_ADDRESS.matcher(getTrimmedText(binding.etMail)).matches()) {
            binding.etMail.setError(getString(R.string.error_invalid_email));
        }
    }

    private void validatePasswordLength() {
        String password = getTrimmedText(binding.etPassword);
        if (password.length() < 10) {
            binding.etPassword.setError(getString(R.string.error_password_lenght));
        }
    }

}