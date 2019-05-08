

import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.*;
import java.util.Enumeration;

public class Servlet extends javax.servlet.http.HttpServlet {


    @SuppressWarnings("Duplicates")
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String isbn="";
        /*String titolo="";
        String autore="";
        String casaed="";*/




        try {

            MariaDbDataSource dataSource = new MariaDbDataSource();
            dataSource.setServerName("localhost");
            dataSource.setDatabaseName("biblioteca");
            dataSource.setUser("root");
            dataSource.setPassword("");
            PreparedStatement stmt;
            Connection conn;
            conn = dataSource.getConnection();
            response.setContentType("text/plain");

            Enumeration<String> parameterNames = request.getParameterNames();//SET DEI POSSIBILI VALORI DELLA REQUEST (nel nostro caso: isbn - titolo - autore - casaed...)
            String paramName;
            String paramValue;

            while (parameterNames.hasMoreElements()) {

                paramName = parameterNames.nextElement();

                paramValue = request.getParameter(paramName);//ottengo il valore del parametro che ha nome *paramName*

                    switch(paramName) {

                        //cancella la tupla con i seguenti valori:
                        case "isbn":
                            if(paramValue!= null) {
                                isbn = paramValue;
                            }break;
                       /* case "titolo":
                            if(paramValue!= null) {
                                titolo = paramValue;
                            }break;
                        case "autore":
                            if(paramValue!= null) {
                                autore = paramValue;
                            }break;
                        case "casaed":
                            if(paramValue!= null) {
                                casaed = paramValue;
                            }break;*/


                    }

            }

            //debug
            response.getWriter().println(isbn);
            /*response.getWriter().println(titolo);
            response.getWriter().println(autore);
            response.getWriter().println(casaed);*/

            stmt = conn.prepareStatement("DELETE FROM libri WHERE isbn=?;");

            stmt.setString(1,isbn);
            /*stmt.setString(2,titolo);
            stmt.setString(3,autore);
            stmt.setString(4,casaed);*/


            int righe_cancellate = stmt.executeUpdate();



            if(righe_cancellate!=0)
            {
                response.getWriter().println("Cancellazione avvenuta correttamente. Righe cancellate: " + righe_cancellate);

            }else
            {
                response.getWriter().println("Cancellazione non avvenuta");
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    @SuppressWarnings("Duplicates")
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // TODO Auto-generated method stub



        String isbn="";
        /*String titolo="";
        String autore="";
        String casaed="";*/

        String new_isbn="";
        String new_titolo="";
        String new_autore="";
        String new_casaed="";
        String paramName="";
        String paramValue="";

        response.setContentType("plain/text");

        try {

        MariaDbDataSource dataSource = new MariaDbDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("biblioteca");
        dataSource.setUser("root");
        dataSource.setPassword("");
        PreparedStatement stmt;

        Connection conn;

            conn = dataSource.getConnection();


            Enumeration<String> parameterNames = request.getParameterNames();//SET DEI POSSIBILI VALORI DELLA REQUEST (nel nostro caso: isbn - titolo - autore - casaed...)

            while (parameterNames.hasMoreElements()) {

                paramName = parameterNames.nextElement();
                paramValue = request.getParameter(paramName);//ottengo il valore del parametro che ha nome *paramName*

                switch(paramName) {

                    //aggiorna la tupla:
                    case "isbn":
                        if(paramValue!= null) {
                            isbn = paramValue;
                        }break;
                    /*case "titolo":
                        if(paramValue!= null) {
                            titolo = paramValue;
                        }break;
                    case "autore":
                        if(paramValue!= null) {
                            autore = paramValue;
                        }break;
                    case "casaed":
                        if(paramValue!= null) {
                            casaed = paramValue;
                        }break;*/
                    //nuovi valori
                    case "new_isbn":
                        if(paramValue!= null) {
                            new_isbn = paramValue;
                        }break;
                    case "new_titolo":
                        if(paramValue!= null) {
                            new_titolo = paramValue;
                        }break;
                    case "new_autore":
                        if(paramValue!= null) {
                            new_autore = paramValue;
                        }break;
                    case "new_casaed":
                        if(paramValue!= null) {
                            new_casaed = paramValue;
                        }break;



                }


            }

            //debug
            response.getWriter().println(isbn);
            /*response.getWriter().println(titolo);
            response.getWriter().println(autore);
            response.getWriter().println(casaed);*/
            response.getWriter().println(new_isbn);
            response.getWriter().println(new_titolo);
            response.getWriter().println(new_autore);
            response.getWriter().println(new_casaed);



            //stmt = conn.prepareStatement("UPDATE libri SET isbn = ?, titolo= ?, autore= ?, casaed=? WHERE isbn = ?, titolo=?, autore=?, casaed=?;");

            stmt = conn.prepareStatement("UPDATE `libri` SET `isbn`=?,`titolo`=?,`autore`=?,`casaed`=? WHERE `isbn`=?;");


            stmt.setString(1,new_isbn);
            stmt.setString(2,new_titolo);
            stmt.setString(3,new_autore);
            stmt.setString(4,new_casaed);
            stmt.setString(5,isbn);
            /*stmt.setString(6,titolo);
            stmt.setString(7,autore);
            stmt.setString(8,casaed);*/


             int righe_aggiornate = stmt.executeUpdate();

            if(righe_aggiornate!=0)
            {
                response.getWriter().println("Aggiornamento avvenuto correttamente. Righe aggiornate: " + righe_aggiornate);
            }else
            {
                response.getWriter().println("Aggiornamento non avvenuto.");
            }


            response.getWriter().println("Righe aggiornate: " + righe_aggiornate);

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @SuppressWarnings("Duplicates")
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String query = "INSERT INTO libri (isbn,titolo,autore,casaed) VALUES ";
        String isbn="";
        String titolo="";
        String autore="";
        String casaed="";
        String tipo_op="";
        String nome_ut="";
        String cogn_ut="";
        int id_ut=0;
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        response.setContentType("application/json");

        try {
            MariaDbDataSource dataSource = new MariaDbDataSource();
            dataSource.setServerName("localhost");
            dataSource.setUser("root");
            dataSource.setPassword("");
            dataSource.setDatabaseName("biblioteca");


            Connection conn;
            Statement stmt;


            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            Enumeration<String> parameterNames = request.getParameterNames();//SET DEI POSSIBILI VALORI DELLA REQUEST (nel nostro caso: isbn - titolo - autore - casaed)

            String paramValue;
            String paramName;

            while(parameterNames.hasMoreElements()) {

                paramName = parameterNames.nextElement();
                paramValue = request.getParameter(paramName);//ottengo il valore del parametro che ha nome *paramName*

                switch(paramName) {
                    case "isbn":
                        if (paramValue != null) {
                            isbn = paramValue;
                        }
                        break;
                    case "titolo":
                        if (paramValue != null) {
                            titolo = paramValue;
                        }
                        break;
                    case "autore":
                        if (paramValue != null) {
                            autore = paramValue;
                        }
                        break;
                    case "casaed":
                        if (paramValue != null) {
                            casaed = paramValue;
                        }
                        break;
                    case "tipo_op":
                        if (paramValue != null) {
                            tipo_op = paramValue;
                        }
                        break;
                    case "id_ut":
                        if (paramValue != null) {
                            id_ut = Integer.parseInt(paramValue);
                        }
                        break;
                    case "nome_ut":
                        if (paramValue != null) {
                            nome_ut = paramValue;
                        }
                        break;
                    case "cogn_ut":
                        if (paramValue != null) {
                            cogn_ut = paramValue;
                        }
                        break;

                }


            }

            switch(request.getRequestURI())
            {
                case "/api/libri":
                    //INSERIMENTO LIBRO
                    query += "(" + "'" + isbn + "'" + "," + "'" + titolo + "'" + "," + "'" + autore + "'" + "," + "'" + casaed + "'" + ");";

                    //response.getWriter().println(query);

                    int value = stmt.executeUpdate(query);


                    if(value!=0)
                    {
                        jsonObject.put("response_code",201);
                        jsonArray.put(jsonObject);

                    }else
                    {
                        jsonObject.put("response_code",400);
                        jsonArray.put(jsonObject);
                    }

                    response.getWriter().println(jsonArray);//stampo
                    stmt.close();
                    conn.close();
                    break;

                case "/api/prestiti":
                    //prima, ricerca id utente tramite nome e cognome
                    PreparedStatement pstmt = conn.prepareStatement("SELECT id_ut FROM utenti WHERE nome_ut=? AND cogn_ut=?");
                    pstmt.setString(1,nome_ut);
                    pstmt.setString(2,cogn_ut);

                    ResultSet rs= pstmt.executeQuery();
                    while(rs.next())
                    {
                        id_ut = rs.getInt("id_ut");//ottengo l'id dell'utente
                    }

                    /*
                    * AGGIUNTA: inserire boolean su libri che identifica la disponibilità (1=disponibile 0=non disponibile)
                    * dunque, se tipo_op = noleggio verifico se il boolean del libro è 1 altrimenti
                    * ritorno un response code 400
                    * */

                    if(tipo_op.equalsIgnoreCase("noleggio"))
                    {
                        pstmt = conn.prepareStatement("SELECT disponibile FROM libri WHERE isbn=?");
                        pstmt.setString(1,isbn);
                        rs = pstmt.executeQuery();
                        boolean disponibile=false;
                        while(rs.next())
                        {
                            disponibile = rs.getBoolean("disponibile");
                        }
                        if(disponibile)//se il libro è disponibile
                        {
                            pstmt = conn.prepareStatement("INSERT INTO op VALUES (NULL,?,NULL,?,?)");
                            pstmt.setString(1,tipo_op);
                            pstmt.setInt(2,id_ut);
                            pstmt.setString(3,isbn);
                            value = pstmt.executeUpdate();

                            if(value!=0)
                            {
                                jsonObject.put("response_code",201);
                                jsonArray.put(jsonObject);

                            }else
                            {
                                jsonObject.put("response_code",400);
                                jsonArray.put(jsonObject);
                            }

                            response.getWriter().println(jsonArray);//stampo
                            stmt.close();
                            conn.close();
                        }else//se non disponibile
                        {
                            jsonObject.put("response_code",500);
                            jsonArray.put(jsonObject);
                            response.getWriter().println(jsonArray);//stampo
                            stmt.close();
                            conn.close();
                        }

                    }


                    pstmt = conn.prepareStatement("INSERT INTO op VALUES (NULL,?,NULL,?,?)");
                    pstmt.setString(1,tipo_op);
                    pstmt.setInt(2,id_ut);
                    pstmt.setString(3,isbn);
                    value = pstmt.executeUpdate();

                    if(value!=0)
                    {
                        jsonObject.put("response_code",201);
                        jsonArray.put(jsonObject);

                    }else
                    {
                        jsonObject.put("response_code",400);
                        jsonArray.put(jsonObject);
                    }

                    response.getWriter().println(jsonArray);//stampo
                    stmt.close();
                    conn.close();
                    break;
            }




        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }







    }


    @SuppressWarnings("Duplicates")

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int count;
        int i;
        String isbn;

        try{
            try{

                String auto = new String();
                MariaDbDataSource dataSource = new MariaDbDataSource();

                    dataSource.setServerName("localhost");
                    dataSource.setDatabaseName("biblioteca");
                    dataSource.setUser("root");
                    dataSource.setPassword("");
                    Connection conn = dataSource.getConnection();
                    Statement stmt = conn.createStatement();

                response.setContentType("application/json");
                JSONArray jsonArray = new JSONArray();
                //JSONObject jsonObject = new JSONObject();
                ResultSet rs;

                //GET PER AUTOCOMPLETE
                Enumeration<String> parameterNames = request.getParameterNames();
                String paramValue;
                String paramName;

                switch(request.getRequestURI())
                {

                    case "/api/libri":
                        //codice per funzione autocomplete jquery ui (da rivedere)
                        /*if (parameterNames.hasMoreElements())//true if and only if this enumeration object contains at least one more element to provide; false otherwise.
                        {
                            while (parameterNames.hasMoreElements())
                            {
                                paramName = parameterNames.nextElement();
                                paramValue = request.getParameter(paramName);//ottengo il valore del parametro che ha nome *paramName*

                                if (paramName.equalsIgnoreCase("auto") && !paramName.isEmpty())
                                {
                                    auto = paramValue;
                                    try
                                    {
                                        PreparedStatement pstmt;
                                        pstmt = conn.prepareStatement("SELECT titolo FROM `libri` WHERE titolo=?;");//POSSIBILE ERRORE NELLA QUERY
                                        pstmt.setString(1, "%" + auto);
                                        rs = pstmt.executeQuery();

                                        while (rs.next())
                                        {
                                            ResultSetMetaData metaData = rs.getMetaData();//ottengo nomi colonne tabella
                                            count = metaData.getColumnCount();//conto le colonne
                                            JSONObject jsonObject = new JSONObject();
                                            for (i = 1; i <= count; i++)
                                            {
                                                try
                                                {
                                                    jsonObject.put(metaData.getColumnName(i), rs.getObject(i));//inserisco key e value nel jsonobject
                                                } catch (JSONException e)
                                                {
                                                    e.printStackTrace();
                                                }

                                                jsonArray.put(jsonObject);
                                            }//for
                                        }//while


                                        response.getWriter().println(jsonArray);
                                        pstmt.close();
                                        conn.close();
                                    } catch (SQLException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }


                            }//end while
                        }//end if parameterNames.hasmoreelements
                        else
                        {*/
                            //se nessun parametro è stato specificato, restituisci la lista dei libri completa
                            try
                            {
                                rs = stmt.executeQuery("SELECT * FROM libri");
                                while (rs.next())
                                {

                                    ResultSetMetaData metaData = rs.getMetaData();//ottengo nomi colonne tabella

                                    count = metaData.getColumnCount();//conto le colonne

                                    JSONObject jsonObject = new JSONObject();//creo oggetto per contenere il json
                                    for (i = 1; i <= count; i++)
                                    {

                                        jsonObject.put(metaData.getColumnName(i), rs.getObject(i));//inserisco key e value nel jsonobject
                                    }


                                    jsonArray.put(jsonObject);//metto il jsonobject in un apposito array
                                }


                                response.getWriter().println(jsonArray);//stampo


                                stmt.close();
                                conn.close();
                            } catch (SQLException e)
                            {
                                e.printStackTrace();
                            }

                        break;// case "/libri"

                    case "/api/prestiti":
                        int id_ut;
                        while (parameterNames.hasMoreElements())
                        {
                            paramName = parameterNames.nextElement();
                            paramValue = request.getParameter(paramName);//ottengo il valore del parametro che ha nome *paramName*

                            switch (paramName)
                            {
                                case "id_ut":
                                    //dato l'id_utente visualizzo tutte le sue operazioni presso la biblioteca
                                    if (paramValue != null)
                                    {
                                        id_ut = Integer.parseInt(paramValue);

                                        PreparedStatement pstmt;
                                        pstmt = conn.prepareStatement("SELECT * FROM `op` WHERE id_ut_fk=?;");
                                        pstmt.setInt(1,id_ut);
                                        rs = pstmt.executeQuery();

                                        try
                                        {
                                            while (rs.next())
                                            {
                                                ResultSetMetaData metaData = rs.getMetaData();//ottengo nomi colonne tabella
                                                count = metaData.getColumnCount();//conto le colonne
                                                JSONObject jsonObject = new JSONObject();
                                                for (int a = 1; a<= count; a++)//CREO L'OGGETTO (un singolo utente)
                                                {
                                                    try
                                                    {
                                                        jsonObject.put(metaData.getColumnName(a), rs.getObject(a));//inserisco key e value nel jsonobject
                                                    } catch (JSONException e)
                                                    {
                                                        e.printStackTrace();
                                                    }
                                                }

                                                jsonArray.put(jsonObject);//metto l'oggetto creato nell'array

                                                //i++;

                                            }//end while
                                        }finally
                                        {
                                            rs.close();
                                        }

                                        response.getWriter().println(jsonArray);//stampo
                                        stmt.close();
                                        conn.close();
                                    }
                                    break;

                                case "isbn":
                                    //dato l'isbn controllo da chi è stato noleggiato e riconsegnato (oppure solo noleggiato e non ancora riconsegnato)
                                    if (paramValue != null)
                                    {
                                        isbn = paramValue;
                                        PreparedStatement pstmt;
                                        pstmt = conn.prepareStatement("SELECT * FROM `op` WHERE isbn_fk=?;");
                                        pstmt.setString(1,isbn);
                                        rs = pstmt.executeQuery();

                                        try
                                        {
                                            while (rs.next())
                                            {
                                                ResultSetMetaData metaData = rs.getMetaData();//ottengo nomi colonne tabella
                                                count = metaData.getColumnCount();//conto le colonne
                                                JSONObject jsonObject = new JSONObject();
                                                for (int a = 1; a<= count; a++)//CREO L'OGGETTO (un singolo utente)
                                                {
                                                    try
                                                    {
                                                        jsonObject.put(metaData.getColumnName(a), rs.getObject(a));//inserisco key e value nel jsonobject
                                                    } catch (JSONException e)
                                                    {
                                                        e.printStackTrace();
                                                    }
                                                }

                                                jsonArray.put(jsonObject);//metto l'oggetto creato nell'array

                                                //i++;

                                            }//end while
                                        }finally
                                        {
                                            rs.close();
                                        }

                                        response.getWriter().println(jsonArray);//stampo
                                        stmt.close();
                                        conn.close();


                                    }
                                    break;

                                /*case from
                                 * case to*/
                            }//end inner switch
                        }//end while
                }//end switch URI

            }catch(SQLException e){
                response.getWriter().println("Errore SQL");
                e.printStackTrace();
            }
        }catch(IOException e){
            System.out.println("Errore IO");
        }


    }}
