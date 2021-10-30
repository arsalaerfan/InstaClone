package com.example.instaclone.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instaclone.PostsAdapter;
import com.example.instaclone.R;
import com.example.instaclone.post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment {

    public static final String TAG = "PostsFragment";
    private RecyclerView rvPosts;
    public PostsAdapter adapter;
    public List<post> allpPosts;

    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPosts = view.findViewById(R.id.rvPosts);
        allpPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allpPosts);

        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPosts();
    }
    private void queryPosts(){
        ParseQuery<post> query = ParseQuery.getQuery(post.class);
        query.include(post.KEY_USER);
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
