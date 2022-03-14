    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Login </title>
    </head>
    <style>
        body {
            background-color: rgb(197, 193, 193);
        }
        .wrapper {
            padding: 10px;
            border: 2px solid;
            width: 300px;
            margin: 5% 35%;
            padding: 20px;
        }
        .main {
            text-align: center;
        }
        .button {
            text-decoration: none;
            border: 2px solid black;
            border-radius: 5px;
            background-color: rgb(102, 102, 196);
            color: white;
            padding: 5px 20px;
            box-shadow: 1px 3px black;
            float:right;
            cursor:pointer
        }
        td {
            padding: 10px 0;
        }

    </style>
    <body>
        <div>
            <div class="wrapper">
                <form action="login" method="post">
                    <h2 class="main">Register page</h2>
                	<#if error??>
            			<h3 style="color: red;text-align: center;">${error}</h3>
            		</#if>
                    <table>
                        <tr>
                            <td> <label for=""> name: </label> </td>
                            <td> <input type="text" name="name"> </td>
                        </tr>
                    </table>
                    <a href="register"> make an account </a>
                    <button type="submit" class="button">login</button>
                    <div style="clear: both;"></div>
                </form>
            </div>
    </div>
    </body>
    </html>