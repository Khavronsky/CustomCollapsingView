package com.khavronsky.appbar2test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CustomCollapsingView mCustomCollapsingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createMyView();
        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = mCustomCollapsingView.getToolbar();
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    private void createMyView() {
        mCustomCollapsingView = (CustomCollapsingView) findViewById(R.id.my_view);
        mCustomCollapsingView.setTextTitle("Пляски святого Вита. Гимнастика Хантингтона")
                .setTextSubtitle("Невро-Кардио")
                .setTextValue("120")
                .setTextUnit("ккал")
                .setTextExtraDescription("за 23 минуты")
                .setImageID(R.drawable.ic_cardio_ex)
                .applyChanges();
    }
    private void showAlertView(){
        mCustomCollapsingView.showAlertView(true).applyChanges();
    }

    @Override
    public void onClick(final View v) {
        onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.delete) {
            recreate();
            return true;
        }
        if (id == R.id.edit) {
            showAlertView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}