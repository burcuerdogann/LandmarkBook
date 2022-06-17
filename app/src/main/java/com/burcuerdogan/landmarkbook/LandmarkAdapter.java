package com.burcuerdogan.landmarkbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.burcuerdogan.landmarkbook.databinding.RecyclerRowBinding;

import java.util.ArrayList;
import java.util.Objects;

/*
Liste şeklinde olan veri setlerini kullanıcıya düzgün gösterebilmek için 2 yol vardır:
1.ListView: Kullanması daha kolay ama verimsizdir.
2.RecyclerView (Jetpack): Kullanması daha zor ama verimlidir.

ListView, listelerdeki x adet eleman için x adet XML oluşturup gösterirken, RecyclerView kullanıcının göreceği kadar XML oluşturup scroll edildiğinde tekrar onu kullanıp gösterir. Bu açıdan daha verimlidir.

Adapter java classımızı (bkz:LandmarkAdapter), Layout (bkz:recycler_row.xml) ile kodları birbirine bağlayıp kullanıcıya gösterebilmek için yazarız.

Oluşturduğumuz LandmarkAdapter classımıza RecyclerView.Adapter classındaki özellikleri alabilmek için extend ederek; LandmarkAdapter classımıza RecyclerView.Adapter'dan inheritance (kalıtım) alırız.

RecyclerView.Adapter sınıfından kalıtım alırken RecyclerView.Adapter<>, bir tane "<VH>" -> ViewHolder / Görünüm Tutucu sınıfı oluşturmamızı ister.

Bu nedenle, Adapter classımız içerisinde bir tane de "görünümlerimizi tutması açısından" yardımcı bir Holder (bkz:LandmarkHolder) sınıfı oluştururuz.

Aynı şekilde Holder sınıfımıza bu sefer RecyclerView.ViewHolder'dan inheritance (kalıtım) alırız.

(Hatamız devam eder) Alt + Enter diyerek Constructorımızı oluştururuz.

Holder classımızı oluşturduktan sonra, artık Adapter sınıfımızın miras aldığı RecyclerView.Adapter<> içerisine Holder sınıfımızı (bkz: LandmarkAdapter.LandmarkHolder) yazabiliriz.

Devam eden hatayı gidermek için tekrar Alt+Enter diyerek uygulamamız gereken 3 methodu oluşturuyoruz.
1. onCreateViewHolder() -> XML'imizi (bkz:recycler_row.xml) bağlama işlemini yapacağımız methoddur.
2. onBindViewHolder() -> Görünümleri tutması için oluşturduğumuz ViewHolder sınıfımız bağlandığında; XML / Layoutumuz içerisinde hangi verileri göstermek istediğimizi söyleyen methoddur.
3. getItemCount() -> XML'imizin kaç defa oluşturulacağını söylediğimiz methoddur.

Sonrasında methodları kullanıp sırasıyla; bağlama işlemini, gösterilecek verileri ve kaç defa gösterileceği gibi işlemleri yapabilmek için bir adet XML / Layout (bkz:recycler_row.xml) oluşturup tekrar etmesini istediğimiz layout tasarımımızı yapıyoruz.

3. methodda (bkz: getItemCount()) XML'imizin kaç defa gösterileceğini söyleyebilmemiz için LandmarkAdapter classımızdan LandmarkArrayList'e ulaşıp sizeını alabilmemiz gerekir.

Ama başka bir classımızın içerisinde olduğu için Adapter classımızdan ulaşamayız.

LandmarkAdapter constructorında LandmarkArrayList'i bir parametre olarak isteyebilmek için; -önceden MainActivity'de yaptığımız gibi- Adapter classımızın (bkz:LandmarkAdapter) içerisinde de bir tane içinde Landmark tutulan bir ArrayList (bkz: ArrayList<Landmark> landmarkArrayList;) değişkeni oluşturuyoruz.

Bunu yaptıktan sonra Generate'e tıklayıp Constructor oluştur dediğimizde artık "LandmarkArrayList"in bir parametre olarak listelenmeye başladığını görürüz.

Ve MainActivity'e geldiğimizde de LandmarkAdapter'den parametresi "LandmarkArrayList" olan "landmarkAdapter" isimli bir nesne oluşturup LandmarkAdapter içerisinde bir değişken olarak kullanıp sizeına ulaşabiliriz.

Bu noktada 3. method işlemimizi tamamlayarak XML'imizin "LandmarkArrayList.size()" kadar gösterilmesini sağlamış oluyoruz.

Holder Classımıza gelecek olursak, diğer classlarda yaptığımız gibi; LandmarkAdapter classımızın içinde görünümlerimizi tutmamıza yarayan yardımcı LandmarkHolder classımızda da private bir binding oluşturuyoruz.

Buradaki amaç, "View itemView" yerine "RecyclerRowBinding binding" yazarak, kendi oluşturduğumuz XML/Layout'u kodumuza bağlamak için 1. methodda kullanabileceğimiz bir recyclerRowBinding oluşturabilmek.

NOT: Bu işlemi direkt "private RecyclerRowBinding binding;" oluşturduktan sonra LandmarkHolder public classını silip Generate'e tıklayarak otomatik contructor yapısını oluşturabiliriz.

Son olarak bindingi görünüme çevirebilmek için; super(itemView)'u "super(binding.getRoot());" olarak değiştiriyoruz.

1. methoda geldiğimizde onCreateViewHolder() methodu bizden LandmarkHolder yani ViewHolder sınıfından bir obje döndürmemizi istiyor. Öncelikle yeni bir LandmarkHolder yani oluşturduğumuz recycler_row.XML layoutumuzu koda bağlayıp döndürebilmemiz için (bkz: return new LandmarkHolder() -> buraya artık bir binding girmemiz gerekiyor) "recyclerRowBinding" isimli bindingimizi oluşturup "return new LandmarkHolder(recyclerRowBinding) diyoruz.

Bu noktada 1. method işlemimizi de tamamlayarak oluşturduğumuz XML/Layout (bkz: recycler_row.XML) ile kodumuzu birbirine bağlamış oluyoruz.

Son olarak 2. methodda (bkz: onBindViewHolder()) artık XML içerisindeki hangi verileri göstereceğimizi yazıyoruz. Bizim verimiz hatırlarsanız "recyclerViewTextView" idi.

"recyclerViewTextView", "LandmarkArrayList.size()" kadar tekrar edecek ve her seferinde "LandmarkArrayList.get(position).name" ile içeriği değişecek. Yani LandmarkArrayList'te sıra hangi liste verisindeyse onun ismini set edecek.

Bunu da bizden "holder" yani bir tutucu istendiği için; "holder.binding.recyclerViewTextView.setText(LandmarkArrayList.get(position).name);" şeklinde yazıp 2. methodumuzu da tamamlıyoruz.*/

