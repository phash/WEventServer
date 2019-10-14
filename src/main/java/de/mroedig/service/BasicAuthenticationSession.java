package de.mroedig.service;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class BasicAuthenticationSession extends AuthenticatedWebSession {

    public BasicAuthenticationSession(Request request) {
        super(request);
    }

    @Override
    public boolean authenticate(String username, String password) {
        //user is authenticated if both username and password are equal to 'wicketer'
        return username.equals(password) && username.equals("wicketer");
    }

    @Override
    public Roles getRoles() {
        Roles resultRoles = new Roles();

        if (isSignedIn())
            resultRoles.add("SIGNED_IN");
/*
        if(userName.equals("superuser"))
            resultRoles.add(Roles.ADMIN);
*/
        return resultRoles;
    }
}