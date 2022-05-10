package com.voltaire.bankapp.ui.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.voltaire.bankapp.adapter.BankStatementAdapter
import com.voltaire.bankapp.data.State
import com.voltaire.bankapp.databinding.ActivityStatementBinding
import com.voltaire.bankapp.models.Correntista
import com.voltaire.bankapp.models.Movimentacao
import com.voltaire.bankapp.models.TipoMovimentacao
import java.lang.IllegalArgumentException

class BankStatementActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ACCOUNT_HOLDER = "com.voltaire.bankapp.ui.statement.EXTRA_ACCOUNT_HOLDER"
    }

    private val binding by lazy {
        ActivityStatementBinding.inflate(layoutInflater)
    }

    private val accountHolder by lazy {
        intent.getParcelableExtra<Correntista>(EXTRA_ACCOUNT_HOLDER) ?: throw IllegalArgumentException()
    }

    private val viewModel by viewModels<BankStatementViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)

        findBankStatement()

        binding.srlBankStatement.setOnRefreshListener { findBankStatement() }
    }

    private fun findBankStatement() {

        viewModel.findBankStatement(accountHolder.id).observe(this) { state ->
            when (state) {
                is State.Error -> {
                    state.message?.let {Snackbar.make(binding.rvBankStatement, it, Snackbar.LENGTH_LONG).show()}
                    binding.srlBankStatement.isRefreshing = false }

                is State.Success -> { binding.rvBankStatement.adapter = state.data?.let { BankStatementAdapter(it) }
                binding.srlBankStatement.isRefreshing = false }

                State.Wait -> { binding.srlBankStatement.isRefreshing = true }
            }

        }
    }
}