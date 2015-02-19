package edu.javacourse.contact.dao;

import edu.javacourse.contact.entity.Contact;
import edu.javacourse.contact.filter.ContactFilter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

/**
 * Project: CM2
 * User: vars
 * Date: 18/02/15
 * Time: 00:02
 * Created with IntelliJ IDEA.
 */
public class ContactDAODb implements ContactDAO {

    private static PropertyResourceBundle confProps;
    private static String jdbcUrl;
    private static String jdbcUsername;
    private static String jdbcPassword;
    private boolean isChanged = true;
    private List<Contact> currentContactList = new ArrayList<Contact>();

    static {
        try {
            confProps = (PropertyResourceBundle) PropertyResourceBundle.getBundle("conf/ContactDAODb");
            jdbcUrl = "jdbc:postgresql://" + confProps.getObject("contact.dao.db.host") + ":" + confProps.getObject("contact.dao.db.port") + "/" + confProps.getObject("contact.dao.db.database");
            jdbcUsername = (String) confProps.getObject("contact.dao.db.user");
            jdbcPassword = (String) confProps.getObject("contact.dao.db.pass");
        } catch (Exception e) {
            System.out.println("Can't parse properties file.");
            e.printStackTrace(System.err);
            System.exit(1);
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find database driver.");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl,jdbcUsername,jdbcPassword);
    }

    @Override
    public Long addContact(Contact contact) {
        return null;
    }

    @Override
    public Contact getContact(Long id) {
        return null;
    }

    @Override
    public void deleteContact(Long id) {

    }

    @Override
    public void editContact(Long id, Contact contact) {

    }

    @Override
    public List<Contact> listContacts() {
        getContactsFromDb();
        int i = 0;
        for (Contact c : currentContactList) {
            System.out.println("Contact: " + ++i + c);
        }
        return currentContactList;
    }

    @Override
    public List<Contact> findContact(ContactFilter filter) {
        return null;
    }

    private void getContactsFromDb() {
        try {
            Connection con = getConnection();
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM contacts");
                currentContactList.clear();
                while (rs.next()) {
                    Contact c = new Contact();
                    c.setContactID(rs.getLong("contact_id"));
                    c.setSurname(rs.getString("surname"));
                    c.setGivenName(rs.getString("givenname"));
                    c.setEmail(rs.getString("email"));
                    c.setPhone(rs.getString("phone"));
                    currentContactList.add(c);
                }
            } finally {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

