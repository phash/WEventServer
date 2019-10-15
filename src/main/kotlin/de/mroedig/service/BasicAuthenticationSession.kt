package de.mroedig.service

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import org.apache.wicket.authroles.authorization.strategies.role.Roles
import org.apache.wicket.request.Request

class BasicAuthenticationSession(request: Request?) : AuthenticatedWebSession(request) {
    public override fun authenticate(username: String, password: String): Boolean {
        //user is authenticated if both username and password are equal to 'wicketer'

        return username == password && username == "wicketer"
    }

    override fun getRoles(): Roles {
        val resultRoles = Roles()
        if (isSignedIn) resultRoles.add("SIGNED_IN")
/*
        if(userName.equals("superuser"))
            resultRoles.add(Roles.ADMIN);
*/

        return resultRoles
    }
}