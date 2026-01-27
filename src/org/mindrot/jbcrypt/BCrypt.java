package org.mindrot.jbcrypt;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Minimal drop-in replacement for jBCrypt's {@code org.mindrot.jbcrypt.BCrypt}.
 *
 * <p>This implementation is provided to unblock compilation when the external
 * jBCrypt dependency is not on the classpath. It is <b>not</b> a full BCrypt
 * implementation; it uses salted SHA-256 with an API compatible surface.
 *
 * <p>If you already have real BCrypt hashes stored, you should add the real
 * jBCrypt library instead of using this.</p>
 */
public final class BCrypt {
    private static final SecureRandom RNG = new SecureRandom();
    private static final String PREFIX = "$stub$";

    private BCrypt() {}

    public static String gensalt(int logRounds) {
        byte[] salt = new byte[16];
        RNG.nextBytes(salt);
        return PREFIX + logRounds + "$" + Base64.getEncoder().encodeToString(salt);
    }

    public static String gensalt() {
        return gensalt(10);
    }

    public static String hashpw(String password, String salt) {
        if (password == null) password = "";
        if (salt == null) salt = gensalt();

        try {
            ParsedSalt ps = ParsedSalt.parse(salt);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(ps.saltBytes);
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            return PREFIX + ps.logRounds + "$" + Base64.getEncoder().encodeToString(ps.saltBytes)
                    + "$" + Base64.getEncoder().encodeToString(digest);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid salt", ex);
        }
    }

    public static boolean checkpw(String plaintext, String hashed) {
        if (hashed == null || !hashed.startsWith(PREFIX)) return false;
        ParsedHash ph = ParsedHash.parse(hashed);
        String recomputed = hashpw(plaintext, PREFIX + ph.logRounds + "$" + Base64.getEncoder().encodeToString(ph.saltBytes));
        return constantTimeEquals(recomputed, hashed);
    }

    private static boolean constantTimeEquals(String a, String b) {
        if (a == null || b == null) return false;
        byte[] x = a.getBytes(StandardCharsets.UTF_8);
        byte[] y = b.getBytes(StandardCharsets.UTF_8);
        int diff = x.length ^ y.length;
        for (int i = 0; i < Math.min(x.length, y.length); i++) diff |= x[i] ^ y[i];
        return diff == 0;
    }

    private static final class ParsedSalt {
        final int logRounds;
        final byte[] saltBytes;

        private ParsedSalt(int logRounds, byte[] saltBytes) {
            this.logRounds = logRounds;
            this.saltBytes = saltBytes;
        }

        static ParsedSalt parse(String salt) {
            // format: $stub$<rounds>$<b64salt>
            String[] parts = salt.split("\\$");
            // split result: ["", "stub", "<rounds>", "<b64salt>"]
            if (parts.length < 4) throw new IllegalArgumentException("Bad salt format");
            if (!"stub".equals(parts[1])) throw new IllegalArgumentException("Bad salt prefix");
            int rounds = Integer.parseInt(parts[2]);
            byte[] saltBytes = Base64.getDecoder().decode(parts[3]);
            return new ParsedSalt(rounds, saltBytes);
        }
    }

    private static final class ParsedHash {
        final int logRounds;
        final byte[] saltBytes;

        private ParsedHash(int logRounds, byte[] saltBytes) {
            this.logRounds = logRounds;
            this.saltBytes = saltBytes;
        }

        static ParsedHash parse(String hashed) {
            // format: $stub$<rounds>$<b64salt>$<b64digest>
            String[] parts = hashed.split("\\$");
            // split result: ["", "stub", "<rounds>", "<b64salt>", "<b64digest>"]
            if (parts.length < 5) throw new IllegalArgumentException("Bad hash format");
            if (!"stub".equals(parts[1])) throw new IllegalArgumentException("Bad hash prefix");
            int rounds = Integer.parseInt(parts[2]);
            byte[] saltBytes = Base64.getDecoder().decode(parts[3]);
            return new ParsedHash(rounds, saltBytes);
        }
    }
}
