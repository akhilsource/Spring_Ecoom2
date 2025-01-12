package com.example.Servicies;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

@Service
public class Order_Id_Genration {
    public String generateOrderId(String username) {
        String sanitizedUsername = username.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        long timestamp = Instant.now().toEpochMilli();
        String rawOrderId = sanitizedUsername + timestamp;
        String hashedOrderId = hashString(rawOrderId);
        // Step 5: Format the order ID (prepend with "ORD-")
        return "ORD-" + sanitizedUsername + "-" + hashedOrderId;
    }
    private String hashString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert bytes to hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.substring(0, 8); // Truncate to first 8 characters for brevity
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }
}

