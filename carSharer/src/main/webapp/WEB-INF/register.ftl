<#include "header.ftl">
        <title> Register </title>
    </head>

    <body>
        <div>
            <div class="wrapper">
                <form action="main" method="post">
                    <h2 class="main">Register page</h2>
                	<#if error??>
            			<h3 style="color: red;text-align: center;">${error}</h3>
            		</#if>
                    <table>
                        <tr>
                            <td> <label for="name"> name: </label> </td>
                            <td> <input type="text" id="name" name="name" required> </td>
                        </tr>
                        <tr>
                            <td> <label  for="emailadress"> email_adress: </label> </td>
                            <td> <input type="email" id="emailadress" name="email" required> </td>
                        </tr>
                    </table>
                    <a href="login"> already registerd </a>
                    <button id="submit" type="submit" class="rl_button">register</button>
                    <div style="clear: both;"></div>
                </form>
            </div>
    </div>
    </body>
    </html>