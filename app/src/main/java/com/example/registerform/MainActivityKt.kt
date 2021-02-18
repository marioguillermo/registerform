package com.example.registerform

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.registerform.databinding.ActivityMainKtBinding

class MainActivityKt : AppCompatActivity() {

    private lateinit var binding: ActivityMainKtBinding
    private var isPasswordHidden = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainKtBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivHidden.setOnClickListener {
            isPasswordHidden = !isPasswordHidden
            if(isPasswordHidden){
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

//        val intent = Intent(this, MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
//        intent.putExtra("KEY_VALUE", "VALUE")
//        startActivity(intent)
//        finish()

        binding.rbgGender.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rbFemale) {
                Toast.makeText(group.context, "Hello", Toast.LENGTH_SHORT).show()
            }
        }
        val selection = binding.rbgGender.checkedRadioButtonId
        binding.rbgGender.clearCheck()
        binding.rbgGender.check(R.id.rbFemale)
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