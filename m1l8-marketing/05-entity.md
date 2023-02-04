Описание сущностей проекта существенно зависит от выбора архитектуры. Приведем оба варианта:

#### Вариант 1

1) dbms clickhouse
2) http-client
3) clickhouse-row-client
4) clickhouse-resultset-client
5) clickhouse-entity-client
6) RowRequest
7) ResultSetRequest
8) EntityRequest
9) RowResponse
10) ResultSetResponse
11) Entity<T>
12) RestController

#### Вариант 2

1) dbms clickhouse
2) driver
3) repository
4) service
5) RestController
6) JPA repository Queries/methods
7) JPA repository native Queries/methods
8) service methods
9) RestController methods
10) RowResponse
11) ResultSetResponse
12) Entity<T>