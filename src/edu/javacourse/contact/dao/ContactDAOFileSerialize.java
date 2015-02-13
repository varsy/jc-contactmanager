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
 * Date: 13/02/15
 * Time: 23:50
 * Created with IntelliJ IDEA.
 */
public class ContactDAOFileSerialize implements ContactDAO {

    private boolean isChanged = true;
    private List<Contact> currentContactList = new ArrayList<Contact>();
    private static PropertyResourceBundle confProps;
    private static String filePath;

    static {
        try {
            confProps = (PropertyResourceBundle) PropertyResourceBundle.getBundle("conf/ContactDAOFileSerialize");
        } catch (Exception e) {
            System.out.println("Can't parse properties file.");
            e.printStackTrace(System.err);
        }
        filePath = (String) confProps.getObject("contact.dao.filepath");
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                System.out.println("Creating new file");
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
        currentContactList.add(contact);
        isChanged = true;
        writeContactsToFile();
        System.out.println("Added: " + contact);
        return contact.getContactID();
    }

    @Override
    public Contact getContact(Long id) {
        readContactsFromFile();
        for (Contact c : currentContactList) {
            if (id.equals(c.getContactID())) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void deleteContact(Long id) {
        readContactsFromFile();
        for (Contact c : currentContactList) {
            if (id.equals(c.getContactID())) {
                currentContactList.remove(c);
                isChanged = true;
                writeContactsToFile();
                System.out.println("Deleted: " + c);
            }
        }
    }

    @Override
    public void editContact(Long id, Contact contact) {

    }

    @Override
    public List<Contact> listContacts() {
        readContactsFromFile();
        int i = 0;
        for (Contact c : currentContactList) {
            System.out.println("Contact " + ++i + ": " + c);
        }
        return currentContactList;
    }

    @Override
    public List<Contact> findContact(ContactFilter filter) {
        return null;
    }


    private void readContactsFromFile() {
        if (isChanged) {
            try {
                FileInputStream fis = new FileInputStream(filePath);
                ObjectInputStream ois = new ObjectInputStream(fis);
                currentContactList = (List<Contact>) ois.readObject();
                isChanged = false;
                ois.close();
                fis.close();
            } catch (EOFException e) {
                System.out.println("Empty file, continuing");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeContactsToFile() {
        if (isChanged) {
            try {
                FileOutputStream fos = new FileOutputStream(filePath);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(currentContactList);
                isChanged = false;
                oos.close();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
