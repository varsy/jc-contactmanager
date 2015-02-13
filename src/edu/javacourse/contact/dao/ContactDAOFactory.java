package edu.javacourse.contact.dao;

import edu.javacourse.contact.exception.DAOException;

import java.util.PropertyResourceBundle;

/**
 * Project: CM2
 * User: vars
 * Date: 08/02/15
 * Time: 21:05
 * Created with IntelliJ IDEA.
 */
public class ContactDAOFactory {

    private static PropertyResourceBundle confProps;

    static {
        try {
            confProps = (PropertyResourceBundle) PropertyResourceBundle.getBundle("conf/ContactDAO");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public static ContactDAO getDAO() throws DAOException {
        String daoType = confProps.getString("contact.dao.type");
        try {
            if (daoType.equals("csv")) {

                PropertyResourceBundle daoProps = (PropertyResourceBundle) PropertyResourceBundle.getBundle("conf/ContactDAOFileCSV");
                Class classDefinition = Class.forName(daoProps.getString("contact.dao.class"));
                return (ContactDAO) classDefinition.newInstance();
            }
            if (daoType.equals("serialize")) {
                PropertyResourceBundle daoProps = (PropertyResourceBundle) PropertyResourceBundle.getBundle("conf/ContactDAOFileSerialize");
                Class classDefinition = Class.forName(daoProps.getString("contact.dao.class"));
                return (ContactDAO) classDefinition.newInstance();
            }

        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        throw new DAOException("Can't return DAO, fatal.");
    }

}

