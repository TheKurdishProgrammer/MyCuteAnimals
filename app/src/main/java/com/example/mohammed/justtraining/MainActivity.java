package com.example.mohammed.justtraining;


import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mohammed.justtraining.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;


public class MainActivity extends AppCompatActivity {


    ActivityMainBinding views;
    private ArrayList<Animal> myAnimals;
    private int currentIndex;
    private Uri targetUri;

    @Override
    protected void onCreate(Bundle savedIntancesState) {
        super.onCreate(savedIntancesState);
        views = DataBindingUtil.setContentView(this, R.layout.activity_main);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();


        String jsonString = sharedPreferences.getString("DATA", null);

        if (jsonString != null) {
            myAnimals = gson.fromJson(jsonString, new TypeToken<Collection<Animal>>() {
            }.getType());

            if (myAnimals != null) {
                Log.v("Is Null", ":Nope");
                if (myAnimals.size() != 0) {
                    currentIndex = myAnimals.size();
                    views.setMyAnimal(myAnimals.get(currentIndex-1));
                    views.imageBlock.setImageURI(Uri.parse(myAnimals.get(currentIndex-1).getImageUri()));
                } else
                    Log.v("but size is:", ":Zero");

            }
        } else
            myAnimals = new ArrayList<>();

    }


    public void Onclick(View v) {
        if (!myAnimals.isEmpty()) {
            switch (v.getId()) {
                case R.id.prev:
                    if (currentIndex-1 > 0) {
                        currentIndex--;
                    } else
                        Toast.makeText(this, "This is the First Animal", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.next:
                    if (currentIndex < myAnimals.size()) {
                        currentIndex++;
                    } else
                        Toast.makeText(this, "This is the last Animal", Toast.LENGTH_SHORT).show();
                    break;
            }

            views.setMyAnimal(myAnimals.get(currentIndex-1));
            views.imageBlock.setImageURI(Uri.parse(myAnimals.get(currentIndex-1).getImageUri()));
        } else
            Toast.makeText(this, "Album is Empty, Nothing to Show", Toast.LENGTH_SHORT).show();

    }

    public void addAnimal(View v) {

       Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(intent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
            targetUri = data.getData();


//            Bitmap bitmap = null;
//            try {
//                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }

        boolean flag = false;
        try {
            myAnimals.add(new Animal(views.included.nameED2.getText().toString(),
                    Integer.parseInt(views.included.ageEd2.getText().toString()),
                    Integer.parseInt(views.included.weightED2.getText().toString()),
                    views.included.colorED2.getText().toString(),
                    targetUri.toString()));
                    flag = !flag;

        } catch (Exception e) {
            Toast.makeText(this, "please Write the Weight or age in number", Toast.LENGTH_SHORT).show();
        }

        if(flag) {
            addAnimalStorage();
            showAnimalInfo();
            currentIndex++;
        }


    }

    private void showAnimalInfo() {
        views.setMyAnimal(myAnimals.get(currentIndex));
        views.imageBlock.setImageURI(Uri.parse(myAnimals.get(currentIndex).getImageUri()));
    }

    @Override
    protected void onStop() {
        super.onStop();

//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        Gson gson = new Gson();
//
//
//        String arrayList = gson.toJson(myAnimals);
//        editor.putString("DATA", arrayList);
//
//        editor.apply();
//
//        Toast.makeText(this, "Finished Saving", Toast.LENGTH_SHORT).show();

    }

    public void addAnimalStorage() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();


        String arrayList = gson.toJson(myAnimals);
        editor.putString("DATA", arrayList);

        editor.apply();

        Toast.makeText(this, "Finished Saving", Toast.LENGTH_SHORT).show();

    }
}
