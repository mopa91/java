<#include "header.ftl">
    <title>New_Rating</title>
</head>
<body>
<#include "navbar.ftl">
    <div class="rate_wrapper">
    	<#if viewDrive??>
    		<form action="/new_rating?fid=${viewDrive.fahrtId}" method="post">
				<h1 class="main">
                Fahrt Bewertung
            	</h1>
            	<#if errors??>
             		<h3 style="color: red;text-align: center;">
				${errors}
					</h3>
				</#if>
				 <#if error??>
             		<h3 style="color: red;text-align: center;">
				${error}
            	</h3>
            </#if> 
            <table>
                <tr>
                	<input type="hidden" name="benutzerId" value="${benutzerId}">
                    <td>Bewertungstext:</td>
                    <td><textarea name="Bewertungstext" id="" cols="40" rows="10"></textarea></td>
                </tr>
                <tr>
                    <td>Bewertungsrating:</td>
                    <td style="margin-left: 10px;">
                        <input type="radio" name="Bewertungsrating" value="1" id="">
                        <label for="" style="margin-right: 70px;">1</label>
                        <input type="radio" name="Bewertungsrating" value="2" id="">
                        <label for="" style="margin-right: 70px;">2</label>
                        <input type="radio" name="Bewertungsrating" value="3" id="">
                        <label for="" style="margin-right: 60px;">3</label>
                        <input type="radio" name="Bewertungsrating" value="4" id="">
                        <label for="" style="margin-right: 70px;">4</label>
                        <input type="radio" name="Bewertungsrating" value="5" id="">
                        <label for="" style="margin-right: 50px;">5</label>
                    </td>
                </tr>
            </table>
            <button type="submit" class="button"> Bewerten </button>
            <div style="clear: both;"></div>
        	</form>
		</#if>
    </div>
</body>
</html>