package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("Interestelar", R.drawable.filme1, "2014-11-07", 8.6, "Em um futuro distópico, um grupo de exploradores viaja pelo espaço em busca de um novo lar para a humanidade."));
        movieList.add(new Movie("O Poderoso Chefão", R.drawable.filme2, "1972-03-24", 9.2, "A história da família mafiosa Corleone, liderada por Don Vito Corleone, e os conflitos internos e externos que enfrentam."));
        movieList.add(new Movie("Pulp Fiction: Tempo de Violência", R.drawable.filme3, "1994-10-14", 8.9, "Várias histórias interligadas sobre criminosos, violência e redenção, em uma das obras mais aclamadas de Quentin Tarantino."));
        movieList.add(new Movie("Um Sonho de Liberdade", R.drawable.filme4, "1994-09-22", 9.3, "Um homem condenado injustamente por assassinato forma uma amizade improvável com um prisioneiro enquanto busca a liberdade."));
        movieList.add(new Movie("Os Vingadores", R.drawable.filme5, "2012-05-04", 8.0, "Os maiores heróis da Terra se unem para impedir uma ameaça global, com Loki tentando dominar a Terra usando um exército alienígena."));
        movieList.add(new Movie("A Origem", R.drawable.filme6, "2020-07-16", 8.8, "Um ladrão especializado em roubar segredos através dos sonhos recebe uma última missão para implantar uma ideia na mente de um alvo."));
        movieList.add(new Movie("Titanic", R.drawable.filme7, "1997-12-19", 7.8, "A história de amor entre Jack e Rose a bordo do infame navio Titanic, enquanto ele afunda no oceano."));
        movieList.add(new Movie("O Cavaleiro das Trevas", R.drawable.filme8, "2008-07-18", 9.0, "Batman enfrenta o Coringa, um vilão psicótico que busca causar caos em Gotham City."));
        movieList.add(new Movie("Matrix", R.drawable.filme9, "1999-03-31", 8.7, "Um hacker chamado Neo descobre a verdade sobre o mundo e se une à resistência contra máquinas que controlam a humanidade."));
        movieList.add(new Movie("Forrest Gump: O Contador de Histórias", R.drawable.filme10, "1994-07-06", 8.8, "A história de Forrest Gump, um homem simples com uma vida extraordinária, que viveu momentos históricos da América."));
        movieList.add(new Movie("Avatar", R.drawable.filme11, "2009-12-18", 7.8, "Em um planeta distante, Jake Sully se infiltra entre os nativos Na'vi e acaba se apaixonando pela sua cultura e por uma guerreira."));
        movieList.add(new Movie("Guardiões da Galáxia", R.drawable.filme12, "2014-08-01", 8.0, "Peter Quill forma uma equipe de desajustados para roubar um artefato, mas acabam lutando para salvar a galáxia."));
        movieList.add(new Movie("O Rei Leão", R.drawable.filme13, "1994-06-15", 8.5, "Simba, um leão jovem, deve confrontar seu tio Scar e assumir seu lugar como rei de uma terra ameaçada."));
        movieList.add(new Movie("Homem-Aranha: Sem Volta Para Casa", R.drawable.filme14, "2021-12-17", 8.3, "Peter Parker enfrenta as consequências de sua identidade secreta sendo revelada e busca ajuda de Doutor Estranho para reverter o caos que causou."));
        movieList.add(new Movie("Frozen: Uma Aventura Congelante", R.drawable.filme14, "2013-11-27", 7.4, "A história das irmãs Elsa e Anna, com Elsa tentando controlar seus poderes de gelo enquanto busca se reconectar com sua irmã perdida."));

        MovieAdapter movieAdapter = new MovieAdapter(this, movieList);
        recyclerView.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                intent.putExtra("movieTitle")
            }
        });

    }
}