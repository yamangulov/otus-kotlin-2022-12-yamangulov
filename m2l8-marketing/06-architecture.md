Возможная архитектура приложения находится в стадии проектирования и сильно зависит от того, какое решение будет выбрано окончательно. На данный момент рассматривается два таких варианта:

1) взаимодействие с Clickhouse через http-интерфейс, в этом случае предполагается примерно такая архитектура:

![](/media/stranger/repo/otus-kotlin-2022-12-yamangulov/m1l8-marketing/архитектура_http.png)

2) взаимодействие с Clickhouse через jdbc, odbc драйверы, или с использование драйвера mysql для построения архитектуры, близкой по типу к MVC с использованием Spring Data JPA с некоторыми доработками:

![](/media/stranger/repo/otus-kotlin-2022-12-yamangulov/m1l8-marketing/архитектура_jpa.png)