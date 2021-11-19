package dev.kingbond.notify.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.kingbond.notify.repository.RepositoryClass

class ViewModelFactory(val repo:RepositoryClass):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelClass(repo) as T
    }
}