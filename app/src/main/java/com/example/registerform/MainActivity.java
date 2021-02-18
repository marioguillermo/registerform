package com.example.registerform;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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
        binding.cbTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                binding.button.setEnabled(isChecked);
            }
        });
    }


    public void runValidations(View view) {
        verifyForEmptyField(binding.etName);
        verifyForEmptyField(binding.etLastName);
        verifyForEmptyField(binding.etMail);
        verifyForEmptyField(binding.etPassword);
        verifyForEmptyField(binding.etDay);
        verifyForEmptyField(binding.etMonth);
        verifyForEmptyField(binding.etYear);
        validateEmailAddressField();
        validatePasswordLength();
    }

    private void runValidationAlternative() {
        ViewGroup viewGroup = binding.getRoot();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            EditText view = getViewAsEditTextOrNull(viewGroup.getChildAt(i));
            if (view != null) {
                verifyForEmptyField(view);
            }
        }
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