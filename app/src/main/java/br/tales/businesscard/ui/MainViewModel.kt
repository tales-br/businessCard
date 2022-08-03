package br.tales.businesscard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.tales.businesscard.data.Card
import br.tales.businesscard.data.CardRepository

class MainViewModel(private val cardRepository: CardRepository): ViewModel()
{
    fun insert (card: Card)
    {
        cardRepository.insert(card)
    }

    fun getAll() : LiveData<List<Card>>
    {
         return cardRepository.getAll()
    }
}

class MainViewModelFactory (private val repository: CardRepository):
    ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if(modelClass.isAssignableFrom(MainViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException ("Unknow viewModel class")
    }

}