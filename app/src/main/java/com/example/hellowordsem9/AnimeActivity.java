package com.example.hellowordsem9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.hellowordsem9.Adapters.AnimeAdapter;
import com.example.hellowordsem9.models.Animes;

import java.util.ArrayList;
import java.util.List;

public class AnimeActivity extends AppCompatActivity {
    List<Animes> anime = new ArrayList<Animes>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        cargarAnimes();

        AnimeAdapter adapter = new AnimeAdapter(this,anime);

        RecyclerView rvAnime =  findViewById(R.id.rv_anime);
        rvAnime.setLayoutManager(new LinearLayoutManager(this));
        rvAnime.setAdapter(adapter);
    }

    private void cargarAnimes() {
        anime.add(new Animes("Deat Note","Libreta de muerte anime ","https://w7.pngwing.com/pngs/341/725/png-transparent-death-note-rectangle-brand-black-m-creame-text-rectangle-black.png",true));
        anime.add(new Animes("Shingeki","Mundo llegno de tinates","https://e7.pngegg.com/pngimages/150/506/png-clipart-eren-yeager-attack-on-titan-rendering-lelouch-lamperouge-shingeki-no-kyojin-3d-computer-graphics-fictional-character.png",false));
        anime.add(new Animes("Mash","Hombre fuerte en mundo de magia","https://mystickermania.com/cdn/stickers/anime/mashle-mash-burnedead-eats-512x512.png",true));
        anime.add(new Animes("One Piece","Hombre que se combertira en el rey de los piratas ","https://w7.pngwing.com/pngs/338/980/png-transparent-one-piece-illustration-monkey-d-luffy-one-piece-animated-cartoon-sticker-one-piece-comics-child-friendship.png",false));
        anime.add(new Animes("Mobs Psico","Niño mentalmente fuerte","https://w7.pngwing.com/pngs/949/582/png-transparent-nose-cheek-forehead-eye-animated-cartoon-mob-psycho-face-black-hair-head.png",true));

    }
}