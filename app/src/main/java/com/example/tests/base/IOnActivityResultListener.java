package com.example.tests.base;

import android.content.Intent;

public interface IOnActivityResultListener {
        /**
         * Данные слушателю
         * @param requestCode
         * @param resultCode
         * @param intent
         * @return TRUE -данные нужны, не нужны FALSE
         */
        boolean onActivityResult(int requestCode, int resultCode, Intent intent);
    }