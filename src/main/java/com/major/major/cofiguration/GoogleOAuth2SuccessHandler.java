package com.sheryians.major.cofiguration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sheryians.major.model.Role;
import com.sheryians.major.model.User;
import com.sheryians.major.repository.RoleRepository;
import com.sheryians.major.repository.UserRepository;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
     final UserRepository userRepository;
	@Autowired
   final RoleRepository roleRepository;
   final DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public GoogleOAuth2SuccessHandler(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String email = (String) token.getPrincipal().getAttributes().get("email");

       // Optional<User> existingUser = userRepository.findUserByEmail(email);

        if (userRepository.findUserByEmail(email).isPresent()) {
            User user = new User();
            user.setFirstName((String) token.getPrincipal().getAttributes().getOrDefault("given_name", "Unknown"));
            user.setLastName((String) token.getPrincipal().getAttributes().getOrDefault("family_name", "User"));
            user.setEmail(email);

            List<Role> roles = new ArrayList<>();
            roles.add(roleRepository.findById(2).get());
           // roleRepository.findById(2).ifPresent(roles::add); // Add role if it exists
            user.setRoles(roles);

            userRepository.save(user);
        }

        // Redirect after successful authentication
        redirectStrategy.sendRedirect(request, response, "/");
    }
}
