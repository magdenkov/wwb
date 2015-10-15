DROP TABLE clients IF EXISTS;
DROP TABLE accounts IF EXISTS;
DROP TABLE transactions IF EXISTS;


CREATE TABLE clients (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30),
  birth_date DATE,
  address    VARCHAR(255)
);
CREATE INDEX clients_last_name ON clients (last_name);

CREATE TABLE accounts (
  id          INTEGER IDENTITY PRIMARY KEY,
  client_id      INTEGER NOT NULL,
  money_amount  INTEGER NOT NULL
);
ALTER TABLE accounts ADD CONSTRAINT fk_accounts_clients FOREIGN KEY (client_id) REFERENCES clients (id);
CREATE INDEX accounts_client_id ON accounts (client_id);

CREATE TABLE transactions (
  id          INTEGER IDENTITY PRIMARY KEY,
  account_id_from      INTEGER NOT NULL,
  account_id_to      INTEGER NOT NULL,
  money_transfer_amount  INTEGER NOT NULL,
  message  VARCHAR(255),
  t_date DATE
);
ALTER TABLE transactions ADD CONSTRAINT fk_transactions_accounts_from FOREIGN KEY (account_id_from) REFERENCES accounts (id);
ALTER TABLE transactions ADD CONSTRAINT fk_transactions_accounts_to FOREIGN KEY (account_id_to) REFERENCES accounts (id);
CREATE INDEX transactions_accounts_id ON transactions (account_id_from, account_id_to);



