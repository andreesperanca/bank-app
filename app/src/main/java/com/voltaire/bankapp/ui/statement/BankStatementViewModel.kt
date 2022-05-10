package com.voltaire.bankapp.ui.statement

import androidx.lifecycle.ViewModel
import com.voltaire.bankapp.data.BankRepository

class BankStatementViewModel : ViewModel() {

    fun findBankStatement(accountId : Int) =
        BankRepository.findBankStatement(accountId)
}