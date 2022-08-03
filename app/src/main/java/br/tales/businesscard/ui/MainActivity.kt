package br.tales.businesscard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.tales.businesscard.databinding.ActivityMainBinding
import androidx.activity.viewModels
import br.tales.businesscard.App
import br.tales.businesscard.adapter.CardAdapter
import br.tales.businesscard.util.Image

class MainActivity : AppCompatActivity()
{
    //Forma mais otimizada e recomendada pelo Google para fazer o binding
    //Faz-se necessário também a adição, no build.grade (app lvl) a feature do viewBinding
    private val mainBinding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    private val adapter by lazy {CardAdapter()}


    private val mainViewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(mainBinding.root)
            mainBinding.rvCards.adapter = adapter
        getAllCards()
        setListeners()
    }

    private fun setListeners()
    {
        mainBinding.fab.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
    }

    private fun getAllCards()
    {
        mainViewModel.getAll().observe(this){ Card ->
            adapter.submitList(Card)
        }

    }
}