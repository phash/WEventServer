package de.mroedig.web.pages

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.form.PasswordTextField
import org.apache.wicket.markup.html.form.StatelessForm
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.util.string.Strings

class LoginPage : WebPage() {
    private val username: String? = null
    private val password: String? = null
    override fun onInitialize() {
        super.onInitialize()
        val form: StatelessForm<LoginPage?> = object : StatelessForm<LoginPage?>("form") {
            override fun onSubmit() {
                if (Strings.isEmpty(username)) return
                val authResult = AuthenticatedWebSession.get().signIn(username, password)
                //if authentication succeeds redirect user to the requested page


                if (authResult) continueToOriginalDestination()
            }
        }.also {
            it.model = CompoundPropertyModel<LoginPage>(this)
            it.add(TextField<Any?>("username"))
            it.add(PasswordTextField("password"))
            add(it)
        }
    }
}