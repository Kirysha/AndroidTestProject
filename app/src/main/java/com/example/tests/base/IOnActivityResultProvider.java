package com.example.tests.base;

import android.content.Intent;

import java.util.ArrayList;

public interface IOnActivityResultProvider {
    void runActivityForResult(Intent intent, int requestCode);

    /**
     * Получить доступ к объекту списка слушателей
     * @return
     */

    ArrayList<IOnActivityResultListener> getOnActivityResultListeners();

    /**
     * Подпись слушателя
     * @param listener
     */
    void addOnActivityResultListener(IOnActivityResultListener listener);

    /**
     * Описать слушателя
     * @param listener
     */
    void removeOnActivityResultListener(IOnActivityResultListener listener);

    /**
     * Полностью описать всех слушателй
     */
    void clearOnActivityResultListeners();
}
