package com.example.registerform

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.registerform.databinding.ActivityMainKtBinding

class MainActivityKt : AppCompatActivity() {

    lateinit var binding: ActivityMainKtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainKtBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivHidden.setImageResource(R.drawable.ic_baseline_visibility_24)
    }




    fun runValidations(view: View) {
        val name = binding.etName.text.toString().trim()
        val lastName = binding.etLastName.text.toString().trim()
        val mail = binding.etMail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        if (name.isEmpty()) {
            binding.etName.error = getString(R.string.error_empty_field)
        }
        if (lastName.isEmpty()) {
            binding.etLastName.error = getString(R.string.error_empty_field)
        }
        if (mail.isEmpty()) {
            binding.etMail.error = getString(R.string.error_empty_field)
        }
        if (password.isEmpty()) {
            binding.etPassword.error = getString(R.string.error_empty_field)
        }

    }

    private fun verifyForEmptyField(editText: EditText) {
        if (editText.getTrimmed().isEmpty()) {
            editText.error = getString(R.string.error_empty_field)
        }
    }

    private fun validateEmailAddressField() {
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.etMail.getTrimmed()).matches()) {
            binding.etMail.error = getString(R.string.error_invalid_email)
        }
    }

    private fun validatePasswordLength() = with(binding.etPassword) {
        if (getTrimmed().length < 10) {
            error = getString(R.string.error_password_lenght)
        }
    }
}

private fun EditText.getTrimmed(): String {
    return text.toString().trim()
}