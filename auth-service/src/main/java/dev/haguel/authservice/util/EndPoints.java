package dev.haguel.authservice.util;

public class EndPoints {
    public static class Auth {
        private static final String BASE_ENDPOINT = "/auth";
        public static final String LOGIN = BASE_ENDPOINT + "/login";
        public static final String VALIDATE_TOKEN = BASE_ENDPOINT + "/validate";
    }
}
