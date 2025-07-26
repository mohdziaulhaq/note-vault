package com.notevault.config;

import com.notevault.enums.AppRole;
import com.notevault.models.Role;
import com.notevault.models.User;
import com.notevault.repositories.RoleRepository;
import com.notevault.security.jwt.JwtUtils;
import com.notevault.security.services.UserDetailsImpl;
import com.notevault.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
// THE OAUTH FLOW
// To login .. user clicks on Login with github, frontend calls the below url
// http://localhost:8080/oauth2/authorization/github

// Redirect after successful login from Github (github calls below backend url )Authorization callback URL
// http://localhost:8080/login/oauth2/code/github (backend)
//
// From Backend to React (after successfull Oauth2 login)
// http://localhost:3000/oauth2/redirect



@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private final UserService userService;

    @Autowired
    private final JwtUtils jwtUtils;

    @Autowired
    RoleRepository roleRepository;

    @Value("${frontend.url}")
    private String frontendUrl;

    String username;

    String idAttributeKey;

    public OAuth2LoginSuccessHandler(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        if (oAuth2AuthenticationToken.getAuthorizedClientRegistrationId().equals("github") || oAuth2AuthenticationToken.getAuthorizedClientRegistrationId().equals("google")) {
            DefaultOAuth2User principal = (DefaultOAuth2User) oAuth2AuthenticationToken.getPrincipal();
            Map<String, Object> attributes = principal.getAttributes();
            String email = attributes.getOrDefault("email", "").toString();
            String name = attributes.getOrDefault("name", "").toString();

            if ("github".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())) {
                username = attributes.getOrDefault("login", "").toString();
                idAttributeKey = "id";
            } else if ("google".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())) {
                username = email.split("@")[0];
                idAttributeKey = "sub";
            } else {
                username = "";
                idAttributeKey = "id";
            }
            System.out.println("HEllo OAUTH: " + email + " : " + name + " : " + username);


            userService.findByEmail(email)
                    .ifPresentOrElse(user -> {
                                DefaultOAuth2User oAuth2User = new DefaultOAuth2User(
                                        List.of(new SimpleGrantedAuthority(user.getRole().getRoleName().name()))
                                        , attributes,
                                        idAttributeKey);
                                Authentication securityAuth = new OAuth2AuthenticationToken(
                                        oAuth2User,
                                        List.of(new SimpleGrantedAuthority(user.getRole().getRoleName().name())),
                                        oAuth2AuthenticationToken.getAuthorizedClientRegistrationId()
                                );
                                SecurityContextHolder.getContext().setAuthentication(securityAuth);
                            },
                            // if user is not present
                            () -> {
                                User newUser = new User();
                                Optional<Role> userRole = roleRepository.findByRoleName(AppRole.ROLE_USER);
                                if (userRole.isPresent()) {
                                    newUser.setRole(userRole.get()); // set existing role
                                } else {
                                    // handle case where the role is not found
                                    throw new RuntimeException("Role not found");
                                }
                                newUser.setEmail(email);
                                newUser.setUserName(username);
                                newUser.setSignUpMethod(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());
                                userService.registerUser(newUser);

                                DefaultOAuth2User oAuth2User = new DefaultOAuth2User(
                                        List.of(new SimpleGrantedAuthority(newUser.getRole().getRoleName().name())),
                                        attributes,
                                        idAttributeKey
                                );

                                Authentication securityAuth = new OAuth2AuthenticationToken(oAuth2User,
                                        List.of(new SimpleGrantedAuthority(newUser.getRole().getRoleName().name())),
                                        oAuth2AuthenticationToken.getAuthorizedClientRegistrationId());

                                SecurityContextHolder.getContext().setAuthentication(securityAuth);
                            });
        }
        this.setAlwaysUseDefaultTargetUrl(true);

        //JWT TOKEN LOGIC
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // Extract necessary attributes

        String email = (String) attributes.get("email");
        System.out.println("OAuth2LoginSuccessHandler: " + username + " : " + email);

        //Create UserDetailsImpl instance
        UserDetailsImpl userDetails = new UserDetailsImpl(
                null,
                username,
                email,
                null,
                false,
                oAuth2User.getAuthorities().stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getAuthority())).collect(Collectors.toList())
        );
        //Generate JWT token
        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        // redirect to the frontend with the jwt token
        String targetUrl = UriComponentsBuilder.fromUriString(frontendUrl + "/oauth2/redirect")
                .queryParam("token", jwtToken)
                .build().toUriString();
        this.setDefaultTargetUrl(targetUrl);
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
