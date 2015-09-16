<!DOCTYPE html>
<html>
    <head>
        <title>Stock Database</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <h1>Stock Database</h1>
        <form action="Stocks" method="post">
            <h2>What would you like to do?</h2>
            Edit or delete an existing stock:<br/>
            Code: <input type="text" name="code"/>
            <input type="submit" name="Find" value="Find"/>
            <input type="submit" name="Delete" value="Delete"/> <br/>
            <br/>
            Add a stock:<br/>
            Code: <input type="text" name="stockcode"/>
            Full company name: <input type="text" name="company"/>
            Price in cents: <input type="text" name="price"/>
            Web page: <input type="text" name="url"/>
            <input type="submit" name="Add" value="Add"/>
            <br/><br/>
            List all stocks:
            <input type="submit" name="List All" value="List All"/>
            <br/>
        </form>
</html>
