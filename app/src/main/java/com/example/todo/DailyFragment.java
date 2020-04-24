package com.example.todo;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todo.adapter.NoteAdapter_show;
import com.example.todo.database.DBOpenHelper;
import com.example.todo.model.NoteModel;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyFragment extends Fragment {
    private static final String TAG = "DailyFragment";
    private DBOpenHelper dbOpenHelper;  // or other way cos this is fragment.
    private RecyclerView recyclerView;
    private View rootView;

    private ArrayList<NoteModel> noteDataList;
    public DailyFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // set our recyclerview on this onCreateView for fragment.
        rootView = inflater.inflate(R.layout.fragment_daily, container, false);
        recyclerView = rootView.findViewById(R.id.list_item_todo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        NoteAdapter_show adapter_show = new NoteAdapter_show(noteDataList, getContext()); // use getContext instead of this for
        adapter_show.notifyDataSetChanged();
        recyclerView.setAdapter(adapter_show);
        // to move the recyclerview
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        return rootView;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbOpenHelper = new DBOpenHelper(getContext());
        noteDataList = dbOpenHelper.selectAllItem();
    }
    private ItemTouchHelper.SimpleCallback simpleCallback = new
            ItemTouchHelper.SimpleCallback(0,
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView,
                              @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            switch (direction){
                case ItemTouchHelper.LEFT:
                    Log.e(TAG, "onSwiped: to left");
                    break;
                case ItemTouchHelper.RIGHT:
                    Log.e(TAG, "onSwiped: to right");
                    break;
            }
        }
        @Override
        public void onChildDraw (Canvas c, RecyclerView recyclerView,
                                 RecyclerView.ViewHolder viewHolder,
                                 float dX, float dY,int actionState, boolean isCurrentlyActive){
            new RecyclerViewSwipeDecorator.Builder(getContext(), c, recyclerView, viewHolder,
                    dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorGreen))
                    .addActionIcon(R.drawable.add)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
}


