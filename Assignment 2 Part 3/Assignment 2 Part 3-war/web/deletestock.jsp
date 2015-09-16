<!DOCTYPE html>
<html>
    <head>
        <title>Stock Database: Delete a stock</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <h1>Stock Database</h1>
    <form action="Stocks" method="post">
        <h2>Do you really want to delete ${deletecode}?</h2><br/>
        <input type="submit" name="Yes" value="Yes" style="width:80px"/> Delete <br/><br/>
        <input type="submit" name="No" value="No" style="width:80px" href="/index.jsp"/> Go back<br/><br/>
        <input type="hidden" name="storedcode" value="${deletecode}">
    </form>
</html>
