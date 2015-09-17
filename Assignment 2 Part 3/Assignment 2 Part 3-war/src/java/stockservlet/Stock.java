/**
 * Simple stock class for a stock market database.
 **/

package stockservlet;

public class Stock 
{
    private String company = "";
    private int price = 0;
    private String url = "";
    
    public Stock(String c, int p, String u)
    {
        company = c;
        price = p;
        url = u;
    }
    
    public void setCompany(String c)
    {
        company = c;
    }
    
    public void setPrice(int p)
    {
        price = p;
    }
    
    public void setUrl(String u)
    {
        url = u;
    }
    
    public String getCompany()
    {
        return company;
    }
    
    public int getPrice()
    {
        return price;
    }
    
    public String getUrl()
    {
        return url;
    }
}
