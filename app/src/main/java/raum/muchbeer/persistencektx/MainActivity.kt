package raum.muchbeer.persistencektx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI


//Differ in android is essential to avoid blocking the ui thread
//MEaning the ui is waiting not doing anything waiting for data to be come
//You should not do long running thread in the UI or Main Thread
//Courotine(help 1.Asynchronize 2. Non blocking 3. use of suspend fun help async task to run sequential

/*Courotine needs:
1. Job ( is anything can be cancel, cancel job cancel sibling
2. Dispatcher (dispatcher sends off coroutine Main.thread, IO share pool of thread
3. Scope (combine the information)*/


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

        val navController= findNavController(R.id.nav_host)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
}