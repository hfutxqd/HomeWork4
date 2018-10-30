package xyz.imxqd.homework4.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import xyz.imxqd.homework4.R;

public class SearchActivity extends BaseActivity {
    public static final String ARG_HINT = "SearchActivity.hint";

    private TextView searchHotWord;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar = findViewById(R.id.toolbar);
        searchHotWord = findViewById(R.id.search_hot_word);

        String text = getIntent().getStringExtra(ARG_HINT);
        searchHotWord.setHint(text);
    }
}
