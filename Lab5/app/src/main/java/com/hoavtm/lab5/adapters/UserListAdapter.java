package com.hoavtm.lab5.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoavtm.lab5.R;
import com.hoavtm.lab5.models.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<User> users = new ArrayList<>();
    public UserListAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, int position) {
        final User user = users.get(position);
        holder.fullName.setText(user.getFullName());
        holder.email.setText(user.getEmail());
        holder.username.setText(user.getUsername());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView username;
        private TextView email;
        private TextView fullName;

        public ViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.tvUsername);
            email = (TextView) itemView.findViewById(R.id.tvEmail);
            fullName = (TextView) itemView.findViewById(R.id.tvFullName);
        }
    }
}
