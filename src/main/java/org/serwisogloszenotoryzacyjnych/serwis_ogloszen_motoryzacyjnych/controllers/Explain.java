package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.controllers;

import net.minidev.json.*;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.User;
import org.springframework.web.bind.annotation.*;

@RestController //Tu definujemy, że używamy kontrolera typu rest, wymagane w każdym
@RequestMapping("/example") //Tu definiujemy pierwszy parametr adresu. W tym wypadku będzie to http://cośtam.coś/EXAMPLE/cośtam
public class Explain extends Controller {   //Zawsze rozszerzamy plik Controller, zawiera podpięte serwisy.
    @PostMapping("/login")    //Definicja metody komunikacji http. Ważna rzecz, w nawiasie jest podstrona na jakiej będzie działał ten zasób. Sprawdzić metody HTTP, my używamy get, post, update, delete
    public JSONObject login(@RequestBody JSONObject json) { //Przyjmujemy i zwracamy obiekt JSONObject. Po prostu, jest wygodniejszy i daje więcej swobody, niż obiekty naszych klas
        JSONObject response =new JSONObject();  //Tu definiujemy i inicjalizujemy odpowiedź serwera na zapytanie. Będziemy do niej dokładać to, co chcemy przekazać

        User user = userService.get(json.get("username").toString());   //pobieramy użytkownika z bazy danych
        /*
        Tutaj po kolei:
        User user- wiadomo, definiujemy obiekt typu user

        userService-serwis użytkowników (pakiet services w drzewku plików). Jest on zdefiniowany w pliku Controller, tam trzeba podpinać kolejne serwisy

        .get()-metoda z serwisu, którą zdefiniowałem tworząc serwis. Możecie nazwać jak chcecie, ale im prościej, tym lepiej.

        json-obiekt, który został przysłany w zapytaniu

        .get("username")- odczytuje wartość jaka jest przypisana do klucza "username". Klucze zgodnie z praktyką powinny się zgadzać z nazwami pól w modelu, chyba, że nie istnieją, to nazwijcie jak chcecie, ale oznaczcie potem w komentarzu, czy coś, żebym wiedział

        .toString()-żeby odczytać to jako string
        */

        if(user==null){ //jeśli użytkownik nie istnieje serwis zwraca null
            response.put("status", false);  //dodajemy pierwszą wartość do odpowiedzi metodą put. put(KLUCZ, WARTOŚĆ). klucz zawsze stringiem, wartość dowolna
            response.put("message", "Brak użytkownika o podanej nazwie"); //i druga wartość. Może być ich ile chcemy

            return response;    //i zwracamy odpowiedź
        }

        System.out.println("|"+user.getPassword()+"| |"+json.get("password").toString()+"|"); //to jakieś tam moje wcześniejsze debugowania

        if(user.getPassword().equals(json.get("password").toString())){ //tu sprawdzam, czy hasło pobranego użytkownika jest takie samo, jak przysłane w json. Ważne, żeby używać .equel(), bo == nie działa
            session.setUser(user); //ustawiam na sesji tego użytkownika

            response.put("status", true);   //tu znowu składam odpowiedź
        }
        else{
            response.put("status", false);
            response.put("message", "Niepoprawny login lub hasło");
        }
        return response;
    }

    @GetMapping("/session_status")   //tu mapujemy metodę get
    public JSONObject status(){
        JSONObject result=new JSONObject();

        result.put("status", session.getStatus());
        result.put("username", session.getUser().getUsername());
        result.put("id", session.getUser().getId());

        return result;
    }

    @GetMapping("/logout")
    public JSONObject logout(){
        session.reset();

        JSONObject result = new JSONObject();
        result.put("status", true);

        return result;
    }

}

/*  Jeśli chodzi o metody http, to zasada jest w sumie prosta.
    Jeśli po prostu odczytujemy dane, metoda get. Może mieć parametr w adresie, wtedy używamy @PathVariable w argumencie funkcji.
    Dodajemy dane, to metoda post. Dane do tej metody przyjmujemy w JSONObject.
    Usuwamy, metoda delete. Do tego argument w url, żeby wiedzieć co usuwany (znowu @PathVariable)
    Aktualizujemy, to update. Dane w nagłówku pakietu, które trafią JSONObject i @PathVariable, żeby wiedzieć co aktualizuje.

    Do testowania używać trzeba klienta rest typu Postman, czy Insomnia. Przez przeglądarkę nie da rady.

    W razie pytań pisać na priv, czy na grupie. Jak będzie trzeba dajcie znać, napiszę jeszcze instrukcję do serwisów.

    Fajnie by było, gdybyście wszystkie stworzone url wklejali do pliku api_routes.txt i jakie argumenty przyjmuje. Ułatwi mi to testowanie i pisanie frontendu.
*/
