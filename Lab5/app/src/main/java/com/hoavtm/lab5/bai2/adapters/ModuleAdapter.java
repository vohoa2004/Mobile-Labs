package com.hoavtm.lab5.bai2.adapters;

import androidx.appcompat.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hoavtm.lab5.bai2.Module;
import com.hoavtm.lab5.R;

import java.util.List;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {
    private Context context;
    private List<Module> moduleList;

    public ModuleAdapter(Context context, List<Module> moduleList) {
        this.context = context;
        this.moduleList = moduleList;
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_module, parent, false);
        return new ModuleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
        Module module = moduleList.get(position);
        holder.imageViewModule.setImageResource(module.getImage());
        holder.textViewTitle.setText(module.getTitle());
        holder.textViewDescription.setText(module.getDescription());
        holder.textViewOperatingSystem.setText(module.getOperatingSystem());

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    showUpdateModuleDialog(position);
                }
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    showDeleteModuleDialog(position);
                }
            }
        });

    }

    public void addModule(Module module) {
        moduleList.add(module);
        notifyItemInserted(moduleList.size() - 1);
    }

    public void updateModule(int position, Module module) {
        moduleList.set(position, module);
        notifyItemChanged(position);
    }

    public void deleteModule(int position) {
        moduleList.remove(position);
        notifyItemChanged(position);
    }

    private void showUpdateModuleDialog(int position) {
        Module currentModule = moduleList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_add_module, null);
        builder.setView(dialogView);

        EditText editTextTitle = dialogView.findViewById(R.id.editTextTitle);
        EditText editTextDescription = dialogView.findViewById(R.id.editTextDescription);
        editTextTitle.setText(currentModule.getTitle());
        editTextDescription.setText(currentModule.getDescription());

        int checkedItem = currentModule.getOperatingSystem().equals("Android") ? 0 : 1;
        final int[] selectedOS = new int[1];
        builder.setTitle("Update Module")
                .setSingleChoiceItems(new String[]{"Android", "iOS"}, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         selectedOS[0] = which;
                    }
                })
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = editTextTitle.getText().toString();
                        String description = editTextDescription.getText().toString();
                        String operatingSystem = selectedOS[0] == 0 ? "Android" : "iOS";
                        int selectedImage = operatingSystem.equals("Android") ? R.drawable.android : R.drawable.ios;
                        Log.d("ModuleAdapter", "Updating module to OS: " + operatingSystem);
                        Module updatedModule = new Module(selectedImage, title, description, operatingSystem);
                        updateModule(position, updatedModule);
                    }
                })
                .setNegativeButton("Cancel", null);

        builder.create().show();
    }

    private void showDeleteModuleDialog(int position) {
        Module currentModule = moduleList.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Module")
                .setMessage("Are you sure you want to delete \"" + currentModule.getTitle() + "\"?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Remove the module from the list
                        deleteModule(position);
                        Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null);

        builder.create().show();
    }


    public static class ModuleViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewModule;
        TextView textViewTitle;
        TextView textViewDescription;
        TextView textViewOperatingSystem;

        ImageView imgDelete, imgEdit;

        public ModuleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewModule = itemView.findViewById(R.id.imageViewModule);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewOperatingSystem = itemView.findViewById(R.id.textViewOperatingSystem);
            imgEdit = itemView.findViewById(R.id.imageviewEdit);
            imgDelete = itemView.findViewById(R.id.imageviewDelete);
        }
    }

    @Override
    public int getItemCount() {
        return moduleList.size();
    }
}
