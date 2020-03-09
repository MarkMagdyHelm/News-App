package com.Mark.news.ui.settings

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.Mark.news.R
import com.Mark.news.constants.Constants
import com.Mark.news.ui.news.view.NewsListsActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_setting.*


class SettingActivity : AppCompatActivity(), View.OnClickListener {
    val categoriesList = arrayListOf<String>()

    var lang: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)


        //check if user open setting or this is the firstTime
        if(intent.getIntExtra("comeFrom",0)==0)
        CheckSittingsEntered()
        //setup counters spinnner
        setupSpinner()
        //set onclick listenrt
        initOnClick()
    }

    private fun CheckSittingsEntered() {
        val user = PreferenceManager.getDefaultSharedPreferences(this)
            .getString(Constants.USER_SETTINGS, "empty")
        if (!user!!.equals("empty"))
            navigatation()
    }

    private fun initOnClick() {
        business_bt.setOnClickListener(this)
        entertainment_bt.setOnClickListener(this)
        general_bt.setOnClickListener(this)
        health_bt.setOnClickListener(this)
        science_btn.setOnClickListener(this)
        sports_btn.setOnClickListener(this)
        technology_btn.setOnClickListener(this)
        next.setOnClickListener(this)
    }

    private fun setupSpinner() {
        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.Langs)
        ) //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        lang_spinner.adapter = spinnerArrayAdapter
        lang_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                lang = resources.getStringArray(R.array.Langs).get(position)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.business_bt -> {
                checkVisiblity(business_check)
                checkCategoriesList(business_bt.text.toString())
            }
            R.id.entertainment_bt -> {
                checkVisiblity(entertainment_check)
                checkCategoriesList(entertainment_bt.text.toString())
            }
            R.id.general_bt -> {
                checkVisiblity(general_check)
                checkCategoriesList(general_bt.text.toString())
            }
            R.id.health_bt -> {
                checkVisiblity(health_check)
                checkCategoriesList(health_bt.text.toString())
            }
            R.id.science_btn -> {
                checkVisiblity(science_check)
                checkCategoriesList(science_btn.text.toString())
            }
            R.id.sports_btn -> {
                checkVisiblity(sports_check)
                checkCategoriesList(sports_btn.text.toString())
            }
            R.id.technology_btn -> {
                checkVisiblity(technology_check)
                checkCategoriesList(technology_btn.text.toString())
            }
            R.id.next -> {
                if(categoriesList.size>0)
                saveSettings(SettingsObj(lang!!, categoriesList,"publishedAt"))
                else
                    Toast.makeText(this, getString(R.string.validation), Toast.LENGTH_LONG).show()
            }

        }


    }

    //validate user when selecting categoris
    private fun checkCategoriesList(categoryName: String) {
        if (categoriesList.contains(categoryName))
            categoriesList.remove(categoryName)
        else
            if (categoriesList.size < 3)
                categoriesList.add(categoryName)
            else
                Toast.makeText(this, getString(R.string.validation), Toast.LENGTH_LONG).show()
    }

    //check on selected imag visiblity
    fun checkVisiblity(v: View) {
        if (v.getVisibility() == View.VISIBLE) {
            v.visibility = View.INVISIBLE
        } else {
            if (categoriesList.size < 3)
                v.visibility = View.VISIBLE
            else
                Toast.makeText(this, getString(R.string.validation), Toast.LENGTH_LONG).show()
        }
    }

    //saving setting in shared prefrances
    fun saveSettings(obj: SettingsObj) {
        val gson = Gson()
        val objString = gson.toJson(obj)
        PreferenceManager.getDefaultSharedPreferences(this).edit()
            .putString(Constants.USER_SETTINGS, objString).apply()

        navigatation()
    }

    //naviagte to next screen
    fun navigatation() {

        val intent = Intent(this, NewsListsActivity::class.java)
        startActivity(intent)
        finish()
    }

}
