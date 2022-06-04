package com.example.cardealership.ui.account;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardealership.R;

import java.io.Serializable;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> implements Serializable {
    private List<User> items;
    Context context;
    private static int viewHolderCount;

    public UsersAdapter(List<User> items){
        this.items = items;
    }

    @NonNull
    @Override
    public UsersAdapter.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutIdForListCars = R.layout.rv_card_user;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListCars, parent, false);
        UsersAdapter.UsersViewHolder viewHolder = new UsersAdapter.UsersViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.UsersViewHolder holder, int position) {
        User item = items.get(position);
        holder.item = item;
        holder.name.setText(item.getName() + " " + item.getLastname());
        holder.mail.setText(item.getEmail());
        holder.phone.setText(item.getPhone());
        holder.role.setText(item.getRole());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, Serializable {

        public TextView name;
        public TextView mail;
        public TextView phone;
        public TextView role;
        public User item;
        CardView cardView;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.rv_user_name);
            mail = (TextView)itemView.findViewById(R.id.rv_user_mail);
            phone = (TextView)itemView.findViewById(R.id.rv_user_phone);
            role = (TextView)itemView.findViewById(R.id.rv_user_role);
            cardView = (CardView) itemView.findViewById(R.id.cv_rv_card_user);
            cardView.setOnCreateContextMenuListener(this);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CarInfo.class);
                    intent.putExtra("EXTRA_MESSAGE", item);
                    context.startActivity(intent);
                }
            });*/
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            menu.setHeaderTitle("Изменить роль на:");
            if(item.getRole().equals("user")){
                menu.add(this.getAdapterPosition(), items.indexOf(item), 0, "Администратора");
                menu.add(this.getAdapterPosition(), items.indexOf(item), 0, "Разработчика");
            }
            if(item.getRole().equals("admin")){
                menu.add(this.getAdapterPosition(), items.indexOf(item), 0, "Пользователя");
                menu.add(this.getAdapterPosition(), items.indexOf(item), 0, "Разработчика");
            }
            if(item.getRole().equals("dev")){
                menu.add(this.getAdapterPosition(), items.indexOf(item), 0, "Пользователя");
                menu.add(this.getAdapterPosition(), items.indexOf(item), 0, "Администратора");
            }
        }
    }

}
