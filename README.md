# IPASS

In de cursus Individual Propedeuse Assessment(TICT-V1IPASS-15) moet er individueel gewerkt  worden aan een webapplicatie. 
Het doel van deze cursus is dat er met de technische kennis, die ik heb opgedaan in de huidige en voorgaande periodes, zelfstandig een project kan voorbereiden, ontwerpen en realiseren, hiermee toon ik aan dat ik de stof voldoende beheers.
Mijn webapplicaite is een dashboard voor de stichting ‘[De Oude Hollandse Waterlinie](https://www.oudehollandsewaterlinie.nl/)’, welke vershillende objecten(voornamelijk informatieborden) beheert. Medewerkers van de stichting gaan deze objecten langs en controleren deze ieder op hun gesteldheid.
Om het controleproces gebruiksvriendelijker, overzichtelijker en een stuk sneller te laten verlopen wordt er een dashboard gerealiseerd welke in contact staat met een centrale database van objecten.

Heroku: [https://ohw-dashboard.herokuapp.com/](https://ohw-dashboard.herokuapp.com/)



# Project setup

### Projectstructuur:
![https://i.imgur.com/o9GhtBu.png](https://i.imgur.com/o9GhtBu.png)
```
ohw-dashboard/
  ├─┬ src     → backend met Spring Boot code
  │ ├── /main
  ├─┬ www    → frontend met Vue.js code
  │ ├── /src → Vue componenten/views
  │ └── /dist → Geoptimalisserde code na 'vue build'
  └── pom.xml     → Maven pom voor backend dependencies
```



### Benodigdheden:
Node ([https://nodejs.org/en/download/](https://nodejs.org/en/download/)), Maven ([https://maven.apache.org/install.html](https://maven.apache.org/install.html)), Vue CLI ([https://cli.vuejs.org/](https://cli.vuejs.org/))

#### MacOSX

```
brew install node
brew install maven
npm install --global vue-cli
```

#### Linux

```
sudo apt update
sudo apt install node
npm install --global vue-cli
```

#### Windows

```
choco install npm
npm install --global vue-cli
```

#### Clone
```
git clone https://github.com/yannickhj/ohw_dashboard.git
```
### Backend
```
cd /ohw_dashboard
mvn install
mvn spring-boot:run
```
Of in Eclipse met [Spring Boot extentie](https://spring.io/tools/sts) > Run as.. > Spring Boot Application.
Dit start de REST API.
### Frontend
```
npm install
vue ui
```
Vue UI opent een overzichtelijk dashboard waarin je de applicatie kunt starten door de 'serve' task uit te voeren. 
De applicatie is vervolgens te vinden op: ```http://localhost:8081/```


## Demo
![Demo gif](http://g.recordit.co/5Wt7MTdWkC.gif)






