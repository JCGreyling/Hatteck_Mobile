package com.example.johan.restfull.parsers;

import com.example.johan.restfull.model.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PersonJASONParser {

    public static List<Person> parseFeed(String content) {

        try {

            JSONArray ar = new JSONArray(content);
            List<Person> personList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = (JSONObject)ar.get(i);

                Person person = new Person();
               // System.out.println("get fullName = " + obj.getJSONObject("person").getString("fullName"));

                person.setId(Integer.parseInt(obj.getJSONObject("person").getString("id")));
                person.setFullName(obj.getJSONObject("person").getString("fullName"));
                person.setAge(Integer.parseInt(obj.getJSONObject("person").getString("age")));
                //System.out.println("person.getId() = " + person.getId());
                //System.out.println("person.getFullName() = " + person.getFullName());
                //System.out.println("person.getAge() = " + person.getAge());

                personList.add(person);
            }

            return personList;
        }catch (JSONException e) {
            e.printStackTrace();

            return null;

        }


    }
}
