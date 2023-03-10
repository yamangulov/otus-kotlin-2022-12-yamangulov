[Файл](import-to-clickhouse-command.txt) - содержит команду для импорта данных из датасета в clickhouse

Сам датасет нужно выкачать, находясь в нужном вам каталоге ОС:

`wget http://prod.publicdata.landregistry.gov.uk.s3-website-eu-west-1.amazonaws.com/pp-complete.csv`

Так как исходный файл CSV имеет размер около 4GB, я добавил его в исключения, чтобы он не заливался в github в проекте