public class LandmarkAdapter extends RecyclerView.Adapter<LandmarkAdapter.LandmarkHolder> {

    ArrayList<Landmark> landmarkArrayList;

    public LandmarkAdapter(ArrayList<Landmark> landmarkArrayList) {
        this.landmarkArrayList = landmarkArrayList;
    }

    //1.Method: XML'imizi (bkz:recycler_row.xml) bağlama işlemini yapacağımız method.
    @NonNull
    @Override
    public LandmarkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LandmarkHolder(recyclerRowBinding);
    }

    //2.Method: Görünümleri tutması için oluşturduğumuz ViewHolder sınıfımız bağlandığında; XML/Layoutumuz içerisinde hangi verileri göstermek istediğimizi söyleyen method.
    @Override
    public void onBindViewHolder(@NonNull LandmarkAdapter.LandmarkHolder holder, int position) {
        holder.binding.recyclerViewTextView.setText(landmarkArrayList.get(position).name);

        //Tıklandığında ne olacağı:

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                //chosenLandmark = landmarkArrayList.get(position);
                //intent.putExtra("landmark", (Parcelable) landmarkArrayList.get(position));
                Singleton singleton = Singleton.getInstance();
                singleton.setSentLandmark(landmarkArrayList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    //3.Method: XML'imizin kaç defa oluşturulacağını söylediğimiz method.
    @Override
    public int getItemCount() {
        return landmarkArrayList.size();
    }

    //Holder Class: Diğer classlarda yaptığımız gibi; LandmarkAdapter classımızın içinde görünümlerimizi tutmamıza yarayan yardımcı LandmarkHolder classımızda da private bir binding oluşturuyoruz.
    //Buradaki amaç, "View itemView" yerine "RecyclerRowBinding binding" yazarak, kendi oluşturduğumuz XML/Layout'u kodumuza bağlamak.
    public class LandmarkHolder extends RecyclerView.ViewHolder {

        private RecyclerRowBinding binding;

        public LandmarkHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
