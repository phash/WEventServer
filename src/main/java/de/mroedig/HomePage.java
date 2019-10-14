package de.mroedig;

import de.mroedig.domain.entity.Blubbs;
import de.mroedig.service.EsService;
import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import javax.inject.Inject;

@AuthorizeInstantiation("SIGNED_IN")
public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;
    @Inject
    private EsService esService;

    public HomePage(final PageParameters parameters) {
        super(parameters);

        add(new Label("version", Model.of(esService.name())));
        Blubbs b = new Blubbs();
        b.setAge(1);
        b.setName("sdf");
        esService.enterBlubbs(b);
        // TODO Add your page's components here

        add(new BookmarkablePageLink<Void>("goToHomePage", getApplication().getHomePage()));

        add(new Link<Void>("logOut") {

            @Override
            public void onClick() {
                AuthenticatedWebSession.get().invalidate();
                setResponsePage(getApplication().getHomePage());
            }
        });

    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        AuthenticatedWebApplication app = (AuthenticatedWebApplication) Application.get();
        //if user is not signed in, redirect him to sign in page
        if (!AuthenticatedWebSession.get().isSignedIn())
            app.restartResponseAtSignInPage();
    }
}
