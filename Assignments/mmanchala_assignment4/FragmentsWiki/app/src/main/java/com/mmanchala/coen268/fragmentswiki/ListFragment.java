package com.mmanchala.coen268.fragmentswiki;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFragment extends Fragment {

    String[] list;
    int id;
    OnChildItemClickListener listener;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("clickedItem");
            String[] companies_list = new String[]{
                    "IBM",
                    "Google",
                    "ebay",
                    "Cisco",
                    "NetApp"
            };

            String[] fruits_list = new String[]{
                    "Apple",
                    "Mango",
                    "Banana",
                    "Orange",
                    "Guava"
            };

            String[] animals_list = new String[]{
                    "Cat",
                    "Rat",
                    "Lion",
                    "Tiger",
                    "Monkey"
            };
            if (id == 0) {
                list = companies_list;
                ChildActivity.currentItem = "https://en.wikipedia.org/wiki/"
                        + companies_list[0];
            }
             else if(id ==1) {
                list = fruits_list;
                ChildActivity.currentItem = "https://en.wikipedia.org/wiki/"
                        + fruits_list[0];
            }
               else if(id==2) {
                list = animals_list;
                ChildActivity.currentItem = "https://en.wikipedia.org/wiki/"
                        + animals_list[0];
            }

            ListView listView = getView().findViewById(R.id.childListView);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, list);

            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    ChildActivity.currentItem = "https://en.wikipedia.org/wiki/" +
                            adapterView.getItemAtPosition(i).toString();
                    listener.onItemSelected("https://en.wikipedia.org/wiki/" +
                            adapterView.getItemAtPosition(i).toString());
                }
            });
        }
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        View view = inflater.inflate(R.layout.activity_child, container, false);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnChildItemClickListener) {
            listener = (OnChildItemClickListener) context;
        }
    }

    public interface OnChildItemClickListener {
        void onItemSelected(String url);
    }
}
