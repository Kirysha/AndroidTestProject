package com.example.tests.base;

public interface IOnBackPressedProvider {
    void addOnBackPressedListener(IOnBackPressedListener l);

    void removeOnBackPressedListener(IOnBackPressedListener l);
}
