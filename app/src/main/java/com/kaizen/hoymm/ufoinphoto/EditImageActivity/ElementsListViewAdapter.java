package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaizen.hoymm.ufoinphoto.R;

import java.util.ArrayList;

/**
 * Created by Damian Muca (Kaizen) on 06.12.17.
 */

class ElementsListViewAdapter extends RecyclerView.Adapter <ElementsListViewAdapter.ViewHolder> {
    private int selectedIndex = -1;
    private ArrayList <Integer> imagesList = new ArrayList<>();
    private EditImageCommunication editImageCommunication;

    ElementsListViewAdapter(EditImageCommunication editImageCommunication) {
        this.editImageCommunication = editImageCommunication;

        imagesList.add(R.drawable.img1);
        imagesList.add(R.drawable.img1);
        imagesList.add(R.drawable.img1);
        imagesList.add(R.drawable.img1);
        imagesList.add(R.drawable.img1);
        imagesList.add(R.drawable.img1);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout constraintLayout;
        private ImageView imageOfUFO;
        private TextView idNumbText;
        ViewHolder(ConstraintLayout constraintLayout){
            super(constraintLayout);
            this.constraintLayout = constraintLayout;
            imageOfUFO = constraintLayout.findViewById(R.id.item_elements_list_iv_id);
            idNumbText = constraintLayout.findViewById(R.id.element_text_id);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_elements_by_images, parent, false);
        return new ElementsListViewAdapter.ViewHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.constraintLayout.setSelected(position == selectedIndex);
        holder.imageOfUFO.setImageResource(imagesList.get(position));
        holder.idNumbText.setText(String.valueOf(position+1));

        holder.constraintLayout.setOnClickListener(v -> {
            selectedIndex = position;
            this.notifyDataSetChanged();
            editImageCommunication.showHideFooterButtonsAnimation();
        });
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    void removeCurrentUFOObjAndSetToNegativeOne(){
        if (selectedIndex == -1)
            Log.e("Remove", "Wrong index tried to remove: " + selectedIndex);
        else {
            imagesList.remove(selectedIndex);
            Log.i("Remove", "Removed index: " + selectedIndex);
        }
        selectedIndex = -1;
    }

    void addItem(int drawableId){
        imagesList.add(drawableId);
    }

    void setSelectedIndex(int newIndex){
        selectedIndex = newIndex;
    }

    int getSelectedIndex(){
        return selectedIndex;
    }

}