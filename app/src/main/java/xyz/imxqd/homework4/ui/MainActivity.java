package xyz.imxqd.homework4.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import xyz.imxqd.homework4.R;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private TextView searchHotWord;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        searchHotWord = findViewById(R.id.search_hot_word);

        final View searchLayout = findViewById(R.id.title_search_layout);
        searchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra(SearchActivity.ARG_HINT, searchHotWord.getText());
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, searchLayout, "search_bar");
                startActivity(intent, optionsCompat.toBundle());
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, TplFragment.newInstance())
                .commit();
    }

    public void setHotWord(String word) {
        searchHotWord.setText(word);
    }
}
