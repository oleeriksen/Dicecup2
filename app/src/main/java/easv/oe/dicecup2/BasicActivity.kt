package easv.oe.dicecup2

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

/**
 * Implements the thing all the basic activities need (i.e Main menu)
 */

open class BasicActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        SetupMainMenu(menu)
        return true
    }

    //region MainMenu

    private fun SetupMainMenu(menu: Menu) {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.MainMenuWelcome -> {
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.MainMenuDice -> {
                val intent = Intent(this, DiceActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //endregion
}