package com.voltaire.bankapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.voltaire.bankapp.databinding.ActivityMainBinding
import com.voltaire.bankapp.models.Correntista
import com.voltaire.bankapp.ui.statement.BankStatementActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            var idAccount = binding.txtIdAccount.text
            if (idAccount != null && idAccount.isNotBlank() && idAccount.isNotEmpty()) {
                val accountHolder = Correntista(idAccount.toString().toInt())

                val intent = Intent (this, BankStatementActivity::class.java).apply {
                    putExtra(BankStatementActivity.EXTRA_ACCOUNT_HOLDER, accountHolder )
                }
                startActivity(intent)

            }else {
                toastCreator("Digite um id válido.")
            }
        }
    }

    private fun toastCreator(s: String) {
        Toast.makeText(this, s , Toast.LENGTH_SHORT).show()
    }
}