package com.example.johan.restfull;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.johan.restfull.model.Person;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {

    private Context context;
    @SuppressWarnings("unused")
    private List<Person> personList;

    public PersonAdapter(Context context,int resource, List<Person> objects) {
        super(context,resource,objects);
        this.context = context;
        this.personList = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.item_person, parent, false);

        //Display Person Name in TextView widget
        Person person = personList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textView1);
        tv.setText(person.getFullName());

        return  view;

    }

}
