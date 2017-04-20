Continous Integration/Deployment
---
Prosjektet har en gitlab integration pipeline satt opp for å holde god kodestil og at prosjektet ikke feiler uten at vi merker det.  

Innstillingene for pipelinen ligger i [gitlab-ci.yml](../.gitlab-ci.yml) filen.

Om noen ønsker å bidra til eller lære om pipelinen så er det bare å kontakte @heg063.  
Jeg skal prøve å forklare om oppsettet her.

---

Alt som blir gjort i pipelinen kjøres av programmer som er satt opp på en server med adressen 158.39.74.42

**SonarQube** ligger på rota til port 9000: <http://158.39.74.42:9000/>  
SonarQube er en server i seg selv.

**API** fra master ligger på `/docs/javadoc` til html-porten (80):  
<http://158.39.74.42:80/docs/javadoc/>

**HTML**-porten hånderes av en NGINX server og roten <http://158.39.74.42:80/>  
ligger i /srv/nginx/pages/gitlabapi/public/

---

![pipeline](GitLab Pipeline.png)