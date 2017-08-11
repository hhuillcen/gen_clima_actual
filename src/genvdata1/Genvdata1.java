/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package genvdata1;
import java.sql.*;
import java.io.*;
/**
 *
 * @author Herwin
 */
public class Genvdata1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    Connection conexion = null;
        try {
            // Cargar el driver
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/weewx", "weewx", "7s3r");
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select from_unixtime(dateTime) as orden, date_format(from_unixtime(dateTime),'%d-%m-%Y') as fecha, date_format(from_unixtime(dateTime),'%r') as hora, round(((outTemp-32)/1.8),2) as temp, concat(outHumidity,'%') as humedad, concat(round((windSpeed/0.62137),2),' Km/h') as viento, uv FROM archive order by orden desc limit 1");
            while (rs.next()) {
                //System.out.println(rs.getString("fecha") + " " + rs.getString("hora")+" "+rs.getString("temp")+" "+rs.getString("humedad")+" "+rs.getString("viento")+" "+rs.getString("uv"));
                 FileWriter miArchivo = null;
                 PrintWriter escribirArchivo;

                    try
                    {
                        miArchivo = new FileWriter("/home/hv/public_html/sedeadm.html");
                        //miArchivo = new FileWriter("d:\\arffs\\sedeadm.html");
                        escribirArchivo = new PrintWriter(miArchivo);
                        escribirArchivo.println(rs.getString("fecha"));
                        escribirArchivo.println(rs.getString("hora"));
                        escribirArchivo.println(rs.getString("temp"));
                        escribirArchivo.println(rs.getString("humedad"));
                        escribirArchivo.println(rs.getString("viento"));
                        if (rs.getString("uv").length()==1)
                        {
                            escribirArchivo.println(rs.getString("uv")+".0");
                        }
                        else
                        {
                            escribirArchivo.println(rs.getString("uv"));
                        }
                      
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    finally
                    {
                        try
                        {
                            if (null != miArchivo)
                            {
                                miArchivo.close();
                            }
                        }
                        catch (Exception ex1)
                        {
                            System.out.println(ex1.getMessage());
                        }
                    }
                
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally { // Se cierra la conexión con la base de datos.
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }        
    }
            
/////////////////////////empieza el otro archivo para uv actuales del paso del día
    int c=0;  
        try {
                   FileWriter miArchivo = null;
                 PrintWriter escribirArchivo;          
            
                                try
                    {
                        
                        miArchivo = new FileWriter("/home/hv/public_html/uv_actual.txt");
                        //miArchivo = new FileWriter("d:\\arffs\\uv_actual.txt");
                        escribirArchivo = new PrintWriter(miArchivo);
                        //escribirArchivo.println("\""+rs.getString("fecha")+"\""+","+rs.getString("mes")+","+rs.getString("d_anio")+","+rs.getString("hormin")+"," +rs.getString("hora")+","+rs.getString("minuto")+","+rs.getString("temp")+","+rs.getString("humedad")+","+rs.getString("viento")+","+rs.getString("lluvia")+","+rs.getString("uv")+","+rs.getString("UV_M_1D")+","+rs.getString("UV_M_1H"));
                        //escribirArchivo.println("INICIO");
   
                        
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    finally
                    {
                        try
                        {
                            if (null != miArchivo)
                            {
                                miArchivo.close();
                            }
                        }
                        catch (Exception ex1)
                        {
                            System.out.println(ex1.getMessage());
                        }
                    }   
            
            
            // Cargar el driver
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/weewx", "weewx", "7s3r");
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select from_unixtime(dateTime) as fecha,"+    
                    "date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60 as hora, "+
                    "round(((outTemp-32)/1.8),2) as temp, outHumidity as humedad, round((windSpeed/0.62137),2) as viento, rain as lluvia, uv as uv_actual "+
                    "from archive "+
                    "where "+  
                    "date_format(from_unixtime(dateTime),'%Y-%m+%d')=date_format(now(),'%Y-%m+%d') "+
                    "and  (date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='6.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='6.5000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='7.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='7.5000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='8.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='8.5000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='9.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='9.5000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='10.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='10.5000' "+
                    "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='11.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='11.5000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='12.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='12.5000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='13.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='13.5000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='14.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='14.5000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='15.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='15.5000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='16.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='16.5000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='17.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='17.5000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='18.0000') "+
                        "order by fecha");
                                   
                 
            while (rs.next()) {
                //System.out.println(rs.getString("fecha") + " " + rs.getString("hora")+" "+rs.getString("temp")+" "+rs.getString("humedad")+" "+rs.getString("viento")+" "+rs.getString("uv"));


                    try
                    {
                        miArchivo = new FileWriter("/home/hv/public_html/uv_actual.txt",true);
                        //miArchivo = new FileWriter("d:\\arffs\\uv_actual.txt",true);
                        escribirArchivo = new PrintWriter(miArchivo);
                        if (rs.getString("uv_actual").equalsIgnoreCase("NULL")){
                            escribirArchivo.println("0");
                        }
                        else{
                           escribirArchivo.println(rs.getString("uv_actual")); 
                        }
                        
                        c++;    
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    finally
                    {
                        try
                        {
                            if (null != miArchivo)
                            {
                                miArchivo.close();
                            }
                        }
                        catch (Exception ex1)
                        {
                            System.out.println(ex1.getMessage());
                        }
                    }
                
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally { // Se cierra la conexión con la base de datos.
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }        
    }
            
        
        
        
        
        if (c<25){
                 FileWriter miArchivo = null;
                 PrintWriter escribirArchivo;
                   try
                    {   
                        if (c==0){
                         miArchivo = new FileWriter("/home/hv/public_html/uv_actual.txt"); 
                         //miArchivo = new FileWriter("d:\\arffs\\uv_actual.txt");
                        }
                        else
                        {
                         miArchivo = new FileWriter("/home/hv/public_html/uv_actual.txt",true);   
                         //miArchivo = new FileWriter("d:\\arffs\\uv_actual.txt",true);
                        }
                        
                
                        escribirArchivo = new PrintWriter(miArchivo);
                        for (int x=c+1;x<=25;x++){
                            
                            escribirArchivo.println("0");
                        }
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    finally
                    {
                        try
                        {
                            if (null != miArchivo)
                            {
                                miArchivo.close();
                            }
                        }
                        catch (Exception ex1)
                        {
                            System.out.println(ex1.getMessage());
                        }
                    }
                
                
            }
     
        
        
////////////////////////fin del otro archivo  para medida de uv del paso del día     
        
        
        
        
        
        
        
        
        
        
        /////////////////////////empieza el otro archivo para temperaturas actuales del paso del día
         c=0;  
        try {
                   FileWriter miArchivo = null;
                 PrintWriter escribirArchivo;          
            
                    try
                    {
                        //miArchivo = new FileWriter("d:\\arffs\\temp_actual.txt");
                        miArchivo = new FileWriter("/home/hv/public_html/temp_actual.txt");
                        escribirArchivo = new PrintWriter(miArchivo);
                        //escribirArchivo.println("\""+rs.getString("fecha")+"\""+","+rs.getString("mes")+","+rs.getString("d_anio")+","+rs.getString("hormin")+"," +rs.getString("hora")+","+rs.getString("minuto")+","+rs.getString("temp")+","+rs.getString("humedad")+","+rs.getString("viento")+","+rs.getString("lluvia")+","+rs.getString("uv")+","+rs.getString("UV_M_1D")+","+rs.getString("UV_M_1H"));
                        //escribirArchivo.println("INICIO");
   
                        
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    finally
                    {
                        try
                        {
                            if (null != miArchivo)
                            {
                                miArchivo.close();
                            }
                        }
                        catch (Exception ex1)
                        {
                            System.out.println(ex1.getMessage());
                        }
                    }   
            
            
            // Cargar el driver
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/weewx", "weewx", "7s3r");
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select from_unixtime(dateTime) as fecha,"+    
                    "date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60 as hora, "+
                    "round(((outTemp-32)/1.8),2) as temp, outHumidity as humedad, round((windSpeed/0.62137),2) as viento, rain as lluvia, uv as uv_actual "+
                    "from archive "+
                    "where "+  
                    "date_format(from_unixtime(dateTime),'%Y-%m+%d')=date_format(now(),'%Y-%m+%d') "+
                    "and  (date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='0.5000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='1.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='2.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='3.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='4.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='5.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='6.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='7.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='8.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='9.0000' "+
                    "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='10.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='11.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='12.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='13.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='14.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='15.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='16.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='17.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='18.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='19.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='20.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='21.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='22.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='23.0000' "+
                        "or date_format(from_unixtime(dateTime),'%k')+(date_format(from_unixtime(dateTime),'%i'))/60='23.5000') "+
                        "order by fecha");
                                   
                 
            while (rs.next()) {
                //System.out.println(rs.getString("fecha") + " " + rs.getString("hora")+" "+rs.getString("temp")+" "+rs.getString("humedad")+" "+rs.getString("viento")+" "+rs.getString("uv"));


                    try
                    {
                        miArchivo = new FileWriter("/home/hv/public_html/temp_actual.txt",true);
                        //miArchivo = new FileWriter("d:\\arffs\\temp_actual.txt",true);
                        escribirArchivo = new PrintWriter(miArchivo);
                        if (rs.getString("temp").equalsIgnoreCase("NULL")){
                            escribirArchivo.println("0");
                        }
                        else{
                           escribirArchivo.println(rs.getString("temp")); 
                        }
                        
                        c++;    
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    finally
                    {
                        try
                        {
                            if (null != miArchivo)
                            {
                                miArchivo.close();
                            }
                        }
                        catch (Exception ex1)
                        {
                            System.out.println(ex1.getMessage());
                        }
                    }
                
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally { // Se cierra la conexión con la base de datos.
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }        
    }
            
        
        
        
        
        if (c<25){
                 FileWriter miArchivo = null;
                 PrintWriter escribirArchivo;
                   try
                    {   
                        if (c==0){
                         miArchivo = new FileWriter("/home/hv/public_html/temp_actual.txt");
                          //miArchivo = new FileWriter("d:\\arffs\\temp_actual.txt");
                        }
                        else
                        {
                         miArchivo = new FileWriter("/home/hv/public_html/temp_actual.txt",true);  
                         // miArchivo = new FileWriter("d:\\arffs\\temp_actual.txt",true);
                        }
                        
                       
                        escribirArchivo = new PrintWriter(miArchivo);
                        for (int x=c+1;x<=25;x++){
                            
                            escribirArchivo.println("0");
                        }
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                    finally
                    {
                        try
                        {
                            if (null != miArchivo)
                            {
                                miArchivo.close();
                            }
                        }
                        catch (Exception ex1)
                        {
                            System.out.println(ex1.getMessage());
                        }
                    }
                
                
            }
     
        
        
////////////////////////fin del otro archivo  para medida de uv del paso del día      
        
    }
}
