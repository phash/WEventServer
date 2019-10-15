package de.mroedig

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaDialect
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.servlet.ServletContext
import javax.servlet.ServletRegistration.Dynamic
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = ["de.mroedig.service", "de.mroedig.domain"])
@EnableJpaRepositories(basePackages = ["de.mroedig.domain"])
@EnableTransactionManagement
class EsConfig : WebSecurityConfigurerAdapter(), WebApplicationInitializer {
    @Bean
    fun dataSource(): DataSource {
        // org.apache.commons.dbcp.BasicDataSource

        val ds = JndiDataSourceLookup()
        return ds.getDataSource("jdbc/eserv")
    }

    @Bean
    public override fun userDetailsService(): UserDetailsService {
        val user: UserDetails? = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build()
        return InMemoryUserDetailsManager(user)
    }

    @Bean
    fun entityManagerFactory(dataSource: DataSource?): LocalContainerEntityManagerFactoryBean {
        val entityManagerFactory = LocalContainerEntityManagerFactoryBean()
//    entityManagerFactory.setPersistenceUnitName("hibernate-persistence");


        entityManagerFactory.dataSource = dataSource
        entityManagerFactory.jpaVendorAdapter = HibernateJpaVendorAdapter()
        entityManagerFactory.jpaDialect = HibernateJpaDialect()
        entityManagerFactory.setPackagesToScan("de.mroedig.domain.entity")
        entityManagerFactory.jpaPropertyMap = hibernateJpaProperties()
        return entityManagerFactory
    }

    private fun hibernateJpaProperties(): Map<String, *> {
        val properties = HashMap<String, String>()
        properties["hibernate.hbm2ddl.auto"] = "create"
        properties["hibernate.show_sql"] = "true"
        properties["hibernate.format_sql"] = "true"
        properties["hibernate.default_schema"] = "events"
        // properties.put("hibernate.hbm2ddl.import_files", "insert-data.sql");
        //  properties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");


        properties["hibernate.dialect"] = "org.hibernate.dialect.PostgreSQLDialect"
        /*
        properties.put("hibernate.c3p0.min_size", "2");
        properties.put("hibernate.c3p0.max_size", "5");
        properties.put("hibernate.c3p0.timeout", "300"); // 5mins
       */

        return properties
    }

    @Bean
    fun transactionManager(emf: EntityManagerFactory?): JpaTransactionManager {
        val jpaTransactionManager = JpaTransactionManager()
        jpaTransactionManager.entityManagerFactory = emf
        return jpaTransactionManager
    }

    override fun configure(web: WebSecurity) {
        web.ignoring()
                .antMatchers(
                        "WEB-INF/res/**",
                        "/WEB-INF/res/img/**",
                        "/WEB-INF/res/css/**",
                        "/res/css/**",
                        "/res/img/**"
                        , "/res**")
    }

    override fun onStartup(container: ServletContext) {
        // Create the 'root' Spring application context

        val rootContext = AnnotationConfigWebApplicationContext()
        rootContext.register(EsConfig::class.java)

        // Manage the lifecycle of the root application context


        container.addListener(ContextLoaderListener(rootContext))

        // Register and map the dispatcher servlet


        val dispatcher: Dynamic = container.addServlet("dispatcher", DispatcherServlet(rootContext))
        dispatcher.setLoadOnStartup(1)
        dispatcher.addMapping("/")
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.httpBasic().and()
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/res/**").permitAll()
                .antMatchers("/res/img/**").permitAll()
                .antMatchers("/res/css/**").permitAll()
                .antMatchers("/res/js/**").permitAll()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .csrf().disable().cors().disable().headers().disable()
    }
}