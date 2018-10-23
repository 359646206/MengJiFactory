package com.pet.test;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.util.Log;
import com.pet.test.utils.Const;

/**
 * Created by yangzhengguang on 2018/9/27.
 */

public class PrefFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener{
    //    private ListPreference mVersionPreference;
    private EditTextPreference mCsqPreference;
    private EditTextPreference mSwPreference;
    private SharedPreferences mSharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
        mSharedPreferences = getPreferenceScreen().getSharedPreferences();
        mCsqPreference = (EditTextPreference) findPreference(Const.KEY_CSQ);
        mSwPreference = (EditTextPreference) findPreference(Const.KEY_SW_VERSION);

//        mVersionPreference = (ListPreference) findPreference(Const.KEY_VERSION);
//        mVersionPreference.setOnPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        String csq = mSharedPreferences.getString(Const.KEY_CSQ,Const.DEFAULT_CSQ);
        Log.i("yy","csq="+csq);
        mSharedPreferences.edit().putString(Const.KEY_CSQ,csq).commit();
        mCsqPreference.setSummary(csq);

        String sw = mSharedPreferences.getString(Const.KEY_SW_VERSION,Const.DEFAULT_SW_VERSION);
        Log.i("yy","sw="+sw);
        mSharedPreferences.edit().putString(Const.KEY_SW_VERSION, sw).commit();
        mSwPreference.setSummary(sw);


//        String version = mSharedPreferences.getString(Const.KEY_VERSION,Const.DEFAULT_VERSION);
//        Log.i("yy","version="+version);
//        CharSequence[] entries=mVersionPreference.getEntries();
//        int index=mVersionPreference.findIndexOfValue((String)version);
//        mVersionPreference.setValue(version);
//        mVersionPreference.setSummary(entries[index]);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.i("yy","key="+key);
        if(Const.KEY_CSQ.equals(key)) {
            mCsqPreference.setSummary(mCsqPreference.getText());
        } else if(Const.KEY_SW_VERSION.equals(key)){
            mSwPreference.setSummary(mSwPreference.getText());
        }

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if(Const.KEY_VERSION.equals(preference.getKey())) {
//            String value = (String) newValue;
//            CharSequence[] entries=mVersionPreference.getEntries();
//            int index=mVersionPreference.findIndexOfValue((String)newValue);
//            mVersionPreference.setSummary(entries[index]);
//            mVersionPreference.setValue(value);
        }
        return false;
    }
}
