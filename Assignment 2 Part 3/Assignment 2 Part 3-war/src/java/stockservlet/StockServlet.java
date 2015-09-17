/**
 * Created by: Jennifer Doherty
 * Unit: ISY00246 S2 2015
 * Date: 17 September 2015
 * Assignment 2 Part 3
 * Stock servlet. From Lab 10 documentation.
 **/

package stockservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.net.*;
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
            
            //Set the different stock code variables
            String editCode = request.getParameter("editcode");
            String addCode = request.getParameter("addcode");
            
            //Determine which button was clicked
            String buttonClicked = buttonClick(request);
            
            //Process each button
            
            //Handle the edit cases
            if (buttonClicked == "Find" || buttonClicked == "Confirm" || buttonClicked == "Cancel") 
            {
                String modifyCode = editCode;
                String storedEditCode = request.getParameter("storededitcode");
                
                if (buttonClicked == "Find") //Direct to edit page
                {
                    String fullStockInfo = "Code: " + editCode 
                            + ", full company name: " + data.get(editCode).getCompany() 
                            + ", price in cents: "  + data.get(editCode).getPrice()
                            + ", web page: <a href=\"http://" + data.get(editCode).getUrl() 
                            + "\" target=\"_blank\">" + data.get(editCode).getUrl() + "</a><br/>";
                    
                    request.setAttribute("modifycode", modifyCode);
                    request.setAttribute("fullstockinfo", fullStockInfo);
                    request.getRequestDispatcher("/editstock.jsp").forward(request, response);
                }
                else if (buttonClicked == "Cancel") //Didn't want to edit
                {
                    out.println("Edit request for " + storedEditCode + " cancelled.</br>");
                }
                else if (buttonClicked == "Confirm") //Confirmed edit
                {
                    if (!data.containsKey(storedEditCode))
                    {
                        out.println("No such stock in database. Note: search is case-sensitive.<br/>");
                    }
                    else
                    {
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

                        if (company == "" || company == null || price == 0 || url == "" || url == null)
                        {
                            out.println("You must provide all stock details when adding.<br/>");
                        }
                        else 
                        {
                            Stock editStock = new Stock(company, price, url);
                            data.replace(storedEditCode, editStock);
                            out.println("Code: " + storedEditCode + " successfully edited.<br/>");
                        }
                    }
                }
            }
            
            //Handle the delete cases
            else if (buttonClicked == "Delete" || buttonClicked == "Yes" || buttonClicked == "No")
            {
                String deleteCode = editCode;
                String storedDeleteCode = request.getParameter("storeddeletecode");

                if (buttonClicked == "Delete") //Direct to confirmation page
                {
                    request.setAttribute("deletecode", deleteCode);
                    request.getRequestDispatcher("/deletestock.jsp").forward(request, response);
                }
                else if (buttonClicked == "No") //Changed mind about deleting
                {
                    out.println("Delete request for " + storedDeleteCode + " cancelled.</br>");
                }
                else if (buttonClicked == "Yes") //Confirmed delete
                {
                    if (!data.containsKey(storedDeleteCode))
                    {
                        out.println("No such stock in database. Note: search is case-sensitive.<br/>");
                    }
                    else 
                    {
                        data.remove(storedDeleteCode);
                        out.println("Code: " + storedDeleteCode + " removed from database.<br/>");
                    }
                }
            }
            else if (buttonClicked == "Add") 
            {
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
                
                if (data.containsKey(addCode))
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
                    data.put(addCode, stock);
                    out.println("Code: " + addCode + " successfully added.<br/>");
                }
            } 
            else if (buttonClicked == "List All") 
            {
                out.println("<h2>Stock list:</h2><br/>");
                for (String listCode : data.keySet()) 
                {
                    
                    out.println("Code: " + listCode 
                            + ", full company name: " + data.get(listCode).getCompany() 
                            + ", price in cents: "  + data.get(listCode).getPrice()
                            + ", web page: <a href=\"http://" + data.get(listCode).getUrl() 
                            + "\" target=\"_blank\">" + data.get(listCode).getUrl() + "</a><br/>");  
                }
            } 
            else
            {
                out.println(buttonClicked + ": an error has occured.<br/>");
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
        else if (request.getParameter("No") != null)
            return "No";
        else if (request.getParameter("Yes") != null)
            return "Yes";
        else if (request.getParameter("Confirm") != null)
            return "Confirm";
        else if (request.getParameter("Cancel") != null)
            return "Cancel";
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
