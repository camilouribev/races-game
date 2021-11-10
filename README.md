# Game-dado
Juego de dados para multiples jugadores.

Proyecto demo en donde se una una separación de resposabilidades usando los conceptos de:
- Agregado
- Comandos y Queries
- Command/Event Handles
- Repositorio
- DDD 


## 1. Comando de creación de juego 
* POST http://localhost:8080/createGame
* BODY
<pre>
{
    "id": "fffff-ggg-jjjjj",
    "type": "",
    "gamers": [
         "Raul Andres",
         "Pedro",
         "Juan"
    ]
}
</pre>

## 2. Query para consultar el juego y su estado
* GET: http://localhost:8080/game/fffff-ggg-jjjjj
* RESULT:
<pre>
{
    "id": "fffff-ggg-jjjjj",
    "gamers": {
        "5257b4d6-5c87-4871-93c3-b2b9ce04d706": {
            "id": "5257b4d6-5c87-4871-93c3-b2b9ce04d706",
            "name": "Raul Andres"
        },
        "8dda6205-f54c-4643-a017-71b6f0353319": {
            "id": "8dda6205-f54c-4643-a017-71b6f0353319",
            "name": "Juan"
        },
        "e5834d8e-5195-41fc-993e-c731dbce4fab": {
            "id": "e5834d8e-5195-41fc-993e-c731dbce4fab",
            "name": "Pedro"
        }
    },
    "inProgress": false,
    "winner": {
        "id": "e5834d8e-5195-41fc-993e-c731dbce4fab",
        "name": "Pedro"
    }
}
</pre>

## Query para determinar el ganador del juego
* GET http://localhost:8080/game/fffff-ggg-jjjjj/winner
* RESULT:
<pre>
{
    "id": "e5834d8e-5195-41fc-993e-c731dbce4fab",
    "name": "Pedro"
}

</pre>

## Comando para iniciar el guejo con la apustar por cada jugador

* POST http://localhost:8080/startGame
* BODY:
<pre>
{
    "id": "fffff-ggg-jjjjj",
    "type": "",
    "gamerBet": {
        "5257b4d6-5c87-4871-93c3-b2b9ce04d706": 3,
        "8dda6205-f54c-4643-a017-71b6f0353319": 6,
        "e5834d8e-5195-41fc-993e-c731dbce4fab": 5
    }
}
</pre>

