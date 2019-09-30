package br.com.fiap.appglasseek.utility;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class TransportView {
    public static class Transport {
        public static FragmentManager fragmentManager;
        public static Context context;
        public static Activity activity;
        public static Fragment fragment;

        public static FragmentManager getFragmentManager() {
            return fragmentManager;
        }

        public static void setFragmentManager(FragmentManager fragmentManager) {
            Transport.fragmentManager = fragmentManager;
        }

        public static Context getContext() {
            return context;
        }

        public static void setContext(Context context) {
            Transport.context = context;
        }

        public static Activity getActivity() {
            return activity;
        }

        public static void setActivity(Activity activity) {
            Transport.activity = activity;
        }

        public static Fragment getFragment() {
            return fragment;
        }

        public static void setFragment(Fragment fragment) {
            Transport.fragment = fragment;
        }

    }

}
