package com.example.android.gridviewtest;

import android.app.Activity;
import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**     
 * Created by anjana on 9/28/15.
 */
public class Fragment_B extends ListFragment {


    private static final String TAG = Fragment_B.class.getSimpleName();
    ArrayList<ContactModel> contactlist;
    ContactsCustomAdapter contactsCustomAdapter;
    String searchkwy;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_b, container, false);

        RetrieveContactsAsyncTask retrieveContactsAsyncTask = new RetrieveContactsAsyncTask();
        retrieveContactsAsyncTask.execute();


        contactsCustomAdapter = new ContactsCustomAdapter(getContext(),ContactListObject.phoneList);
        setListAdapter(contactsCustomAdapter);

        return  viewGroup;

    }

    @Override
    public void onListItemClick(ListView l, View view, int position, long id) {
        ViewGroup viewGroup = (ViewGroup) view;
        TextView txt = (TextView) viewGroup.findViewById(R.id.txtitem1);
        Toast.makeText(getActivity(), ((TextView)view).getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void fillContacts() {
        //manger.deviceList.clear();

        ContentResolver contentResolver = getActivity().getContentResolver();

        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Log.d("name",name);
            String image_uri = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

            String email = null;
            Bitmap bitmap = null;
            String number = null;


            /*if (image_uri != null) {

                try {

                    bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), Uri.parse(image_uri));
                } catch (FileNotFoundException e){

                } catch (IOException e) {


                } catch (Exception e){

                }

            }*/

            //get phone numbers
            Cursor phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
            while (phones.moveToNext()) {
                int type = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                if (type == ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE) {
                    number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Log.d("Number: ", number);
                }
            }
            phones.close();

            Cursor emails = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=" + contactId, null, null);
            while (emails.moveToNext()) {
                int type = emails.getInt(emails.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                if (type == ContactsContract.CommonDataKinds.Email.TYPE_WORK) {
                    email = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                    Log.d("Email: ", email);
                }
            }
            emails.close();
            if (number != null) {
                ContactModel contact = new ContactModel();
                contact.setEmail(email);
                contact.setPhNumber(number);
                contact.setName(name);
                contact.setImageIcon(bitmap);

                ContactListObject.phoneList.add(contact);
                contactlist = ContactListObject.phoneList;

                // manger.deviceList.add(contact);
            }
            cursor.moveToNext();

        }
        cursor.close();


    }

    class RetrieveContactsAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {

                fillContacts();
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }

    public void filterValueset(String value){
        if(contactsCustomAdapter != null){
            Log.d("Inside Filter", value);
            contactsCustomAdapter.getFilter().filter(value);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        contactsCustomAdapter.notifyDataSetChanged();
    }
}
