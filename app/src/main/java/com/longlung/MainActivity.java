package com.longlung;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.graphics.PostProcessor;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.longlung.postfreeapp.R;
import com.longlung.postfreeapp.adapter.PostAdapter;
import com.longlung.postfreeapp.api.APIClient;
import com.longlung.postfreeapp.api.ApiInterface;
import com.longlung.postfreeapp.app.BaseActivity;
import com.longlung.postfreeapp.pressenter.PostPresenter;
import com.longlung.postfreeapp.response.PostResponse;
import com.longlung.postfreeapp.response.PostsItem;
import com.longlung.postfreeapp.ui.PostDetailActivity;
import com.longlung.postfreeapp.view.PostView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements PostView {

    private PostAdapter postAdapter;
    private RecyclerView recyclerViewPost;
    private ProgressBar progressBar;
    private PostPresenter postPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        progressBar = findViewById(R.id.progresBar);
        progressBar.setVisibility(View.GONE);
        recyclerViewPost    = findViewById(R.id.recyclerViewPost);
        postPresenter = new PostPresenter(this);
        postPresenter.getAllPosts();


    }


    @Override
    public void onLoading() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onHidingLoading() {
        progressBar.setVisibility(View.GONE);


    }

    @Override
    public void onError(String message) {
        showMessage(message);

    }

    @Override
    public void onSuccess(String message) {
        showMessage(message);

    }

    @Override
    public void onGetPostSuccess(List<PostsItem> postsItemList) {
        postAdapter = new PostAdapter(postsItemList, this, new PostAdapter.OnClickListener() {
            @Override
            public void onClickCard(View view, PostsItem postsItem) {
                postPresenter.getPostById(postsItem.getId());
            }
        });
        recyclerViewPost.setAdapter(postAdapter);
        recyclerViewPost.setLayoutManager(new GridLayoutManager(this,1));

    }

    @Override
    public void onGetPostBySuccess(PostsItem data) {
       // showMessage(data.toString());
        if (data != null){
            Intent intent = new Intent(this, PostDetailActivity.class);
            intent.putExtra("TITLE",data.getTitle());
            intent.putExtra("BODY",data.getBody());
            startActivity(intent);
        }

    }
}