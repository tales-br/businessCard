package br.tales.businesscard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import br.tales.businesscard.App
import br.tales.businesscard.R
import br.tales.businesscard.data.Card
import br.tales.businesscard.databinding.ActivityAddBinding



class AddActivity : AppCompatActivity()
{
    private val addBinding by lazy {ActivityAddBinding.inflate(layoutInflater)}

    private val mainViewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(addBinding.root)
        setListeners()
    }

    private fun setListeners()
    {
        addBinding.ibClose.setOnClickListener{
            finish()
        }

        addBinding.btConfirm.setOnClickListener{
            val card = Card (
                cardName = addBinding.txtInName.editText?.text.toString(),
                cardCompany = addBinding.txtInCompany.editText?.text.toString(),
                cardEmail = addBinding.txtInEmail.editText?.text.toString(),
                cardPhone = addBinding.txtInPhone.editText?.text.toString(),
                cardColor = addBinding.txtInColor.editText?.text.toString()
                )
            mainViewModel.insert(card)
            Toast.makeText(this,getString(R.string.add_success),Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}