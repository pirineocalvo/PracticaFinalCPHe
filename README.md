### SECCIÓN 1: EJECUCIÓN DEL PROGRAMA EN ECLIPSE
- git clone https://github.com/pirineocalvo/PracticaFinalCPHe.git
- Antes de abrir el proyecto configura el .env (SECCIÓN 4).
- Abrir eclipse-> File-> Open Projects from File System-> Seleccionar la carpeta del proyecto
- Clic derecho sobre el proyecto en el Project Explorer de Eclipse-> Properties-> Java Build Path-> Aquí añadir los paquetes en rojo dándole a Edit y buscándolo en tu sistema. En caso de que no aparezcan, ir a la siguiente sección 2 para descargarlos.
- Ahora comprueba que al añadir/editar tengas jasypt esté en Modulepath y mysql-connector en Classpath
- Después de configurar el Java Build Path, clickar en Apply and close
![image](https://github.com/user-attachments/assets/ac649456-37af-49b5-9704-c19e69600a27)
- Finalmente, despliega el proyecto en el Project Explorer, localiza el Main.java, clic derecho sobre él y Run As-> Java Application
---

### SECCIÓN 2: PAQUETES NECESARIOS
_Opción 1, necesario cuenta google drive_
Este link te redirecciona a un drive con las versiones de jasypt y mysql-connector exactas para el proyecto. Se encuentran en la carpeta libs.
https://drive.google.com/drive/folders/13huduxYea_ZIueQ5Bcayc0RxYvqvjFxR?usp=drive_link
_Opción 2, pinchar en el .jar_
- JASYPT: 1.9.3 --> https://mvnrepository.com/artifact/org.jasypt/jasypt/1.9.3
- MYSQL-CONNECTOR: 9.2.0 --> https://mvnrepository.com/artifact/com.mysql/mysql-connector-j/9.2.0
![image](https://github.com/user-attachments/assets/7ab4df98-a9e6-4c9b-bdb0-844774c04271)
---

### SECCIÓN 3: POSIBLE PROBLEMA DE CODIFICACIÓN
Puede ocurrir que Eclipse tenga la codicifación de caracteres errónea y no detecte bien las tildes o la ñ, entre otros. Te explico los pasos a seguir: 
- Mete el proyecto como se comenta anteriormente.
- Clic derecho sobre el proyecto-> Properties-> Resource-> En la parte de Text file encofing-> Marca en azul Other-> UTF-8
- Finalmente, Apply and close
![image](https://github.com/user-attachments/assets/59497365-41b5-4f68-80c0-c0e562016170)

---
### SECCIÓN 4: PROBLEMA .env Y CONEXIÓN BBDD
- El proyecto subido en este repositorio tiene el script concesionario.sql con la bbdd PracticaFinalCPHe, pero no el archivo .env necesario para la ejecución debes añadirlo tú.
- Asegúrate que en el Explorador de archivos muestra extensiones: Ver-> Mostrar-> Extensiones de nombre de archivo
- Explorador de archivos-> Abre la carpeta del proyecto PracticaFinalCPHe-> Clic derecho sobre cualquier parte vacía de la carpeta-> Nuevo-> Documento de texto.txt-> Cambia (incluyendo la extensión) el nombre a ".env".
![image](https://github.com/user-attachments/assets/3c34209f-072d-47ae-b7b9-e44700abdb9f)

_Rellenar .env_
<pre>
   DB_HOST=
   DB_PORT=
   DB_USERNAME=
   DB_PASSWORD=
   DB_DATABASE=PracticaFinalCPHe
</pre>
 


