package com.example.tests.base;

import androidx.annotation.NonNull;

public interface IOnRequestPermissionsResultListener {

    /**
     * Передача входных даннх слушателю
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @return true - данные пригодились, иначе false
     */

    boolean onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
