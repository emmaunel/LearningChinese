package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ayobami on 12/9/2016.
 */

public class WordAdapter extends ArrayAdapter<Word> implements Filterable {
    private int colorResource;
    public ArrayList<Word> words;
    public ArrayList<Word> orig;
    CustomFilter filter;
    public WordAdapter(Context context, ArrayList<Word> words, int mColorResource) {
        super(context, 0, words);
        colorResource = mColorResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_list_item, parent, false);
        }
        Word current = getItem(position);

        TextView english = (TextView) listItemView.findViewById(R.id.english);
        english.setText(current.getEnglish());

        TextView chinese = (TextView) listItemView.findViewById(R.id.chinese);
        chinese.setText(current.getChinese());

        ImageView image = (ImageView)listItemView.findViewById(R.id.image);
        if(current.hasImage()){
            image.setImageResource(current.getImage());
            image.setVisibility(View.VISIBLE);
        } else{
            image.setVisibility(View.GONE);
        }

        View textBackground = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), colorResource);
        textBackground.setBackgroundColor(color);
        return listItemView;
    }


    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new CustomFilter();
        }
        return filter;
    }

    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0){
                constraint = constraint.toString().toUpperCase();

                ArrayList<Word> wordArrayList = new ArrayList<Word>();

                for (int i = 0; i <orig.size(); i++ ){
                    if (orig.get(i).getEnglish().contains(constraint)){
                        Word word = new Word(orig.get(i).getEnglish());
                        wordArrayList.add(word);
                    }
                }

                results.count = wordArrayList.size();
                results.values = wordArrayList;
            } else {
                results.count = orig.size();
                results.values = orig;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            words = (ArrayList<Word>) results.values;
            notifyDataSetChanged();
        }
    }
}
