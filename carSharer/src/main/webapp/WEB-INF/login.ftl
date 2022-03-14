<#include "header.ftl">
        <title> Login </title>
    </head>
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
                            <td> <label for=""> email: </label> </td>
                            <td> <input type="email" name="email"> </td>
                        </tr>
                    </table>
                    <a href="register"> make an account </a>
                    <button type="submit" class="rl_button">login</button>
                    <div style="clear: both;"></div>
                </form>
            </div>
    </div>
    </body>
    </html>