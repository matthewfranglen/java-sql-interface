CREATE TABLE t_transactions
    (
        i_transaction_id SERIAL PRIMARY KEY,
        t_transaction_operation TEXT NOT NULL,
        ts_transaction_time TIMESTAMP WITH TIME ZONE DEFAULT now()
    )
;
