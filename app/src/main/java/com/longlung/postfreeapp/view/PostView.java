package com.longlung.postfreeapp.view;

import com.longlung.postfreeapp.response.PostsItem;

import java.util.List;

public interface PostView {
    void onLoading();
    void onHidingLoading();
    void onError(String message);
    void onSuccess(String message);
    void onGetPostSuccess(List<PostsItem> postsItemList);
    void onGetPostBySuccess(PostsItem data);

}
