package com.example.tests.base;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public abstract class AActivityForResult extends ABaseActivity
  implements IOnActivityResultProvider, IORequestPermissionResultProvider, IOnBackPressedProvider {

    /**
     * Слушатели события Activity.onActivityResult
     */
    private ArrayList<IOnActivityResultListener>
            onActivityResultListeners = new ArrayList<>();

    /**
     * Слушатели события Activity.onRequestPermissionsResult
     */
    private ArrayList<IOnRequestPermissionsResultListener>
            onRequestPermissionsResultListeners = new ArrayList<>();

    /**
     * Слушатели события Activity.onBackPressed
     */
    private ArrayList<IOnBackPressedListener>
            onBackPressedListeners = new ArrayList<>();

    /**
     * Реализация интерфейса IOnActivityResultProvider
     */

    @Override
    public void runActivityForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    @Override
    public ArrayList<IOnActivityResultListener> getOnActivityResultListeners() {
        return onActivityResultListeners;
    }

    @Override
    public void addOnActivityResultListener(IOnActivityResultListener listener) {
        if (onActivityResultListeners == null) // Только для UNIT-тестирования
            onActivityResultListeners = new ArrayList<>();
        if (!onActivityResultListeners.contains(listener))
            onActivityResultListeners.add(listener);
    }

    @Override
    public void removeOnActivityResultListener(IOnActivityResultListener listener) {
        onActivityResultListeners.remove(listener);
    }

    @Override
    public void clearOnActivityResultListeners() {
        onActivityResultListeners.clear();
    }

    /**
     * end
     */

    /**
     * Реализация интерфейса IOnRequestPermissionsResultProvider
     */

    @Override
    public float checkPermissions(@NonNull int[] permissions) {

        int size = permissions.length;
        if (size == 0)
            return 0;

        float countSuccess = 0;
        for (int permission : permissions) {
            if (permission == PackageManager.PERMISSION_GRANTED)
                countSuccess++;
        }

        return countSuccess / size;
    }

    @Override
    public float checkPermissions(@NonNull String[] permissions) {

        int size = permissions.length;
        if (size == 0)
            return 0;

        float countSuccess = 0;
        for (String permission : permissions) {
            if (checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED)
                countSuccess++;
        }

        return countSuccess / size;
    }

    @Override
    public boolean runRequestPermissions(final @NonNull String[] permissions, final int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkPermissions(permissions) == 1) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        int size = permissions.length;
                        int[] result = new int[size];
                        for (int i = 0; i < size; i++)
                            result[i] = PackageManager.PERMISSION_GRANTED;
                        onRequestPermissionsResult(requestCode, permissions, result);
                    }
                });

                return false;
            }

            requestPermissions(permissions, requestCode);
            return true;
        }

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                int size = permissions.length;
                int[] result = new int[size];
                for (int i = 0; i < size; i++)
                    result[i] = PackageManager.PERMISSION_GRANTED;
                onRequestPermissionsResult(requestCode, permissions, result);
            }
        });

        return false;
    }

    @Override
    public ArrayList<IOnRequestPermissionsResultListener> getOnRequestPermissionsResultListeners() {
        return onRequestPermissionsResultListeners;
    }

    @Override
    public void addOnRequestPermissionsResultListener(
            IOnRequestPermissionsResultListener listener) {
        if (onRequestPermissionsResultListeners == null) // Только для UNIT-тестирования
            onRequestPermissionsResultListeners = new ArrayList<>();
        if (!onRequestPermissionsResultListeners.contains(listener))
            onRequestPermissionsResultListeners.add(listener);
    }

    @Override
    public void removeOnRequestPermissionsResultListener(
            IOnRequestPermissionsResultListener listener) {

        onRequestPermissionsResultListeners.remove(listener);
    }

    @Override
    public void clearOnRequestPermissionsResultListeners() {
        onRequestPermissionsResultListeners.clear();
    }

    /**
     * end
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * Код перебора объектов изменет т.к. в прошлой версии кода,
         *   если слушатель отписался, то следующий за ним не будет обработан
         */
        ArrayList<IOnActivityResultListener> list = getOnActivityResultListeners();
        for (int i = 0, length = list.size(); i < length; ) {
            IOnActivityResultListener listener = list.get(i);
            if (listener.onActivityResult(requestCode, resultCode, data)) {
                return;
            }
            i++;
            if (list.size() != length) {
                if (list.size() < length) { // Слушатель отписался
                    i--;
                }
                length = list.size();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ArrayList<IOnRequestPermissionsResultListener> list = getOnRequestPermissionsResultListeners();
        for (int i = 0, length = list.size(); i < length; ) {
            IOnRequestPermissionsResultListener listener = list.get(i);
            if (listener.onRequestPermissionsResult(requestCode, permissions, grantResults))
                return;

            i++;
            if (list.size() != length) {
                if (list.size() < length) // Слушатель отписался
                    i--;
                length = list.size();
            }
        }
    }

    @Override
    public void addOnBackPressedListener(IOnBackPressedListener listener) {
        if (!onBackPressedListeners.contains(listener))
            onBackPressedListeners.add(listener);
    }

    @Override
    public void removeOnBackPressedListener(IOnBackPressedListener listener) {
        onBackPressedListeners.remove(listener);
    }

    @Override
    public void onBackPressed() {

        for (int i = 0, length = onBackPressedListeners.size(); i < length; ) {
            IOnBackPressedListener listener = onBackPressedListeners.get(i);
            if (listener.onBackPressed())
                return;

            i++;
            int size = onBackPressedListeners.size();
            if (size != length) {
                if (size < length) // Слушатель отписался
                    i--;
                length = size;
            }
        }

        super.onBackPressed();
    }

}
