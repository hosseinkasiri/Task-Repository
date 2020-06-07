package com.example.task.model;

import android.content.Context;
import android.widget.Toast;

public class Toaster {

    public static void makeToast(Context context , String string){

        Toast.makeText(context , string , Toast.LENGTH_SHORT).show();

    }




}
