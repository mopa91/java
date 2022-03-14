<#include "header.ftl">
    <title> Search </title>
</head>
<body>
<#include "navbar.ftl">
    <div>
        <div class="search_wrapper">
            <form action="view_search" method="post">
                <#if simpleError??>
                	<h3 style="color: red;text-align: center;"> ${simpleError} </h3>
               	</#if>
                <table>
                    <tr>
                        <td> <label for=""> start: </label> 
                        <input type="text" name="start"> </td>
                        <td> <label for=""> ziel: </label> 
                        <input type="text" name="ziel"> </td>
                    </tr>
                    <tr>
                        <td><label for=""> ab: </label> 
                        <input type="date" name="date"> </td>
                        <td><button type="submit" class="rl_button">suchen</button></td>
                    </tr>
                </table>
                <div style="clear: both;"></div>
            </form>
            <hr>
            <div>
                Suchergebnnise
                <div>
                <#if error??>
                	<h3 style="color: red;text-align: center;"> ${error} </h3>
               	</#if>
               	<#if listUser??>
	               	<#list listUser as listUser>
		               	<#if listUser.startort = startort && listUser.zielort = zielort>
		                    <div class="search_pic-finger">
		                        <a href="view_drive?fid=${listUser.fid}">
		                        <img border="0" src="pic/${listUser.pic}" width="50" height="50" class="center">
		                        </a>
		                        <p> Von: ${listUser.startort} </p>
		                        <p> Nach: ${listUser.zielort} </p>
		                        <p> pries: ${listUser.fahrtkosten}</p>
		                    </div>
		                </#if>
	               	</#list>
               	</#if>
                <div style="clear: both;"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>