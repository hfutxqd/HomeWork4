package xyz.imxqd.homework4.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

        int paddingTop = toolbar.getPaddingTop();
        toolbar.setPadding(toolbar.getLeft(), paddingTop + getStatusBarHeight(), toolbar.getRight(), toolbar.getBottom());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, TplFragment.newInstance())
                .commit();
    }

    public void setHotWord(String word) {
        searchHotWord.setText(word);
    }
}
