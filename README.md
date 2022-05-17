# zadanieStaz-restAPI
Do uruchomienia programu należy pobrać 4 projekty, każdy z nich skompilować i uruchomić.
Następnie przykładowe requesty znajdują się w pliku requests.http zamieszczonym na repozytorium.
W obecnej formie, aby stworzyć rezerwację dla danego użytkownika na konkretną prelekcję, należy najpierw stworzyć użytkownika, a dopiero następnym krokiem jest tworzenie rezerwacji.

Aby dostać się do bazy danych h2 należy wejść pod konkretny adres, odpowiednio:
- baza danych lecture       localhost:8081/h2-console
- baza danych user          localhost:8082/h2-console
- baza danych reservation   localhost:8083/h2-console
Dane do logowania to (login: sa, hasło: ).
