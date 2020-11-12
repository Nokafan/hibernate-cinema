**Project Cinema Hall**

Back-end part of the cinema hall project. RESTful API system to sale tickets online. It was made by using N-tier architecture, and KISS, DRY, SOLID principals.

**Used technologies:**
-  Java Core, Java 11
-  Maven
-  MySql
-  Spring MVC
-  Spring Security
-  Hibernate
-  Log4j
-  Lombok
-  Validator

Only authenticated users can use the full functionality of the app. On login page you can sign in as admin  which was injected by default (use login: admin@i.ua, password: 1234). New registered user gets role of USER by default. At the moment, the users can have two roles: USER or ADMIN. It’s also possible to have these roles to be combined. So if a users have two roles - they can use both functionality as user and as admin.

**Configuration guide (for local machine)**

Make sure you have MySQL Workbench. Download and open the project. You have to create schema hibernate_cinema  in your RDBMS. Set up DB properties at db.properties, set correct username, and password for access to DB.
Configure Tomcat: Deployment — war_exploded, context address — "/"

Maven is used as the packaging tool, and you need to enable the import all of the dependencies and plugins.

Make sure that Annotation processors are enabled.

**Author**
https://github.com/Nokafan
