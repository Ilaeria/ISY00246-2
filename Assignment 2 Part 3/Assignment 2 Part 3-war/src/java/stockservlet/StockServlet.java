/**
 * Created by: Jennifer Doherty
 * Unit: ISY00246 S2 2015
 * Date: 5 September 2015
 * Assignment 2 Part 3
 * Stock servlet. From Lab 10 documentation.
 **/

package stockservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockServlet extends HttpServlet 
{
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private HashMap<String, Stock> data;
    
    public void init()
    {
        data = new HashMap<String,Stock>();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
    response.setContentType("text/html;charset=UTF-8"); 
    PrintWriter out = response.getWriter();
    try 
    {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Stocks</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<br/>"); 
        out.println("<h1>Stock Database</h1>");
            
            //Determine which button was clicked
            String buttonClicked = buttonClick(request);
            // Now process each button type
            if (buttonClicked == "Find") 
            {
                String code = request.getParameter("code");
                if (!data.containsKey(code))
                {
                    out.println("No such stock in database.<br/>");
                }
                else
                {
                    out.println("Code: " + code 
                            + " Full company name: " + data.get(code).getCompany() 
                            + " Price in cents: "  + data.get(code).getPrice()
                            + " Web page: " + data.get(code).getUrl()
                            + "<br/>");
                }
            } 
            else if (buttonClicked == "Delete") 
            {
                String code = request.getParameter("code");
                if (!data.containsKey(code))
                {
                    out.println("No such stock in database.<br/>");
                }
                else 
                {
                    data.remove(code);
                    out.println("Code: " + code + " removed from database.<br/>");
                }
            }
            else if (buttonClicked == "Add") 
            {
                String stockCode = request.getParameter("stockcode");
                String company = request.getParameter("company");
                String url = request.getParameter("url");
                
                //Try to parse the price
                int price = 0;
                try
                {
                    price = Integer.parseInt(request.getParameter("price"));
                }
                catch (NumberFormatException e)
                {
                    out.println("The stock price must be a whole number in cents.<br/>");
                }
                out.println(stockCode + company + price + url + "<br/>");
                
                if (data.containsKey(stockCode))
                {
                    out.println("That stock already exists in database.<br/>");
                }
                else if (company == "" || company == null || price == 0 || url == "" || url == null)
                {
                    out.println("You must provide all stock details when adding.<br/>");
                }
                else 
                {
                    Stock stock = new Stock(company, price, url);
                    data.put(stockCode, stock);
                    out.println("Code: " + stockCode + " successfully added.<br/>");
                }
            } 
            else if (buttonClicked == "List All") 
            {
                out.println("Stock list:<br/>");
                for (String code : data.keySet()) 
                {
                    out.println("Code: " + code 
                            + " Full company name: " + data.get(code).getCompany() 
                            + " Price in cents: "  + data.get(code).getPrice()
                            + " Web page: " + data.get(code).getUrl()
                            + "<br/>");
                }
            } 
            else
            {
                out.println("An error has occured.<br/>");
            }
                
            // provide link back to main page
            out.println("<br/> <a href=\""+request.getContextPath()+"\">Go to home page</a>");
            
            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception e)
        {
            out.println("An error has occured:<br/>");
            e.printStackTrace(out);
        }
        
        finally
        {
            out.close();
        }
    }
    
    public void destroy() 
    {
    }
    
    //Check which button was clicked
    private String buttonClick(HttpServletRequest request) 
    {
        if (request.getParameter("Find") != null)
            return "Find";
        else if (request.getParameter("Delete") != null)
            return "Delete";
        else if (request.getParameter("Add") != null)
            return "Add";
        else if (request.getParameter("List All") != null)
            return "List All";
        else
            return null;
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() 
    {
        return "Stocks database";
    }

}
