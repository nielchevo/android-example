package com.tokopedia.testproject.problems.news.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tokopedia.testproject.R;
import com.tokopedia.testproject.problems.news.model.Article;
import com.tokopedia.testproject.problems.news.presenter.NewsPresenter;

import java.util.List;

public class NewsActivity extends AppCompatActivity implements com.tokopedia.testproject.problems.news.presenter.NewsPresenter.View {

    private NewsPresenter newsPresenter;
    private NewsAdapter newsAdapter;

    private RecyclerView recyclerView;
    private RelativeLayout utilView;

    private Button btnRetry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        newsPresenter = new NewsPresenter(this);
        newsAdapter = new NewsAdapter(null);

        // init view
        recyclerView = findViewById(R.id.recyclerView);
        utilView = findViewById(R.id.loadingPanel);

        // Listener handler
        btnRetry = findViewById(R.id.retry_button);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRetryClicked();
            }
        });

        recyclerView.setAdapter(newsAdapter);

        if(newsAdapter.getItemCount() <= 0)
        {
            DisplayLoading(true);
        }

        newsPresenter.getEverything("android"); // this for load data
    }

    private void DisplayLoading(boolean isDisplay)
    {
        ProgressBar loadingBar = (ProgressBar) findViewById(R.id.loading_bar);

        if(isDisplay){
            utilView.setVisibility(View.VISIBLE);
            loadingBar.setVisibility(View.VISIBLE);
        }else{
            utilView.setVisibility(View.GONE);
            loadingBar.setVisibility(View.INVISIBLE);
        }
    }

    private void DisplayRetry(boolean isDisplay) {
        if(isDisplay){
            utilView.setVisibility(View.VISIBLE);
            btnRetry.setVisibility(View.VISIBLE);
        }else{
            utilView.setVisibility(View.GONE);
            btnRetry.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onSuccessGetNews(List<Article> articleList) {
        DisplayLoading(false);

        newsAdapter.setArticleList(articleList);
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onErrorGetNews(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
        DisplayLoading(false);

        DisplayRetry(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newsPresenter.unsubscribe();
    }

    @Override
    public void onCompletedGetNews() {
        DisplayLoading(false);
        DisplayRetry(false);
    }

    private void onRetryClicked() {
        if(btnRetry.getVisibility() == View.VISIBLE) {
            DisplayRetry(false);
            DisplayLoading(true);

            // refetch news
            newsPresenter.doRetryFetchNews();
        }
    }
}
