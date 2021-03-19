<img alt="Logo" src="http://coderslab.pl/svg/logo-coderslab.svg" width="400">


Przed przystąpieniem do pracy  przeczytaj poniższe wskazówki.
## Jak zacząć?

1. Zaimportuj projekt jako projekt `Maven`, wg poniższych wskazówek:

	* W `IntelliJ` wybieramy: `File –> New –> Project from Existing Sources...`
	* Wskazujemy lokalizację katalogu ze sklonowanym projektem i zatwierdzamy.
	* Następnie w nowym oknie wybieramy: `Import project from external model` i wskazujemy `Maven`
	* Wybieramy kolejno opcje: `Next –> Next –> Next –> Finish`
	
	
	-----------------------------properties to work on localhost database--------------------------
	
	spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://localhost:3306/charitydonation?serverTimezone=UTC
    spring.datasource.username=
    spring.datasource.password=
    
    spring.mvc.view.prefix=/WEB-INF/views/
    spring.mvc.view.suffix=.jsp
    spring.mvc.locale=pl_PL
    spring.mvc.locale-resolver=fixed
    
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username=
    spring.mail.password=
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
    
    
    
    -----------------------------properties to work on heroku remote database--------------------------
    
    spring.datasource.url=${JDBC_DATABASE_URL}
    spring.datasource.username=${JDBC_DATABASE_USERNAME}
    spring.datasource.password=${JDBC_DATABASE_PASSWORD}
    spring.jpa.show-sql=false
    spring.jpa.generate-ddl=true
    spring.jpa.hibernate.ddl-auto=create
