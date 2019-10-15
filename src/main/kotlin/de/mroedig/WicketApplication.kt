package de.mroedig

import de.mroedig.service.BasicAuthenticationSession
import de.mroedig.web.pages.HomePage
import de.mroedig.web.pages.LoginPage
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.spring.injection.annot.SpringComponentInjector

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 */
class WicketApplication : AuthenticatedWebApplication() {
    /**
     * @see org.apache.wicket.Application.getHomePage
     */
    override fun getHomePage(): Class<out WebPage?> {
        return HomePage::class.java
    }

    /**
     * @see org.apache.wicket.Application.init
     */
    public override fun init() {
        super.init()
        componentInstantiationListeners.add(SpringComponentInjector(this))
        mountPage("/login", LoginPage::class.java)
        // add your configuration here

    }

    override fun getWebSessionClass(): Class<out AbstractAuthenticatedWebSession?> {
        return BasicAuthenticationSession::class.java
    }

    override fun getSignInPageClass(): Class<out WebPage?> {
        return LoginPage::class.java
    }
}