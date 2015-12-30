WITH t_purchases AS
    (
        SELECT
            COUNT(*) AS count
        FROM
            t_transactions
        WHERE
            t_transaction_operation = 'buy'
            AND ts_transaction_time > now() - '5 minutes'::interval
    ),
t_sales AS
    (
        SELECT
            COUNT(*) + 1 AS count
        FROM
            t_transactions
        WHERE
            t_transaction_operation = 'sell'
            AND ts_transaction_time > now() - '5 minutes'::interval
    )
SELECT
    t_purchases.count::numeric / t_sales.count::numeric AS ratio
FROM
    t_purchases,
    t_sales
;
