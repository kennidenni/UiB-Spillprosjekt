### Siste utgave av et stabilt spill er å finne i følgende brancher:  
  
184-carlthecrasher-release-beta-testing  
  
#184 (related issue) 
  
186-foodfeud-release-beta-testing 
  
#186 (related issue)  
  
### Script for bygging av .jar fil  
  
I [build.gradle](src/build.gradle) er det definer en jobb for bygging av .jar filer i prosjektene "Carl the crasher" og "FoodFeud".  
  
Denne jobben heter `dist` og er tilgjengelig i rotprosjektet:  
  
`./gradlew build && ./gradlew dist`  
filene blir lagt i:  
car-game/build/libs/teamdank-cargame.jar  
food-feud/build/libs/teamdank-foodfeud.jar  
  
jobbene kan også kjøres uavhengig av hverandre:  
`./gradlew build && ./gradlew teamdank-cargame:dist`  
`./gradlew build && ./gradlew teamdank-foodfeud:dist`  
Disse jobbene kan kjøres på alle branch'er  