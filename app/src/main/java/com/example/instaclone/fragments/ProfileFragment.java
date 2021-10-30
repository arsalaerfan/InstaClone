package com.example.instaclone.fragments;

import android.util.Log;

import com.example.instaclone.post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {

    private void queryPosts(){
        ParseQuery<post> query = ParseQuery.getQuery(post.class);
        query.include(post.KEY_USER);
        query.whereEqualTo(post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<post>() {
            @Override
            public void done(List<post> posts, ParseException e) {
                if( e != null){
                    Log.e(TAG, "Issues with getting posts", e);
                }
                for(post pos: posts){
                    Log.i(TAG, "Post: " + pos.getDescription() + ", username: " + pos.getUser().getUsername());
                }
                allpPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
