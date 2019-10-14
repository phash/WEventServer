package de.mroedig;

import de.mroedig.domain.entity.Blubbs;
import de.mroedig.service.EsService;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

import javax.inject.Inject;

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

    }
}
