<!DOCTYPE html>
<html>
    <head>
        <title>Stock Database: Edit a stock</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <h1>Stock Database</h1>
    <form action="Stocks" method="post">
        <h2>Stock Details</h2>
        ${modifycode}<br/><br/>
        ${fullstockinfo}<br/><br/>
        <b>Edit ${modifycode}:</b><br/><br/>
        Full company name: <input type="text" name="company"/>
        Price in cents: <input type="text" name="price"/>
        Web page: <input type="text" name="url"/><br/><br/>
        <input type="submit" name="Confirm" value="Confirm" style="width:80px"/> Edit<br/><br/>
        <input type="submit" name="Cancel" value="Cancel" style="width:80px" href="/index.jsp"/> Go back<br/><br/>
        <input type="hidden" name="storededitcode" value="${modifycode}">
    </form>
</html>
