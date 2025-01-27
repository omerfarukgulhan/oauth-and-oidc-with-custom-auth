package com.ofg.oauth.security;

import com.ofg.oauth.model.entity.User;
import com.ofg.oauth.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return processOAuth2User(userRequest.getClientRegistration().getRegistrationId(), oAuth2User);
    }

    private OAuth2User processOAuth2User(String registrationId, OAuth2User oAuth2User) {
        String providerId = oAuth2User.getName();
        String email = oAuth2User.getAttribute("email"); // Assuming email is available

        Optional<User> existingUser = userRepository.findByOauth2ProviderAndOauth2Id(registrationId, providerId);
        User user;
        if (existingUser.isPresent()) {
            user = existingUser.get();
            // You might want to update user details if needed
        } else {
            // Create a new user
            String username = email != null ? email.split("@")[0] : "oauthuser_" + providerId;
            user = new User(username, email, registrationId, providerId);
            user.setUsername(username);
            user.setEmail(email);
            user.setOauth2Provider(registrationId);
            user.setOauth2Id(providerId);
            userRepository.save(user);
        }
        return oAuth2User; // You might need to adapt this to your UserDetails implementation if needed
    }
}