# Space Adventure

Java terminal app \
School project 🏫

## Run project
```
git clone https://github.com/KarelHudera/Space_Adventure.git
cd Space_Adventure/src
javac Main.java
java Main
```

## Game play

### Story
Lost in a mysterious rocket facility, your mission is clear: Escape the unknown planet.
Navigate through rooms, gather essential items, and launch the rocket.
Unravel the secrets of the facility and embark on a thrilling journey to escape the enigmatic world.
Good luck!

### Map
``` 
                     ┌──────────┐
                     │launch pad│
                     └────┬┬────┘
                          ││
                     ┌────┴┴────┐        ┌───────────┐
                     │white room│        │server room│
                     └────┬┬────┘        └────┬┬─────┘
                          ││                  ││
  ┌──────────┐   ┌────────┴┴────────┐   ┌─────┴┴───────┐
  │laboratory│===│technical facility│===│control center│
  └──────────┘   └────────┬┬────────┘   └──────────────┘
                          ││
                  ┌───────┴┴───────┐
                  │Storage facility│
                  └────────────────┘
```

### Commands
```
Type 'help' to show this message.
Type 'goTo ROOMNAME' to move to a different room.
Type 'whereAmI' to check your current location.
Type 'showMap' to display game map
Type 'search' to show items in current room
Type 'pick ITEM' to put item to your inventory
Type 'inventory' to show your inventory
Type 'startRocket' to win the game
Type 'exit' to end the game.
```

## License
```xml
Copyright 2024 Karel Hudera

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```  