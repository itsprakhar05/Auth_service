package authservice.service;

import authservice.entities.UserInfo;
import authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Token valid for 15 minutes
    private static final long TOKEN_EXPIRATION_MILLIS = 15 * 60 * 1000;

    public String createPasswordResetToken(String email) {
        Optional<UserInfo> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return null;
        }
        UserInfo user = userOpt.get();
        String token = UUID.randomUUID().toString();
        long expiry = Instant.now().toEpochMilli() + TOKEN_EXPIRATION_MILLIS;
        user.setPasswordResetToken(token);
        user.setPasswordResetTokenExpiry(expiry);
        userRepository.save(user);
        return token;
    }

    public boolean updatePassword(String token, String newPassword) {
        Optional<UserInfo> userOpt = userRepository.findByPasswordResetToken(token);
        if (userOpt.isEmpty()) {
            return false;
        }
        UserInfo user = userOpt.get();
        long now = Instant.now().toEpochMilli();
        if (user.getPasswordResetTokenExpiry() == null || user.getPasswordResetTokenExpiry() < now) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setPasswordResetToken(null);
        user.setPasswordResetTokenExpiry(null);
        userRepository.save(user);
        return true;
    }
}
