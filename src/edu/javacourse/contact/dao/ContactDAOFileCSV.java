package edu.javacourse.contact.dao;

import edu.javacourse.contact.entity.Contact;
import edu.javacourse.contact.filter.ContactFilter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

/**
 * Project: CM2
 * User: vars
 * Date: 08/02/15
 * Time: 22:04
 * Created with IntelliJ IDEA.
 */
public class ContactDAOFileCSV implements ContactDAO {

    private boolean isChanged = true;
    private List<Contact> currectContactList = new ArrayList<Contact>();
    private static PropertyResourceBundle confProps;

    static {
        try {
            confProps = (PropertyResourceBundle) PropertyResourceBundle.getBundle("conf/ContactDAOFileCSV");
        } catch (Exception e) {
            System.out.println("Can't parse properties file.");
            e.printStackTrace(System.err);
        }
        // preparing file for io
        File file = new File((String) confProps.getObject("contact.dao.filepath"));
        if (!file.exists()) {
            try {
                System.out.println("new!");
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public Long addContact(Contact contact) {
        readContactsFromFile();
        contact.setContactID(System.currentTimeMillis());
        currectContactList.add(contact);
        isChanged = true;
        writeContactsToFile();
        System.out.println("Added: " + contact);
        return contact.getContactID();
    }

    @Override
    public Contact getContact(Long id) {
        for (Contact contact : currectContactList) {
            if (id.equals(contact.getContactID())) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public void deleteContact(Long id) {
        readContactsFromFile();
        Contact toDelete = null;
        for (Contact contact : currectContactList) {
            if (id.equals(contact.getContactID())) {
                toDelete = contact;
            }
        }
        if (toDelete != null) {
            currectContactList.remove(toDelete);
            isChanged = true;
            writeContactsToFile();
            System.out.println("Deleted: " + toDelete);
        }

    }

    @Override
    public void editContact(Long id, Contact contact) {

    }

    @Override
    public List<Contact> listContacts() {
        readContactsFromFile();
        int i = 0;
        for (Contact contact : currectContactList) {
            System.out.println("Contact " + ++i + ": " + contact);
        }
        return currectContactList;
    }

    @Override
    public List<Contact> findContact(ContactFilter filter) {
        return null;
    }


    private void readContactsFromFile() {
        if (isChanged) {
            List<Contact> contactList = new ArrayList<Contact>();
            contactList.clear();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("" + confProps.getObject("contact.dao.filepath")));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] parts = line.split(";");
                    // 1;sizov;andrey;a@b.com;1231231
                    if (parts.length == 5) {
                        Contact contact = new Contact();
                        contact.setContactID(Long.parseLong(parts[0]));
                        contact.setSurname(parts[1]);
                        contact.setGivenName(parts[2]);
                        contact.setEmail(parts[3]);
                        contact.setPhone(parts[4]);
                        contactList.add(contact);
                    }
                }
                bufferedReader.close();
                currectContactList = contactList;
                isChanged = false;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void writeContactsToFile() {
        if (isChanged) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("" + confProps.getObject("contact.dao.filepath")));
                String line;
                for (Contact contact : currectContactList) {
                    line = contact.getContactID() + ";" + contact.getSurname() + ";" + contact.getGivenName() + ";" + contact.getEmail() + ";" + contact.getPhone() + "\n";
                    bufferedWriter.write(line);
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}


