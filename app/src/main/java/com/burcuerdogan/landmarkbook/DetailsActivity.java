package com.burcuerdogan.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.burcuerdogan.landmarkbook.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    //View Binding (Jetpack) -> Daha verimli olduğu için kullanırız.
    //Bizim için bazı sınıflar oluşturur ve bu sınıflar içerisinde findviewbyID vb işlemler hazır yapılmış olur.

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //https://developer.android.com/topic/libraries/view-binding#java
        //Inflate: Bizim XML/Layout'umuz ile kodumuzu bağlamaya çalıştığımız yerdir.
        //Sonrasında bağladığımız şeyi "View view" ile bir görünüme çeviririz.
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Intent intent = getIntent();

        //Casting
        //Landmark selectedLandmark = (Landmark) intent.getSerializableExtra("landmark");
        //Landmark selectedLandmark = chosenLandmark;

        Singleton singleton = Singleton.getInstance();
        Landmark selectedLandmark = singleton.getSentLandmark();

        binding.landmarkText.setText(selectedLandmark.name);
        binding.countryText.setText(selectedLandmark.country);
        binding.imageView.setImageResource(selectedLandmark.image);
        binding.contentText.setText(selectedLandmark.content);




    }
}