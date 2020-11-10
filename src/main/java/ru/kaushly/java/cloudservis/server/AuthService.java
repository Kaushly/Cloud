package ru.kaushly.java.cloudservis.server;

import java.util.Objects;
import java.util.UUID;

public interface AuthService {
    Record findRecord(String login, String password);

    class Record {
        private UUID id;
        private String login;
        private String password;

        public Record(String login, String password) {
            id = new UUID(0, 100000);
            this.login = login;
            this.password = password;
        }

        public UUID getId() {
            return id;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Record record = (Record) o;
            return id == record.id &&
                    login.equals(record.login) &&
                    password.equals(record.password);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, login, password);
        }
    }
}
