package com.example.registerform.ui.kotlin

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.registerform.R
import com.example.registerform.databinding.ActivityMainBinding
import com.example.registerform.domain.User

class MainActivityKt : AppCompatActivity() {
    companion object {
        const val USER_EXTRA = "USER_EXTRA"
    }

    private lateinit var binding: ActivityMainBinding
    private var isPasswordHidden = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { view: View -> runValidations(view) }
        binding.ivHidden.setOnClickListener {
            isPasswordHidden = !isPasswordHidden
            if (isPasswordHidden) {
                binding.ivHidden.setImageResource(R.drawable.ic_baseline_visibility_off_24)
                binding.etPassword.transformationMethod = PasswordTransformationMethod()
            } else {
                binding.ivHidden.setImageResource(R.drawable.ic_baseline_visibility_24)
                binding.etPassword.transformationMethod = null
            }
        }

        binding.cbTerms.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.button.isEnabled = isChecked
        }
    }


    private fun runValidations(view: View) {
        val isEverythingOK = verifyForEmptyField(binding.etName)
            && verifyForEmptyField(binding.etLastName)
            && verifyForEmptyField(binding.etPassword)
            && verifyForEmptyField(binding.etMail)
            && validatePasswordLength()
            && validateEmailAddressField()
        if (isEverythingOK) {
            val intent = Intent(this, SuccessActivityKt::class.java)
            val user = User(
                name = binding.etName.getTrimmed(),
                lastName = binding.etLastName.getTrimmed(),
                password = binding.etPassword.getTrimmed(),
                email = binding.etMail.getTrimmed()
            )
            intent.putExtra(USER_EXTRA, user)
            startActivity(intent)
            finish()
        }

    }

    private fun verifyForEmptyField(editText: EditText): Boolean {
        val isValid = editText.getTrimmed().isNotEmpty()
        if (!isValid) {
            editText.error = getString(R.string.error_empty_field)
        }
        return isValid
    }

    private fun validateEmailAddressField(): Boolean {
        val isValid = Patterns.EMAIL_ADDRESS.matcher(binding.etMail.getTrimmed()).matches()
        if (!isValid) {
            binding.etMail.error = getString(R.string.error_invalid_email)
        }
        return isValid
    }

    private fun validatePasswordLength(): Boolean {
        val isValid = binding.etPassword.getTrimmed().length >= 10
        if (!isValid) {
            binding.etPassword.error = getString(R.string.error_password_lenght)
        }
        return isValid
    }
}

private fun EditText.getTrimmed(): String {
    return text.toString().trim()
}