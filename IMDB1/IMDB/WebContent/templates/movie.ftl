    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Add Movie </title>
		<#include "header.ftl">
        <div>
            <div class="wrapper">
                <form action="addRate" method="post">
                    <h2 class="main">Add Movie</h2>
					<td>  </td>
                    <#if error??>
            			<h3 style="color: red;text-align: center;">${error}</h3>
            		</#if>
            		<#if successful??>
            			<h3 style="color: green;text-align: center;">${successful}</h3>
            		</#if>
                    <table>
                        <tr>
                            <td> <label for=""> Title: </label> </td>
                            <td> <input type="text" name="title"> </td>
                       <#if username??>
                           	<td> <input type="hidden" name="username" value="${username}"> </td>
                        </#if>
                        </tr>
                        <tr>
                            <td> <label for=""> Director: </label> </td>
                            <td> <input type="text" name="director"> </td>
                        </tr>
                        <tr>
                            <td> <label for=""> Main Actor: </label> </td>
                            <td> <input type="text" name="actor"> </td>
                        </tr>
						<tr>
                			<td><label for=""> publishDate: </label></td>
                			<td>
                    		<input type="date" name="date">
               			 	</td>
            			</tr>
                    </table>
					<#if successful??>
            		<div>
            			<h3><a href="rate?title=${title}&username=${username}"> Rate this movie </a> </h3>
            		</div>
            		</#if>
                    <button type="submit" class="button">send</button>
                    <div style="clear: both;"></div>
                </form>

            </div>
    </div>
    </body>
    </html>