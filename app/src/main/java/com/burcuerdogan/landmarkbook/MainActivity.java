package com.burcuerdogan.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.burcuerdogan.landmarkbook.databinding.ActivityDetailsBinding;
import com.burcuerdogan.landmarkbook.databinding.ActivityMainBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Landmark> landmarkArrayList;

    //View Binding (Jetpack)
    //https://developer.android.com/topic/libraries/view-binding
    private ActivityMainBinding binding;
    //static Landmark chosenLandmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        landmarkArrayList = new ArrayList<>();

        //Data

        Landmark pisa = new Landmark("Tower of Pisa", "Italy","The height of the tower is 55.86 metres (183 feet 3 inches) from the ground on the low side and 56.67 m (185 ft 11 in) on the high side. The width of the walls at the base is 2.44 m (8 ft 0 in). Its weight is estimated at 14,500 tonnes (16,000 short tons). The tower has 296 or 294 steps; the seventh floor has two fewer steps on the north-facing staircase.", R.drawable.pisa);
        Landmark eiffel = new Landmark("The Eiffel Tower", "France","It is named after the engineer Gustave Eiffel, whose company designed and built the tower. Locally nicknamed \"La dame de fer\" (French for \"Iron Lady\"), it was constructed from 1887 to 1889 as the centerpiece of the 1889 World's Fair and was initially criticised by some of France's leading artists and intellectuals for its design, but it has become a global cultural icon of France and one of the most recognisable structures in the world.", R.drawable.eiffel);
        Landmark colosseum = new Landmark("The Colosseum","Italy","The Colosseum is built of travertine limestone, tuff (volcanic rock), and brick-faced concrete. It could hold an estimated 50,000 to 80,000 spectators at various points in its history, having an average audience of some 65,000; it was used for gladiatorial contests and public spectacles including animal hunts, executions, re-enactments of famous battles, and dramas based on Roman mythology, and briefly mock sea battles. The building ceased to be used for entertainment in the early medieval era.", R.drawable.colleseum);
        Landmark londonBridge = new Landmark("London Bridge", "UK","Several bridges named London Bridge have spanned the River Thames between the City of London and Southwark, in central London. The current crossing, which opened to traffic in 1973, is a box girder bridge built from concrete and steel. It replaced a 19th-century stone-arched bridge, which in turn superseded a 600-year-old stone-built medieval structure. This was preceded by a succession of timber bridges, the first of which was built by the Roman founders of London.", R.drawable.londonbridge);

        landmarkArrayList.add(pisa);
        landmarkArrayList.add(eiffel);
        landmarkArrayList.add(colosseum);
        landmarkArrayList.add(londonBridge);

        //Bitmap - Not Efficient
        Bitmap pisaBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pisa);

        //Liste şeklinde olan veri setlerini kullanıcıya düzgün gösterme:
        //1.ListView: Kullanması daha kolay ama verimsizdir.
        //2.RecyclerView (Jetpack): Kullanması daha zor ama verimlidir.
        //ListView, listelerdeki x birim eleman için x birim XML oluşturup gösterirken, RecyclerView kullanıcının göreceği kadar oluşturup scroll edildiğinde tekrar onu kullanıp gösterir.
        /*
        https://developer.android.com/guide/topics/ui/layout/recyclerview?gclid=CjwKCAjwqauVBhBGEiwAXOepkd6tjB76k50Ij9svkJsZrBwPliuRM_HYqlGtdeQbdNGFLoe2dLrBERoC5WQQAvD_BwE&gclsrc=aw.ds#implement-adapter
        RecyclerView'un kendi adapter sınıfı vardır; onu yazmamız gerekir.
        RecyclerView.Adapter<>: Layout ile kodları birbirine bağlayıp kullanıcaya gösterebilmek için yazarız.
        <> -> Görünüm tutucu yardımcı bir sınıf oluşturmamız gerekir.
        1.Adım: ViewHolder oluşturmaktır.
        */

        //LandmarkAdapter'den parametresi "LandmarkArrayList" olan "landmarkAdapter" isimli bir nesne oluşturuyoruz.

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LandmarkAdapter landmarkAdapter = new LandmarkAdapter(landmarkArrayList);
        binding.recyclerView.setAdapter(landmarkAdapter);

    }
}