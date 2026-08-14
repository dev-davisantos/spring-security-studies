# Spring Security Studies

## Objective:
 **This repositoriy was created to document my Spring Security studies.**

## Commits:
**Note: I will explain in this readme everything I've learned in each step of the course or tests, using commits to document what i did or learn**

### feat: learn how to create a default securityFilterChain config, and create a controller for testing this (#257facb)
- Learned how default configs of spring security works
- How to create a simple configuration
- Created a simple config for route "/public" and others
- Created a controller to test this routes

### feat: learn how to add in memory initial users, and how to create a password encoder (#e2942be)
- Learned how UserDetailsService works
- Learned how to create a UserDetailsService with in memory users
- Learned how Spring Security can authenticated by UserDetails
- Learned how to create a password encoder
- Learned how Spring encode and use encoder to matches the password

### feat: learn how to create a custom authentication provider (#ec9e858)
- Learned how Authentication Provider works by default
- Learned how to create a own custom Authentication provider
- Learned how to use my own Authentication Provider in my SecurityFilterChain

### feat: learn how to add a custom security filter (#dbd79de)
- Learned how to create a filter with OncePerRequestFilter
- Learned how to add to filter chain this custom filter
- Learned how to configure the sequence of filters

### feat: learn about diference between roles and authorities, and how to change the roles prefix (#968581b)
- Understood how Spring Security read and proccess roles and authorities
- Learn how to create a authorization for a url of endpoints
- Creating a new prefix for roles
- Tested a prefix GROUP_ for role (using GROUP_ADMIN role)

### fix: cleaning the security configurations, for using authorization in endpoints by controller
- Remove custom authentication provider from security chain
- Remove a custom prefix for roles
- Change the user from SecretAuthenticationFilter, from role "ROLE_USER" to "ROLE_SPECIAL"
- Enable my in memory UserDetailsService

### feat: add in controller permission control, with @EnableMethodSecurity and @PreAuthorize
- Used @EnabledMethodSecurity (EnabledSecurity = true) to enable in controller permission control
- Used authorization.requestMatchers to set public or non public routes in API.
- Used @PreAuthorize to set a admin only endpoint
- Debuged my API to find a 401 for all users, and realize that Spring is using CustomAuthenticationProvider automatically because it's have an @Component annotation, same if it's not configured in SecurityFilterChain. And i left the class to document an old provider that i used, but let the annotation like a component, to not be activated again
- Tested the routes after fixed the 401 bug.