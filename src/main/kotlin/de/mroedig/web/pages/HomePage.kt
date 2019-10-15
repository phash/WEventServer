package de.mroedig.web.pages

import de.mroedig.domain.entity.Blubbs
import de.mroedig.service.EsService
import org.apache.wicket.Application
import org.apache.wicket.Page
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.model.Model
import org.apache.wicket.request.mapper.parameter.PageParameters
import javax.inject.Inject

@AuthorizeInstantiation("SIGNED_IN")
class HomePage(parameters: PageParameters?) : WebPage(parameters) {
    @Inject
    private val esService: EsService? = null

    override fun onConfigure() {
        super.onConfigure()
        val app = Application.get() as AuthenticatedWebApplication
        //if user is not signed in, redirect him to sign in page
        if (!AuthenticatedWebSession.get().isSignedIn) app.restartResponseAtSignInPage()
    }

    companion object {
        private const val serialVersionUID = 1L
    }

    init {
        add(Label("version", Model.of(esService!!.name())))
        val b = Blubbs()
        b.age = 1
        b.name = "sdf"
        esService.enterBlubbs(b)
        // TODO Add your page's components here

        add(BookmarkablePageLink<Any, Page>("goToHomePage", application.homePage as Class<Page>?))
        add(object : Link<Void?>("logOut") {
            override fun onClick() {
                AuthenticatedWebSession.get().invalidate()
                setResponsePage(application.homePage)
            }
        })
    }
}