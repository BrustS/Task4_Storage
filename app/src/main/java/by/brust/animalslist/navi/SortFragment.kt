package by.brust.animalslist.navi

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation

import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import by.brust.animalslist.R
import by.brust.animalslist.isUseRoom
import by.brust.animalslist.sortSetting

class SortFragment : PreferenceFragmentCompat() {

    private var sort_name : Preference? = null
    private var sort_age : Preference? = null
    private var sort_breed : Preference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.setting)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        // Init preference
         isUseRoom = prefs.getBoolean("switch_room",true)
         sort_name = findPreference<Preference>(getString(R.string.sort_name))
         sort_age = findPreference<Preference>(getString(R.string.sort_age))
         sort_breed = findPreference<Preference>(getString(R.string.sort_breed))
         settingsSetPoint()

        sort_name?.setOnPreferenceClickListener {
            sortSetting = "name"
            settingsSetPoint()

            true
        }

        sort_age?.setOnPreferenceClickListener {
            sortSetting = "age"
            settingsSetPoint()

            true
        }

        sort_breed?.setOnPreferenceClickListener {
            sortSetting = "breed"
            settingsSetPoint()

            true
        }
        val back_btn = findPreference<Preference>(getString(R.string.back_btn))
        back_btn?.setOnPreferenceClickListener {
                Navigation.findNavController(view).navigate(R.id.action_sortFragment_to_mainFragment)
            true
        }
    }

    private fun settingsSetPoint() {
        //Set default icon on Preference
        sort_name?.setIcon(R.drawable.ic_baseline_navigate_next_24)
        sort_age?.setIcon(R.drawable.ic_baseline_navigate_next_24)
        sort_breed?.setIcon(R.drawable.ic_baseline_navigate_next_24)

        when (sortSetting) {
            "name" ->  sort_name?.setIcon(R.drawable.ic_baseline_done_outline_24)
            "age" ->  sort_age?.setIcon(R.drawable.ic_baseline_done_outline_24)
            else -> sort_breed?.setIcon(R.drawable.ic_baseline_done_outline_24)
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }


}