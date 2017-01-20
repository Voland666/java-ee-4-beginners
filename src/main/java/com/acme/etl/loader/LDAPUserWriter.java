package com.acme.etl.loader;

import com.acme.etl.core.User;
import com.acme.etl.exceptions.UserWriterException;
import org.apache.directory.api.ldap.model.schema.DitContentRule;

import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Collection;
import java.util.Properties;

/**
 * Created by vm.andreev on 20.01.17.
 */
public class LDAPUserWriter implements UserWriter {
    private final Properties properties;

    public LDAPUserWriter(Properties properties) {
        this.properties = properties;
    }

    private DirContext getAdminDirContext(DirContext userContext) throws NamingException {
        DirContext adminContext;
        try {
            adminContext = (DirContext) userContext.lookup("ou=admins");
        } catch (NameNotFoundException e) {
            Attributes adminOuAttributes = new BasicAttributes(true);
            Attribute adminObjectClass = new BasicAttribute("objectClass");
            adminObjectClass.add("top");
            adminObjectClass.add("organizationalUnit");
            adminOuAttributes.put(adminObjectClass);
            adminContext = userContext.createSubcontext("ou=admins", adminOuAttributes);
        }
        return adminContext;
    }

    private Attributes makeUserAttributes(User user) {
        Attributes userAttributes = new BasicAttributes(true);
        Attribute userObjectClass = new BasicAttribute("objectClass");
        userObjectClass.add("top");
        userObjectClass.add("person");
        userObjectClass.add("uidObject");
        userAttributes.put(userObjectClass);
        userAttributes.put(user.getUserIdAttribute("uid"));
        userAttributes.put(user.getUserNameAttribute("sn"));
        return userAttributes;
    }

    @Override
    public void save(Collection<User> users) throws UserWriterException {
        DirContext dirContext;
        try {
            dirContext = new InitialDirContext(properties);
            final DirContext userContext = (DirContext) dirContext.lookup("ou=users");
            try {
                final DirContext adminContext = getAdminDirContext(userContext);
                for (User user : users) {
                    try {
                        adminContext.bind("cn=" + user.getUserNameAttribute("sn").get().toString(), user, makeUserAttributes(user));
                        System.out.println("Saved: " + user + " to LDAP");
                    } catch (NamingException e) {
                        throw new UserWriterException("Unable to bind user", e);
                    }
                }
            } catch (NamingException e) {
                throw new UserWriterException("Unable to get admin organizational unit", e);
            }
        } catch (NamingException e) {
            throw new UserWriterException("Unable to get directory context", e);
        }
    }
}
