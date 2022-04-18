package com.github.muellerma.coffee

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.github.muellerma.coffee.databinding.ActivityPreferenceBinding

class PreferenceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreferenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.commit {
            add(binding.activityContent.id, MainSettingsFragment())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    class MainSettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            addPreferencesFromResource(R.xml.pref_main)
        }
    }
}

fun SharedPreferences.getTimeout() : Int {
    return getString("timeout", "0")?.toInt() ?: 0
}

fun PreferenceFragmentCompat.getPreference(key: String) =
    preferenceManager.findPreference<Preference>(key)!!