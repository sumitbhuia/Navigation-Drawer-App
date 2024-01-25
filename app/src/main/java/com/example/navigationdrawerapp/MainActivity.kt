package com.example.navigationdrawerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.navigation.NavigationView
// To create a fragment click on package -> new -> fragment -> fragment blank

class MainActivity : AppCompatActivity() {
    //naming variable for action bar toggle type , will connect later
    lateinit var toggle: ActionBarDrawerToggle
    //naming variable for  drawer layout , will connect later
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // connecting drawer layout
        drawerLayout = findViewById(R.id.drawerLayout)
        //connecting toggle of action bar
        //this is the format to initialize a toggle , same always
        toggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open,
            R.string.close
        )

        // add drawer layout -> navigation view (nav_view) to toggle expanded view
       drawerLayout.addDrawerListener(toggle)
        //this is responsible for converting the 3 dashed to an arrow and reverse
       toggle.syncState()

        //this line is important to make the 3 dashes visible , but without this this drawer is still accessible with corner swiping
       supportActionBar?.setDisplayHomeAsUpEnabled(true)

       //Functionalities for the drawer items

        //making an instance and connecting to nav_view (content inside drawer)
       val navView : NavigationView = findViewById(R.id.nav_view)

        //Set a listener that will be notified when a menu item is selected form nav_view
       navView.setNavigationItemSelectedListener {
           // condition item is checked / selected
           it.isChecked = true

           //when menuItem->itemId(home , login . message, settings) is selected
           when(it.itemId){
               //if the itemId is following replace it with the following
               R.id.home->{
                   //the replaceFragment is custom made  , a fragment is passed to take the frameLayout realEstate , and a title to change the layout name on the actionBar
                   // here it.title means the title set for the item in the nav_menu and convert it to string for now chances of error , even if it is already string
                   replaceFragment(HomeFragment(),it.title.toString())
               }
               R.id.message->{
                   replaceFragment(MessageFragment(),it.title.toString())
               }
               R.id.settings->{
                   replaceFragment(SettingsFragment(),it.title.toString())
               }
               R.id.login->{
                   replaceFragment(LoginFragment(),it.title.toString())
               }
           }
           true
       }


    }



    // custom replaceFragment function , take a fragment and a title string as input
    private fun replaceFragment (fragment: Fragment , title : String){
        // make an instance of fragment manager
        val fragmentManager = supportFragmentManager
        // an instance of fragment transaction
        val fragTrans = fragmentManager.beginTransaction()

        //the transaction instance will do the following , replace -> the single frameLayout realEstate , with the fragment passed in the parameter
        fragTrans.replace(R.id.fragment_layout_1 , fragment)
        //commit the changes
        fragTrans.commit()


        //after every successful transaction close the the drawer
        drawerLayout.closeDrawers()
        // and change the the Layout heading in the action bar to the (title) string passed in the parameter
        setTitle(title)
    }


    // think this part to be basic requirements
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}








