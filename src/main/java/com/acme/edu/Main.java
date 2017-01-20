/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.edu;

import java.util.Properties;
import javax.naming.*;
import javax.naming.directory.*;

/**
 * Install Apache Directory Studio: http://directory.apache.org/studio/downloads.html
 */
public class Main {
    public static void main(String[] args) {
        Context initialContext = null;
        DirContext initialDirContext = null;

        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            props.put(Context.PROVIDER_URL, "ldap://localhost:10389/ou=system");

            props.put(Context.SECURITY_AUTHENTICATION, "simple");
            props.put(Context.SECURITY_PRINCIPAL, "uid=admin, ou=system");
            props.put(Context.SECURITY_CREDENTIALS, "secret");

            props.put("com.sun.jndi.ldap.connect.timeout", "5000");
            props.put("com.sun.jndi.ldap.connect.pool", "true");
            props.put("com.sun.jndi.ldap.connect.pool.debug", "fine");

            initialContext = new InitialContext(props);
            final Context configurationContext = (Context)initialContext.lookup("ou=configuration");
            final NamingEnumeration<Binding> configurationElements = configurationContext.listBindings("");
            while (configurationElements.hasMore()) {
                System.out.println(configurationElements.next().getName());
            }
            System.out.println("---");

            //Dir Examples
            initialDirContext = new InitialDirContext(props);
            final DirContext usersContext = (DirContext)initialDirContext.lookup("ou=users");
            NamingEnumeration<Binding> usersContextElements = usersContext.listBindings("");
            while (usersContextElements.hasMore()) {
                System.out.println(usersContextElements.next().getName());
            }

            Attributes attrs = new BasicAttributes(true);
            Attribute objclass = new BasicAttribute("objectclass");
            objclass.add("top");
            objclass.add("organizationalUnit");
            attrs.put(objclass);
//            usersContext.createSubcontext("ou=admins", attrs);

            Attributes personAttributes = new BasicAttributes();
            BasicAttribute personBasicAttribute = new BasicAttribute("objectclass");
            personBasicAttribute.add("top");
            personBasicAttribute.add("person"); //http://www.zytrax.com/books/ldap/ape/core-schema.html#person
            personBasicAttribute.add("uidObject"); //http://www.zytrax.com/books/ldap/ape/core-schema.html#uidObject
            personAttributes.put(personBasicAttribute);
            personAttributes.put("sn", "last name");
            personAttributes.put("description", "description");
            personAttributes.put("uid", "1");

            final DirContext adminsContext = (DirContext) usersContext.lookup("ou=admins");
            adminsContext.rebind("cn=Ivan Ivanov", new User(1, "Ivan"), personAttributes); //Complex question about saving java objects to production LDAP

            personAttributes.put("uid", "2");
            adminsContext.rebind("cn=Petr Petrov", new User(2, "Petr"), personAttributes);


            Attributes matchAttrs = new BasicAttributes(true);
            matchAttrs.put(new BasicAttribute("cn", "Petr Petrov"));
            matchAttrs.put(new BasicAttribute("uid"));
            NamingEnumeration answer = initialDirContext.search("ou=admins,ou=users", matchAttrs);
            while (answer.hasMore()) {
                SearchResult sr = (SearchResult)answer.next();
                System.out.println(">>>" + sr.getName());
            }



        } catch (NamingException ne) {
            ne.printStackTrace();
        } finally {
            if (initialContext != null) try {
                initialContext.close();
            } catch (NamingException e) {
                e.printStackTrace();
            }

            if (initialDirContext != null) try {
                initialDirContext.close();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }
}