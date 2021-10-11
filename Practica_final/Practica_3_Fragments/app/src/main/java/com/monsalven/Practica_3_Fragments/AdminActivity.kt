package com.monsalven.Practica_3_Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.zxing.integration.android.IntentIntegrator
import com.squareup.picasso.Picasso


class AdminActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var backPressedTime = 0L
    private lateinit var auth: FirebaseAuth



    //val header_view = navigationView.findViewById<TextView>(R.id.textView)

    override fun onCreate(savedInstanceState: Bundle?) {
        /*Firebase*/
        val db = Firebase.firestore
        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_lend, R.id.nav_mypastlends, R.id.nav_my_profile, R.id.nav_inventory, R.id.nav_return_object, R.id.nav_historylends, R.id.detailhistoryFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val headerView = navigationView.getHeaderView(0)


        var navImage = headerView.findViewById<ImageView>(R.id.imageView) as ImageView


        var navUsername = headerView.findViewById<TextView>(R.id.textViewUserName) as TextView

        val navUserEmail = headerView.findViewById<TextView>(R.id.textViewUserEmail) as TextView
        val id_current_auth = auth.currentUser?.uid
        db.collection("users")
            .whereEqualTo("id", id_current_auth)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("nombre", "${document.id} => ${document.data.getValue("name")}")
                    navUsername.text = document.data.getValue("name").toString()
                    navUserEmail.text = document.data.getValue("email").toString()
                    Picasso.get().load(document.data.getValue("urlPicture").toString()).into(navImage)
                }
            }
            .addOnFailureListener { exception ->
                Log.w("nombre", "Error getting documents.", exception)
            }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show()
            } else {
                texto = result.contents;
                Toast.makeText(this, "El valor escaneado es: " + result.contents, Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                startActivity(Intent(this@AdminActivity, LoginActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
    /*
    override fun onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            finishAffinity()
            finish()
        }
        else {
            Toast.makeText(this, getString(R.string.back_pressed), Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }*/
}