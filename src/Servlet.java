

import org.json.JSONArray;

import org.json.JSONObject;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebEndpoint;
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
        response.setContentType("plain/text");

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
                }


            }

            query += "(" + "'" + isbn + "'" + "," + "'" + titolo + "'" + "," + "'" + autore + "'" + "," + "'" + casaed + "'" + ");";

            response.getWriter().println(query);

            int value = stmt.executeUpdate(query);

            if(value!=0)
            {
                response.getWriter().println("Inserimento avvenuto correttamente");

            }else
            {
                response.getWriter().println("Inserimento non avvenuto");
            }

            stmt.close();
            conn.close();



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }







    }


    @SuppressWarnings("Duplicates")

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int count;
        int i;


        try{
            try{

                MariaDbDataSource dataSource = new MariaDbDataSource();
                dataSource.setServerName("localhost");
                dataSource.setDatabaseName("biblioteca");
                dataSource.setUser("root");
                dataSource.setPassword("");

                Connection conn = dataSource.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM libri");
                /*A ResultSet is a Java object that contains the results of executing an SQL query. In other words, it contains the rows that satisfy the conditions of the query.
                 * The data stored in a ResultSet object is retrieved through a set of get methods that allows access to the various columns of the current row.*/

                response.setContentType("application/json");

                JSONArray jsonArray = new JSONArray();

                try {
                    while (rs.next()) {
                        ResultSetMetaData metaData = rs.getMetaData();//ottengo nomi colonne tabella

                        count = metaData.getColumnCount();//conto le colonne

                        JSONObject jsonObject = new JSONObject();//creo oggetto per contenere il json
                        for(i=1;i<=count;i++){

                            jsonObject.put(metaData.getColumnName(i),rs.getObject(i));//inserisco key e value nel jsonobject
                        }
                        jsonArray.put(jsonObject);//metto il jsonobject in un apposito array
                    }
                }
                finally {
                    rs.close();
                }

                response.getWriter().println(jsonArray);//stampo


                stmt.close();
                conn.close();
            }catch(SQLException e){
                response.getWriter().println("Errore SQL");
                e.printStackTrace();
            }
        }catch(IOException e){
            System.out.println("Errore IO");
        }

    }
}
