package db.repository;

import db.client.DBClient;

public abstract class Repository {
    protected final DBClient client;

    public Repository(DBClient client) {
        this.client = client;
    }

}
