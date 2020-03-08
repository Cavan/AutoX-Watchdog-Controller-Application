package autoxwatchdog.commander;
//Citation: https://www.tutorialspoint.com/working-with-recycler-view-in-android-app
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AutoXWatchdogImages extends AppCompatActivity {
    private List<MovieModel> movieList = new ArrayList<>();
    private MoviesAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_x_watchdog_images);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new MoviesAdapter(movieList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
    }
    private void prepareMovieData() {
        MovieModel movie = new MovieModel("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);
        movie = new MovieModel("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);
        movie = new MovieModel("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);
        movie = new MovieModel("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);
        movie = new MovieModel("The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);
        movie = new MovieModel("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);
        movie = new MovieModel("Up", "Animation", "2009");
        movieList.add(movie);
        movie = new MovieModel("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);
        movie = new MovieModel("The LEGO MovieModel", "Animation", "2014");
        movieList.add(movie);
        movie = new MovieModel("Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);
        movie = new MovieModel("Aliens", "Science Fiction", "1986");
        movieList.add(movie);
        movie = new MovieModel("Chicken Run", "Animation", "2000");
        movieList.add(movie);
        movie = new MovieModel("Back to the Future", "Science Fiction", "1985");
        movieList.add(movie);
        movie = new MovieModel("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movieList.add(movie);
        movie = new MovieModel("Goldfinger", "Action & Adventure", "1965");
        movieList.add(movie);
        movie = new MovieModel("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movieList.add(movie);
        mAdapter.notifyDataSetChanged();
    }

}
