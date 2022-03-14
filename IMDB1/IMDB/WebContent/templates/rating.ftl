	<#include "header.ftl">
            <div class="wrapper">
            <#if movieRate??>
                <form action="rate?title=${movieRate.title}&username=${movieRate.username}" method="post">
                    <h2 class="main"> Rating movie </h2>
                    <#if error??>
            			<h3 style="color: red;text-align: center;">${error}</h3>
            		</#if>
            		<#if successful??>
            			<h3 style="color: green;text-align: center;">${successful}</h3>
            		</#if>
                    <table>
					
						<tr>
                            <td> <label for=""> Title: </label> </td>
                            <td> <input type="text" name="title" value="${movieRate.title}" disabled> </td>
                            <td> <input type="hidden" name="movieid" value="${movieRate.movieid}"> </td>
                            <td> <input type="hidden" name="registerId" value="${movieRate.registerId}"> </td>

                        </tr>
                        <tr>
                            <td> <label for=""> Director: </label> </td>
                            <td> <input type="text" name="director" value="${movieRate.director}" disabled> </td>
                        </tr>
                        <tr>
                            <td> <label for=""> Main Actor: </label> </td>
                            <td> <input type="text" name="actor" value="${movieRate.actor}" disabled> </td>
                        </tr>
					<#if AverageRate??>
						<tr>
                            <td> <label for=""> movieRate: </label> </td>
                            <td> <input type="text" name="actor" value="${AverageRate.rate}" disabled> </td>
                        </tr>
					</#if>
					<#if existError??>
						<h3 style="color: red;text-align: center;">${existError}</h3>
					<#else>
						<tr>
	                    <td> Comment:</td>
	                    <td><textarea name="comment" id="" cols="20" rows="10"></textarea></td>
	               		</tr>
	                	<tr>
	                    <td>Rate:</td>
	                    <td style="margin-left: 10px;">
	                        <input type="radio" name="rate" value="1" id="">
	                        <label for="" style="margin-right: 10px;">1</label>
	                        <input type="radio" name="rate" value="2" id="">
	                        <label for="" style="margin-right: 10px;">2</label>
	                        <input type="radio" name="rate" value="3" id="">
	                        <label for="" style="margin-right: 20px;">3</label>
	                        <input type="radio" name="rate" value="4" id="">
	                        <label for="" style="margin-right: 10px;">4</label>
	                        <input type="radio" name="rate" value="5" id="">
	                        <label for="" style="margin-right: 10px;">5</label>
	                    </td>
	                	</tr>
	                    </table>
	                    <button type="submit" class="button">send</button>
					</#if>
                    <div style="clear: both;"></div>
            
                </form>
			</#if>
            </div>
    </div>
    </body>
    </html>