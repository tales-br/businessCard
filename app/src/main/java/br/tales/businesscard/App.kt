package br.tales.businesscard

import android.app.Application
import br.tales.businesscard.data.AppDataBase
import br.tales.businesscard.data.CardDAO
import br.tales.businesscard.data.CardRepository

/**Classe iniciadora da aplicação
 *
 */
class App: Application()
{
    val database by lazy {AppDataBase.getDataBase(this)}
    val repository by lazy {CardRepository(database.cardDao())}
}